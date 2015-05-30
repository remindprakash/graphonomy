package org.graph.api.service;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import org.graph.api.domain.Authority;
import org.graph.api.domain.User;
import org.graph.api.graph.repository.GraphUserRepository;
import org.graph.api.neo4j.domain.GUser;
import org.graph.api.neo4j.domain.Organization;
import org.graph.api.repository.AuthorityRepository;
import org.graph.api.repository.UserRepository;
import org.graph.api.security.SecurityUtils;
import org.graph.api.service.util.RandomUtil;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserServiceImpl implements  UserService {
	
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	private Neo4jOperations template;
	
    @Inject
    private AuthorityRepository authorityRepository;
    
    @Inject
    private PasswordEncoder passwordEncoder;
    
    @Inject
    private UserRepository userRepository;

    @Inject
    private GraphUserRepository graphUserRepository;
    
	@Override
	public GraphAPIUserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException, DataAccessException {
		final User user = findByLogin(login);
		if (user == null)
			throw new UsernameNotFoundException("Username not found: " + login);
		return new GraphAPIUserDetails(user);
	}

	public User findByLogin(String login) {
		return userRepository.findOneByLogin(login);
	}

	@Override
	public User getUserFromSession() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		Object principal = authentication.getPrincipal();
		if (principal instanceof GraphAPIUserDetails) {
			GraphAPIUserDetails userDetails = (GraphAPIUserDetails) principal;
			return userDetails.getUser();
		}
		return null;
	}

	void setUserInSession(User user) {
		SecurityContext context = SecurityContextHolder.getContext();
		GraphAPIUserDetails userDetails = new GraphAPIUserDetails(user);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
				userDetails, user.getPassword(), userDetails.getAuthorities());
		context.setAuthentication(authentication);

	}

	@Transactional
	public User createUserInformation(String login, String password,
			String companyName, String firstName, String lastName, String email, String langKey) {

		User newUser = new User();
		Authority authority = authorityRepository.findOne("ROLE_USER");
		Set<Authority> authorities = new HashSet<>();
		String encryptedPassword = passwordEncoder.encode(password);
		newUser.setLogin(login);
		// new user gets initially a generated password
		newUser.setPassword(encryptedPassword);
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		newUser.setEmail(email);
		newUser.setLangKey(langKey);
		// new user is not active
		newUser.setActivated(false);
		// new user gets registration key
		newUser.setActivationKey(RandomUtil.generateActivationKey());
		authorities.add(authority);
		newUser.setAuthorities(authorities);
		userRepository.save(newUser);
		GUser guser = new GUser();
		guser.setLogin(login);
		guser.setFirstName(firstName);
		guser.setLastName(lastName);
		guser.setEmail(email);
		Organization org = new Organization();
		org.setName(companyName);
		guser.setOrganization(org);
		graphUserRepository.save(guser);
		if(logger.isDebugEnabled()) {
			logger.debug("Created Information for GUser: {}", newUser);
		}	
		return newUser;
	}
	
    public  User activateRegistration(String key) {
        logger.debug("Activating user for activation key {}", key);
        User user = userRepository.findOneByActivationKey(key);
        // activate given user for the registration key.
        if (user != null) {
            user.setActivated(true);
            user.setActivationKey(null);
            userRepository.save(user);
            logger.debug("Activated user: {}", user);
        }
        return user;
    }

    public User completePasswordReset(String newPassword, String key) {
        logger.debug("Reset user password for reset key {}", key);
        User user = userRepository.findOneByResetKey(key);
        DateTime oneDayAgo = DateTime.now().minusHours(24);
        if (user != null) {
            if (user.getResetDate().isAfter(oneDayAgo.toInstant().getMillis())) {
                user.setActivated(true);
                user.setPassword(passwordEncoder.encode(newPassword));
                user.setResetKey(null);
                user.setResetDate(null);
                userRepository.save(user);
                return user;
            } else {
                return null;
            }
        }
        return null;
    }

    public User requestPasswordReset(String mail) {
        User user = userRepository.findOneByEmail(mail);
            if (user != null) {
                user.setResetKey(RandomUtil.generateResetKey());
                user.setResetDate(DateTime.now());
                userRepository.save(user);
                return user;
            }
        return user;
    }

    @Transactional(readOnly = true)
    public User getUserWithAuthorities() {
        User currentUser = userRepository.findOneByLogin(SecurityUtils.getCurrentLogin());
        currentUser.getAuthorities().size(); // eagerly load the association
        return currentUser;
    }
    
    public void updateUserInformation(String firstName, String lastName, String email) {
        User currentUser = userRepository.findOneByLogin(SecurityUtils.getCurrentLogin());
        currentUser.setFirstName(firstName);
        currentUser.setLastName(lastName);
        currentUser.setEmail(email);
        userRepository.save(currentUser);
        logger.debug("Changed Information for GUser: {}", currentUser);
    }

    public void changePassword(String password) {
        User currentUser = userRepository.findOneByLogin(SecurityUtils.getCurrentLogin());
        String encryptedPassword = passwordEncoder.encode(password);
        currentUser.setPassword(encryptedPassword);
        userRepository.save(currentUser);
        logger.debug("Changed password for GUser: {}", currentUser);
    }

}

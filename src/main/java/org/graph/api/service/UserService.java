package org.graph.api.service;

import org.graph.api.domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserService extends UserDetailsService {
	
	@Override
	GraphAPIUserDetails loadUserByUsername(String login)
			throws UsernameNotFoundException, DataAccessException;

	User getUserFromSession();

    User findByLogin(String login);

    public User createUserInformation(String login, String password,
			String companyName, String firstName, String lastName, String email, String langKey);
    
    public  User activateRegistration(String key);
    
    public User completePasswordReset(String newPassword, String key);
    
    public User requestPasswordReset(String mail);
    
    public User getUserWithAuthorities();
    
    public void updateUserInformation(String firstName, String lastName, String email);
    
    public void changePassword(String password);

}

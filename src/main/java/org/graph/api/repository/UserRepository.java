package org.graph.api.repository;

import java.util.List;

import org.graph.api.domain.User;
import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the GUser entity.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByActivationKey(String activationKey);

    List<User> findAllByActivatedIsFalseAndCreatedDateBefore(DateTime dateTime);

    User findOneByResetKey(String resetKey);

    User findOneByLogin(String login);

    User findOneByEmail(String email);

}

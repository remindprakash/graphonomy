package org.graph.api.graph.repository;

import java.util.List;

import org.graph.api.neo4j.domain.GUser;
import org.joda.time.DateTime;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;

/**
 * 
 * @author gnana
 *
 */
public interface GraphUserRepository extends GraphRepository<GUser>,
        RelationshipOperationsRepository<GUser>
         {

    GUser findByLogin(String login);
    
    GUser findOneByActivationKey(String activationKey);

    GUser findOneByResetKey(String resetKey);

    GUser findOneByLogin(String login);

    GUser findOneByEmail(String email);

}

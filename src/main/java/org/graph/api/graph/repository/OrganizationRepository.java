package org.graph.api.graph.repository;

import org.graph.api.neo4j.domain.Organization;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;

/**
 * 
 * @author gnana
 *
 */
public interface OrganizationRepository extends GraphRepository<Organization>, RelationshipOperationsRepository<Organization> {

	Organization findById(String id);
}

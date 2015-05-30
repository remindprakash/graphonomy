package org.graph.api.graph.repository;

import org.graph.api.neo4j.domain.GRelationship;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;


/**
 * 
 * @author gnana
 *
 */
public interface GRelationshipRepository extends GraphRepository<GRelationship>, RelationshipOperationsRepository<GRelationship> {

	GRelationship findById(String id);
}

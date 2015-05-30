package org.graph.api.graph.repository;

import org.graph.api.neo4j.domain.Database;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;


/**
 * 
 * @author gnana
 *
 */
public interface DatabaseRepository extends GraphRepository<Database>, RelationshipOperationsRepository<Database> {

	Database findById(String id);
}

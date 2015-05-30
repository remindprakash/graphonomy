package org.graph.api.graph.repository;

import org.graph.api.neo4j.domain.GNode;
import org.springframework.data.neo4j.repository.GraphRepository;
import org.springframework.data.neo4j.repository.RelationshipOperationsRepository;


/**
 * 
 * @author gnana
 *
 */
public interface GNodeRepository extends GraphRepository<GNode>, RelationshipOperationsRepository<GNode> {

	GNode findById(String id);
}

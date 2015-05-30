package org.graph.api.service;

import java.util.Set;

import org.graph.api.neo4j.domain.Database;
import org.graph.api.neo4j.domain.GUser;
import org.graph.api.neo4j.domain.Organization;

public interface GraphUserService {

	
	Organization getOrganization(GUser user);

	Set<Database> getDatabases(GUser user);
	

}


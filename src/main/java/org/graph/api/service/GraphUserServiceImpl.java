package org.graph.api.service;

import java.util.Set;

import org.graph.api.graph.repository.GraphUserRepository;
import org.graph.api.neo4j.domain.Database;
import org.graph.api.neo4j.domain.GUser;
import org.graph.api.neo4j.domain.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.neo4j.template.Neo4jOperations;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("graphUserService")
public class GraphUserServiceImpl implements GraphUserService {

	@Autowired
	private Neo4jOperations template;
	
	@Autowired
	private GraphUserRepository userRepository;
	
	@Override
	@Transactional(readOnly=true)
	public Organization getOrganization(GUser user) {
		if(user == null) {
			return null;
		}
		template.fetch(user.getOrganization());
		return user.getOrganization();
	}
	
	@Override
	public Set<Database> getDatabases(GUser user) {
		if (user == null) {
			return null;
		}
		Set<Database> databases = template.fetch(user.getDatabases());
		return databases;
	}


}

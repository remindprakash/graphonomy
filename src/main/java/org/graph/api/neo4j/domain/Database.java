package org.graph.api.neo4j.domain;

import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Database {

	@GraphId
	Long nodeId;

	@Indexed
	long id;

	@Indexed
	private String name;

	@RelatedTo(type = "OWNS_DATABASE", direction = Direction.INCOMING)
	private Set<GUser> users;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<GUser> getUsers() {
		return users;
	}

	public void setUsers(Set<GUser> users) {
		this.users = users;
	}

}

package org.graph.api.neo4j.domain;

import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class GNode {

	@GraphId
	Long nodeId;

	@Indexed
	long id;

	@Indexed
	private String label;

	@RelatedTo(type = "CONTAINS_ATTR", direction = Direction.OUTGOING)
	Set<GAttribute> attributes;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Set<GAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<GAttribute> attributes) {
		this.attributes = attributes;
	}

}

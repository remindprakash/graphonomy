package org.graph.api.neo4j.domain;

import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class GRelationship {

	@GraphId
	Long nodeId;

	@Indexed
	long id;
	
	@Indexed
	private String label;

	private GNode start;

	private GNode end;

	private String direction;

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

	public GNode getStart() {
		return start;
	}

	public void setStart(GNode start) {
		this.start = start;
	}

	public GNode getEnd() {
		return end;
	}

	public void setEnd(GNode end) {
		this.end = end;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public Set<GAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(Set<GAttribute> attributes) {
		this.attributes = attributes;
	}

}

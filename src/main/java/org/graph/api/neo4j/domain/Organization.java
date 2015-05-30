package org.graph.api.neo4j.domain;

import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;

@NodeEntity
public class Organization {
    @GraphId Long nodeId;

    @Indexed
    long id;
    
    @Indexed
    String name;
    String address1;
    String address2;
    String city;
    String state;
    String country;
    
    @RelatedTo(type = "USER_ORG" , direction=Direction.INCOMING)
    Set<GUser> users;

    public Organization() {
    }

    public Organization(String name) {
        this.name = name;
    }
    
    
    

    public void setId(long id) {
		this.id = id;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setName(String name) {
		this.name = name;
	}

    public String getName() {
        return name;
    }
    
	public Set<GUser> getUsers() {
		return users;
	}

	public void setUsers(Set<GUser> users) {
		this.users = users;
	}
	
	public void addUser(GUser user) {
		this.users.add(user);
	}

	@Override
    public String toString() {
        return String.format("%s (%s)", name);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization user = (Organization) o;
        if (nodeId == null) return super.equals(o);
        return nodeId.equals(user.nodeId);

    }

    public Long getId() {
        return nodeId;
    }

    @Override
    public int hashCode() {
        return nodeId != null ? nodeId.hashCode() : super.hashCode();
    }
}

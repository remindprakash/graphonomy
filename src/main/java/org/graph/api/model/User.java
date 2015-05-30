package org.graph.api.model;

import java.util.Set;

import org.neo4j.graphdb.Direction;
import org.springframework.data.neo4j.annotation.Fetch;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.Indexed;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.GrantedAuthority;

@NodeEntity
public class User {
    @GraphId Long nodeId;

    private static final String SALT = "cewuiqwzie";

    @Indexed
    String login;
    String name;
    String password;
    String info;
    
    private Roles[] roles;

    @RelatedTo(type = "USER_ORG", direction = Direction.OUTGOING)
    @Fetch Organization organization;
    
    @RelatedTo(type = "OWNS_DATABASE", direction = Direction.OUTGOING)
    Set<Database> databases;

    public User() {
    }

    public User(String login, String name, String password, Roles... roles) {
        this.login = login;
        this.name = name;
        this.password = encode(password);
        this.roles = roles;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", name, login);
    }

    public String getName() {
        return name;
    }

    public Roles[] getRole() {
        return roles;
    }


    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
    public void updatePassword(String old, String newPass1, String newPass2) {
        if (!password.equals(encode(old))) throw new IllegalArgumentException("Existing Password invalid");
        if (!newPass1.equals(newPass2)) throw new IllegalArgumentException("New Passwords don't match");
        this.password = encode(newPass1);
    }

    private String encode(String password) {
        return new Md5PasswordEncoder().encodePassword(password, SALT);
    }


    public void setName(String name) {
        this.name = name;
    }
    
    public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public Set<Database> getDatabases() {
		return databases;
	}

	public void setDatabases(Set<Database> databases) {
		this.databases = databases;
	}



	public enum Roles implements GrantedAuthority {
        ROLE_USER, ROLE_ADMIN;

        @Override
        public String getAuthority() {
            return name();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
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

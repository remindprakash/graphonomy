package org.graph.api.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.graph.api.domain.Authority;
import org.graph.api.domain.User;
import org.graph.api.neo4j.domain.GUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 
 * @author gnana
 *
 */
public class GraphAPIUserDetails implements UserDetails {
	
    private final User user;

    public GraphAPIUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
    	Set<Authority> roles =  user.getAuthorities();
        if (roles ==null) return Collections.emptyList();
        List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
        auths.addAll(roles);
        return auths;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public User getUser() {
        return user;
    }
}

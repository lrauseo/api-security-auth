package com.lrrauseo.auth.model;

import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@Document("profile")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Profile implements GrantedAuthority {

    /**
     * 
     */
    private static final long serialVersionUID = 2460058283119705118L;

    @Id
    private String id;
    private String name;
    @DBRef
    private Set<AuthUser> users = new LinkedHashSet<>();

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    @Override
    public String getAuthority() {
	// TODO Auto-generated method stub
	return null;
    }

    public Set<AuthUser> getUsers() {
	return users;
    }

    public void setUsers(Set<AuthUser> users) {
	this.users = users;
    }

}

package com.lrrauseo.auth.model;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Document("auth-user")
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthUser implements UserDetails {

    /**
    * 
    */
    private static final long serialVersionUID = -5025458038848980110L;

    @Id
    private String id;

    private String username;
    private String password;
    private String email;

    @DBRef
    private Set<Profile> profiles = new LinkedHashSet<Profile>();

    public String getId() {
	return id;
    }

    public void setId(String id) {
	this.id = id;
    }

    public String getUserName() {
	return username;
    }

    public void setUserName(String userName) {
	this.username = userName;
    }

    public void setProfiles(Set<Profile> profiles) {
	this.profiles = profiles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
	return profiles.stream().map((p) -> new SimpleGrantedAuthority(p.getName())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {

	return password;
    }

    @Override
    public String getUsername() {

	return username;
    }

    @Override
    public boolean isAccountNonExpired() {
	// TODO Auto-generated method stub
	return true;
    }

    @Override
    public boolean isAccountNonLocked() {
	// TODO Auto-generated method stub
	return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
	// TODO Auto-generated method stub
	return true;
    }

    @Override
    public boolean isEnabled() {
	// TODO Auto-generated method stub
	return true;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

}

package com.lrrauseo.auth.dto.response;

import java.io.Serializable;
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

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseAuthUserDTO implements Serializable {

    /**
    * 
    */
    private static final long serialVersionUID = -5025458038848980110L;

    private String id;

    private String username;

    private String email;

    private Set<ResponseProfileDTO> profiles = new LinkedHashSet<ResponseProfileDTO>();

}

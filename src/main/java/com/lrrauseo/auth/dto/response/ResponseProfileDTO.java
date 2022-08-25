package com.lrrauseo.auth.dto.response;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lrrauseo.auth.model.AuthUser;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseProfileDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4942428506777282891L;
    private String id;
    private String name;
    private Set<AuthUser> users = new LinkedHashSet<>();
}

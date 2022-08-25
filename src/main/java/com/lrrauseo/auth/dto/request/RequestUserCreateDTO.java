package com.lrrauseo.auth.dto.request;

import java.io.Serializable;
import java.util.LinkedHashSet;
import java.util.Set;

import lombok.Data;

@Data
public class RequestUserCreateDTO implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 3897215993352067777L;
    private String username;
    private String email;
    private String password;
    private Set<RequestCreateProfileDTO> profiles = new LinkedHashSet<RequestCreateProfileDTO>();
}

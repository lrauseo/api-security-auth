package com.lrrauseo.auth.dto.request;

import java.io.Serializable;

import lombok.Data;

@Data
public class RequestCreateProfileDTO implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 4942428506777282891L;
    
    private String name;
}

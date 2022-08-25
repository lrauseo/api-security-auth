package com.lrrauseo.auth.dto.request;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDTO {
    private String username;
    private String password;
    public UsernamePasswordAuthenticationToken converter() {	
	return new UsernamePasswordAuthenticationToken(username, password);
    }
}

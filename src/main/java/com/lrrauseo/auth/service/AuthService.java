package com.lrrauseo.auth.service;

import java.io.UnsupportedEncodingException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import com.lrrauseo.auth.config.security.TokenService;
import com.lrrauseo.auth.dto.request.LoginRequestDTO;
import com.lrrauseo.auth.dto.response.ResponseTokenDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final AuthenticationManager authManager;

    private final TokenService tokenService;

    public ResponseTokenDTO authenticate(LoginRequestDTO loginDto)
	    throws UnsupportedEncodingException, AuthenticationException {
	UsernamePasswordAuthenticationToken dadosLogin = loginDto.converter();
	Authentication authentication = authManager.authenticate(dadosLogin);
	return tokenService.generateToken(authentication);

    }

}

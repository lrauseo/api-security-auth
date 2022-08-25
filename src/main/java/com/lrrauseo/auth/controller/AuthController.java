package com.lrrauseo.auth.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lrrauseo.auth.dto.request.LoginRequestDTO;
import com.lrrauseo.auth.dto.response.ResponseTokenDTO;
import com.lrrauseo.auth.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping
    public ResponseEntity<ResponseTokenDTO> autenticar(@RequestBody LoginRequestDTO loginDto)
	    throws UnsupportedEncodingException, AuthenticationException {

	ResponseTokenDTO token = service.authenticate(loginDto);

	return ResponseEntity.ok(token);

    }
}

package com.lrrauseo.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lrrauseo.auth.dto.request.RequestUserCreateDTO;
import com.lrrauseo.auth.dto.response.ResponseAuthUserDTO;
import com.lrrauseo.auth.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final  UserService service;    
    
    @PostMapping
    public ResponseEntity<String> create(@RequestBody RequestUserCreateDTO userDTO) {
	
	return ResponseEntity.created(null).body(service.createUser(userDTO));
    }
    
    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<ResponseAuthUserDTO> getById(@PathVariable String id){
    	return ResponseEntity.ok().body(service.getById(id));
    }
}

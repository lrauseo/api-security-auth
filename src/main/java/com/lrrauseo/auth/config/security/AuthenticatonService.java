package com.lrrauseo.auth.config.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lrrauseo.auth.model.AuthUser;
import com.lrrauseo.auth.repository.AuthUserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthenticatonService implements UserDetailsService {

    private final AuthUserRepository repository;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
 
	Optional<AuthUser> user = repository.findByUsername(username);
	return user.orElseThrow(() -> new UsernameNotFoundException("Usuario/Senha Inválidos"));	
    }

}

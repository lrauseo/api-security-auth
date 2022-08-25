package com.lrrauseo.auth.config.security;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.lrrauseo.auth.model.AuthUser;
import com.lrrauseo.auth.repository.AuthUserRepository;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AuthenticationTokenFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final AuthUserRepository repository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
	    throws ServletException, IOException,ExpiredJwtException {

	String token = getToken(request);
	if (token!= null) {
	    boolean valido = tokenService.isValidToken(token);
	    if (valido) {
		autenticarCliente(token);
	    }
	}

	filterChain.doFilter(request, response);
    }

    private void autenticarCliente(String token) {
	String userId;
	try {
	    userId = tokenService.getUserId(token);
	    AuthUser usuario = repository.findById(userId).get();
	    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null,
		    usuario.getAuthorities());
	    SecurityContextHolder.getContext().setAuthentication(authentication);
	} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException
		| IllegalArgumentException | UnsupportedEncodingException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    private String getToken(HttpServletRequest request) {
	String token = request.getHeader("Authorization");
	if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
	    return null;
	}

	return token.substring(7, token.length());
    }

}

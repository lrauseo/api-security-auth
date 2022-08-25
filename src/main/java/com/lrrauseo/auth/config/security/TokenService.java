package com.lrrauseo.auth.config.security;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.lrrauseo.auth.dto.response.ResponseTokenDTO;
import com.lrrauseo.auth.model.AuthUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class TokenService {
    @Value("${auth.jwt.expiration}")
    private String expiration;

    @Value("${auth.jwt.secret}")
    private String secret;

    public ResponseTokenDTO generateToken(Authentication authentication) throws UnsupportedEncodingException {
	AuthUser authenticated = (AuthUser) authentication.getPrincipal();

	Calendar expirationTime = Calendar.getInstance();
	expirationTime.add(Calendar.MILLISECOND, Integer.parseInt(expiration));
	log.info("expiration : {}", expirationTime.getTime());
	String tokenId = UUID.randomUUID().toString();

	String simpleKey = Base64.getEncoder().encodeToString(secret.getBytes("UTF-8"));
	var jwt = Jwts.builder().setId(tokenId).setIssuer("Auth Server").setSubject(authenticated.getId().toString())
		.setIssuedAt(Calendar.getInstance().getTime()).setExpiration(expirationTime.getTime())
		.signWith(SignatureAlgorithm.HS512, simpleKey);
	return ResponseTokenDTO.builder().token(jwt.compact()).tipo("Bearer")
		.expiration(expirationTime.getTime().toString()).build();
    }

    public boolean isValidToken(String token) throws IllegalArgumentException, UnsupportedEncodingException {
	try {

	    Jwts.parser().setSigningKey(secret.getBytes("UTF-8")).parseClaimsJws(token);
	    return true;
	} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException e) {
	    log.info(e.getMessage());
	    throw e;

	}
    }

    public String getUserId(String token) throws ExpiredJwtException, UnsupportedJwtException, MalformedJwtException,
	    SignatureException, IllegalArgumentException, UnsupportedEncodingException {
	Claims claims = Jwts.parser().setSigningKey(secret.getBytes("UTF-8")).parseClaimsJws(token).getBody();
	return claims.getSubject();
    }
}

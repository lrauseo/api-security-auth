package com.lrrauseo.auth.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.lrrauseo.auth.repository.AuthUserRepository;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfiguration {

	private final AuthenticatonService authService;
	private final TokenService tokenService;
	private final AuthUserRepository authRepository;

	@Bean
	protected AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {

		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	protected SecurityFilterChain configure(HttpSecurity http) throws Exception {

		http.userDetailsService(authService)
				.authorizeHttpRequests((authz) -> authz.antMatchers(HttpMethod.POST, "/auth").permitAll()
						.antMatchers(HttpMethod.POST, "/user")
						.permitAll().anyRequest().authenticated())
				.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.addFilterBefore(new AuthenticationTokenFilter(tokenService, authRepository),
						UsernamePasswordAuthenticationFilter.class);

		return http.build();

	}

	@Bean
	protected PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

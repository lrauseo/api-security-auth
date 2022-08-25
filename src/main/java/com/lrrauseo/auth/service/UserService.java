package com.lrrauseo.auth.service;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lrrauseo.auth.dto.request.RequestUserCreateDTO;
import com.lrrauseo.auth.dto.response.ResponseAuthUserDTO;
import com.lrrauseo.auth.exception.BadRequestServerException;
import com.lrrauseo.auth.model.AuthUser;
import com.lrrauseo.auth.repository.AuthUserRepository;
import com.lrrauseo.auth.repository.ProfileRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final AuthUserRepository repository;
	private final ProfileRepository profileRepository;
	private final PasswordEncoder passwordEncoder;
	private final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	public String createUser(RequestUserCreateDTO userDTO) {

		var authUser = OBJECT_MAPPER.convertValue(userDTO, AuthUser.class);
		authUser.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		if (authUser.getProfiles().size() > 0)
			profileRepository.saveAll(authUser.getProfiles());
		repository.save(authUser);
		return authUser.getId();
	}

	public ResponseAuthUserDTO getById(String id) {

		AuthUser authUser;
		try {
			authUser = repository.findById(id).orElseThrow(() -> new AccountNotFoundException("Usuario não existe"));

			return OBJECT_MAPPER.convertValue(authUser, ResponseAuthUserDTO.class);
		} catch (AccountNotFoundException e) {
			throw new BadRequestServerException.builder().message(e.getMessage()).throwable(e)
					.status(HttpStatus.BAD_REQUEST).build();
		}
	}
}

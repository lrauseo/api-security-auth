package com.lrrauseo.auth.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.lrrauseo.auth.model.AuthUser;

@EnableMongoRepositories
public interface AuthUserRepository extends MongoRepository<AuthUser, String> {
	Optional<AuthUser> findById(String id);

	Optional<AuthUser> findByEmail(String email);

	Optional<AuthUser> findByUsername(String username);

}

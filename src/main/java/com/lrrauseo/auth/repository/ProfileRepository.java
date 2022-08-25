package com.lrrauseo.auth.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.lrrauseo.auth.model.Profile;

public interface ProfileRepository extends MongoRepository<Profile, String> {

}

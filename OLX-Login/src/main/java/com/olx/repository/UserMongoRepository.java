package com.olx.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.olx.entity.BlacklistedTokensDocument;

public interface UserMongoRepository extends MongoRepository<BlacklistedTokensDocument, Integer> {

	public BlacklistedTokensDocument findByToken(String token);
}

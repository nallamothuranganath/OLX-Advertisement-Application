package com.olx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.olx.entity.BlacklistedTokensDocument;
import com.olx.repository.UserMongoRepository;

@Service
public class UserMongoServiceImpl implements UserMongoService{
	
	@Autowired
	UserMongoRepository userMongoRepository;

	@Override
	public void insertUserToken(String token) {
		
		BlacklistedTokensDocument object = new BlacklistedTokensDocument();
		object.setToken(token);
		this.userMongoRepository.save(object);
		
	}
}

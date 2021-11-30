package com.olx.service;

import org.springframework.security.authentication.BadCredentialsException;

import com.olx.dto.AuthenticationRequest;
import com.olx.dto.User;

public interface UserService {

	public User createUser(User user);
	public String authenticateUser(AuthenticationRequest authenticationRequest) throws BadCredentialsException;
	public boolean validateLoginToken(String token);
	public boolean logOutUser(String token);
	public User getUser(String token);
}

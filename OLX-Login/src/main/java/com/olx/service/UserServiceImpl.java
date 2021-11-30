package com.olx.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.olx.dto.AuthenticationRequest;
import com.olx.dto.User;
import com.olx.entity.BlacklistedTokensDocument;
import com.olx.entity.UserEntity;
import com.olx.exception.CreateUserException;
import com.olx.exception.LogOutUserMongoException;
import com.olx.exception.UserLoggedOutException;
import com.olx.exception.UserNotFoundException;
import com.olx.repository.UserMongoRepository;
import com.olx.repository.UserRepository;
import com.olx.security.JwtUtil;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	ModelMapper modelMapper;

	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtUtil jwtUtil;
	
	@Autowired
	UserMongoRepository userMongoRepository;
	
	@Autowired
	UserMongoService userMongoService;

	private boolean validateClientToken(String token) {

		try {
			jwtUtil.extractUsername(token);
		} catch (Exception e) {
			return false;
		}

		return true;
	}

	private String getUserName(String token) {
		try {
			String username = userDetailsServiceImpl.loadUserByUsername(jwtUtil.extractUsername(token)).getUsername();
			return username;
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public User createUser(User user) {

		try {
			UserEntity userEntity = this.modelMapper.map(user, UserEntity.class);
			userEntity.setActive("true");
			userEntity.setRoles("ROLE_USER");
			userEntity = userRepository.save(userEntity);
			this.modelMapper.typeMap(UserEntity.class, User.class).addMapping(UserEntity::getActive, User::setActive);
			User userObj = this.modelMapper.map(userEntity, User.class);
			return userObj;
		} catch (Exception exc) {
			throw new CreateUserException(exc.getMessage());
		}

	}

	@Override
	public String authenticateUser(AuthenticationRequest authenticationRequest) throws BadCredentialsException {

		try {
			Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUserName(), authenticationRequest.getPassword()));
		} catch (BadCredentialsException err) {
			throw err;
		}

		return jwtUtil.generateTokenByUsername(authenticationRequest.getUserName());
	}

	@Override
	public boolean validateLoginToken(String token) {

		token = token.substring(token.indexOf(" ") + 1);
		boolean isValidToken = validateClientToken(token);
		String databaseUserName = getUserName(token);
		System.out.println("token: "+token);
		System.out.println("isValidToken: "+isValidToken);
		System.out.println("databaseUserName: "+databaseUserName);
		if (isValidToken && databaseUserName != null) {
			try {
				isValidToken = jwtUtil.validateTokenByUserName(token, databaseUserName);
				if(!(isValidToken))
					return false;
			} catch (Exception e) {
				isValidToken = false;
				return isValidToken;
			}
		
		BlacklistedTokensDocument object = userMongoRepository.findByToken(token);
		
		if(object != null) {
		if(object.getToken() != null) {
			throw new UserLoggedOutException();
		}
		else {
			isValidToken = true;
		return isValidToken;
		}
		}
		else {
			isValidToken = true;
		return isValidToken;
		}
		
		}
		else {
			return false;
		}
	}

	@Override
	public boolean logOutUser(String token) {

		token = token.substring(token.indexOf(" ") + 1);
		boolean isValidToken = validateClientToken(token);
		String databaseUserName = getUserName(token);
		if (isValidToken && databaseUserName != null) {
			try {
				isValidToken = jwtUtil.validateTokenByUserName(token, databaseUserName);
			} catch (Exception e) {
				isValidToken = false;
			}
		}

		if (isValidToken) {
			try {
				/*UserEntity userEntity = userRepository.findByUserName(databaseUserName);
				userRepository.delete(userEntity);*/
				this.userMongoService.insertUserToken(token);
				isValidToken = true;
				return isValidToken;
			} catch (Exception err) {
				//isValidToken = false;
				//return isValidToken;
				throw new LogOutUserMongoException(err.getMessage());
			}
		}
		return isValidToken;

	}

	@Override
	public User getUser(String token) {
		
		token = token.substring(token.indexOf(" ") + 1);
		boolean isValidToken = validateClientToken(token);
		String databaseUserName = getUserName(token);
		if (isValidToken && databaseUserName != null) {
			try {
				isValidToken = jwtUtil.validateTokenByUserName(token, databaseUserName);
			} catch (Exception e) {
				isValidToken = false;
			}
		}

		if (isValidToken) {
			
			UserEntity userEntity = userRepository.findByUserName(databaseUserName);						
			User user = this.modelMapper.map(userEntity, User.class);
			this.modelMapper.typeMap(UserEntity.class, User.class).addMapping(UserEntity::getActive, User::setActive);
			
			return user;
			
		}else {
			throw new UserNotFoundException();
		}
	}

}

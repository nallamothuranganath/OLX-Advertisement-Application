package com.olx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.AuthenticationRequest;
import com.olx.dto.User;
import com.olx.security.JwtUtil;
import com.olx.service.UserDetailsServiceImpl;
import com.olx.service.UserService;

@RestController
@RequestMapping(value = "/olx/user")
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping(value = "/", produces = { MediaType.APPLICATION_JSON_VALUE,
			MediaType.APPLICATION_XML_VALUE }, consumes = { MediaType.APPLICATION_JSON_VALUE,
					MediaType.APPLICATION_XML_VALUE })
	public ResponseEntity<User> createUser(@RequestBody User user) {

		User userObj = this.userService.createUser(user);
		return new ResponseEntity<>(userObj, HttpStatus.CREATED);
	}

	@PostMapping(value = "/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> authenticateUser(@RequestBody AuthenticationRequest authenticationRequest) {
		String authenticateUser = null;
		try {
			authenticateUser = this.userService.authenticateUser(authenticationRequest);
		} catch (BadCredentialsException err) {
			return new ResponseEntity<>(err.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(authenticateUser, HttpStatus.OK);
	}

	@GetMapping(value = "/validate/token")
	public ResponseEntity<Boolean> validateLoginToken(@RequestHeader(value = "Authorization") String token) {

		boolean isValidToken = this.userService.validateLoginToken(token);

		if (isValidToken) {
			return new ResponseEntity<>(isValidToken, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(isValidToken, HttpStatus.BAD_REQUEST);
		}

	}
	
	@DeleteMapping(value = "/logout")
	public ResponseEntity<Boolean> logOutUser(@RequestHeader("auth-token") String token){
		
		boolean isUserDeleted = this.userService.logOutUser(token);
		if (isUserDeleted) {
			return new ResponseEntity<>(isUserDeleted, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(isUserDeleted, HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/")
	public ResponseEntity<User> getUser(@RequestHeader("auth-token") String token) {

		User user = this.userService.getUser(token);
	    return new ResponseEntity<>(user, HttpStatus.OK);

	}

}

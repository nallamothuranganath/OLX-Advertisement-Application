package com.olx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class LoginDelegateImpl implements LoginDelegate {

	@Autowired
	RestTemplate restTemplate;

	/*
	 * private RestTemplate restTemplate;
	 * 
	 * @Autowired public LoginDelegateImpl(RestTemplateBuilder restTemplateBuilder)
	 * { restTemplate = restTemplateBuilder.errorHandler(new
	 * RestTemplateResponseErrorHandler()) .build(); }
	 */

	@Override
	@CircuitBreaker(name="TOKEN-VALIDATION-SERVICE", fallbackMethod = "fallBackValidateLogin")
	public Boolean isValidToken(String token) {

		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("Authorization", token);
			HttpEntity entity = new HttpEntity(headers);

			// ResponseEntity<Boolean> result =
			// this.restTemplate.exchange("http://localhost:9001/olx/user/validate/token/",
			ResponseEntity<Boolean> result = this.restTemplate.exchange("http://AUTH-SERVICE/olx/user/validate/token/",
					HttpMethod.GET, entity, Boolean.class);
			return result.getBody();
		} catch (Exception e) {
			return Boolean.FALSE;
		}

		// Boolean result =
		// this.restTemplate.getForObject("http://localhost:9001/olx/user/validate/token/",
		// Boolean.class);
		// return result;
	}
	
	public Boolean fallBackValidateLogin(String token, Exception err) {
		System.out.println("Fallback method called in user login validation rest template: "+err);
		return null;
	}

}

package com.olx.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class MasterDataDelegateImpl implements MasterDataDelegate {

	@Autowired
	RestTemplate restTemplate;

	@Override
	@CircuitBreaker(name="STATUS-FROM-MASTER-DATA-SERVICE", fallbackMethod="fallbackCategoryMasterData")
	public String getCategoryName(int categoryId) {

		/*
		 * boolean status =
		 * restTemplate.getForObject("http://localhost:9002/olx/advertise/category/"+
		 * categoryId, Boolean.class);
		 * 
		 * return status;
		 */

		ResponseEntity<String> response = restTemplate
				.getForEntity("http://API-GATEWAY/olx/masterdata/advertise/category/" + categoryId, String.class);

		return response.getBody();
	}

	@Override
	@CircuitBreaker(name="STATUS-FROM-MASTER-DATA-SERVICE", fallbackMethod="fallbackCategoryMasterData")
	public String getStatusById(int statusId) {

		//System.out.println("MasterDataDelegateImpl statusId:"+statusId);
		ResponseEntity<String> response = restTemplate
				.getForEntity("http://API-GATEWAY/olx/masterdata/advertise/status/" + statusId, String.class);

		return response.getBody();
	}
	
	public String fallbackCategoryMasterData(int id, Exception err) {
		
		System.out.println("Fall back method called: "+err);
		return null;
	}

}

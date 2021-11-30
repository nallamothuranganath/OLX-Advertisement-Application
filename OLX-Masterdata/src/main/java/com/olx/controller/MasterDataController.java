package com.olx.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.olx.dto.AdvStatus;
import com.olx.dto.Category;
import com.olx.service.MasterDataService;

import io.swagger.annotations.ApiOperation;

@RestController
//@CrossOrigin(origins = "*")
@RequestMapping(value = "/olx/masterdata")
public class MasterDataController {

	// static List<Category> categories = new ArrayList<>();
	// static List<AdvStatus> advStatusList = new ArrayList<>();

	@Autowired
	MasterDataService masterDataService;

	@GetMapping(value = "/advertise/category", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "This REST endpoint shows all Masterdata Categories.")
	public ResponseEntity<List<Category>> getAllCategories() {
		
		List<Category> categoriesList = this.masterDataService.getAllCategories();

		return new ResponseEntity(categoriesList, HttpStatus.OK);
	}

	@GetMapping(value = "/advertise/status", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "This REST endpoint shows all Masterdata advertise status.")
	public ResponseEntity<List<AdvStatus>> getAllAdvertiseStatus() {

		List<AdvStatus> advertiseList = this.masterDataService.getAllAdvertiseStatus();
		return new ResponseEntity(advertiseList, HttpStatus.FOUND);
	}

	//This below method is called from OLX Advertise Service using RestTemplate
	@GetMapping(value = "/advertise/category/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "This REST endpoint shows catgeory name for given category id.")
	public String getCategoryName(@PathVariable("id") int categoryId) {
		return this.masterDataService.getCategoryName(categoryId);
	}
	
	@GetMapping(value = "/advertise/status/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public String getStatusById(@PathVariable("id") int statusId) {
		return this.masterDataService.getStatusById(statusId);
	}
}

package com.olx.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.olx.dto.Advertise;
import com.olx.exception.InvalidCategoryIdException;
import com.olx.service.AdvertisesService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/olx/advertise")
//@CrossOrigin(origins = "*")
public class AdvertisesController {

	@Autowired
	AdvertisesService advertisesService;
	
	@ExceptionHandler(value= {InvalidCategoryIdException.class})
	public ResponseEntity<Object> handleInvalidStockIdException(RuntimeException exception, WebRequest request){
		
		return new ResponseEntity<Object> ("Invalid Category Id.",HttpStatus.BAD_REQUEST);
		
	}

	@PostMapping(value = "/", consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "This REST endpoint creates new advertise.")
	public ResponseEntity<Advertise> createNewAdvertise(@RequestBody Advertise advertise,
			@RequestHeader("auth-token") String advCreatetoken) {
		
		Advertise newAdvertise = this.advertisesService.createNewAdvertise(advertise, advCreatetoken);	
		return new ResponseEntity(newAdvertise, HttpStatus.CREATED);
	}

	@PutMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "This REST endpoint updates an advertise.")
	public ResponseEntity<Advertise> updateAdvertise(@PathVariable("id") int advertiseId, @RequestBody Advertise advertise,
			@RequestHeader("auth-token") String advUpdtoken) {
		
		Advertise updateAdvertise = this.advertisesService.updateAdvertise(advertiseId, advertise, advUpdtoken);
		
		return new ResponseEntity(updateAdvertise, HttpStatus.OK);
	}

	@GetMapping(value = "user/advertise", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "This REST endpoint shows user advertises.")
	public ResponseEntity<List<Advertise>> getUserAdvertises(@RequestHeader("auth-token") String userAuthtoken) {

		List<Advertise> userAdvertises = this.advertisesService.getUserAdvertises(userAuthtoken);
		
		return new ResponseEntity(userAdvertises, HttpStatus.OK);
	}

	@GetMapping(value = "/user/advertise/{postId}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "This REST endpoint shows user advertise for the given id.")
	public ResponseEntity<Advertise> getUserAdvertiseById(@PathVariable("postId") int id,
			@RequestHeader("auth-token") String userAuthtoken) {

		Advertise userAdvertiseById = this.advertisesService.getUserAdvertiseById(id, userAuthtoken);
		
		return new ResponseEntity(userAdvertiseById, HttpStatus.OK);
	}

	@DeleteMapping(value = "/user/advertise/{postId}")
	@ApiOperation(value = "This REST endpoint shows deletes an advertise for the given id.")
	public ResponseEntity<Boolean> deleteUserAdretiseById(@PathVariable("postId") int id,
			@RequestHeader("auth-token") String userAuthtoken) {
		boolean result = this.advertisesService.deleteUserAdretiseById(id, userAuthtoken);
		return new ResponseEntity(result, HttpStatus.OK);
	}

	@GetMapping(value = "/search/filtercriteria", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "This REST endpoint shows advertises for the given filter criteria.")
	public ResponseEntity<List<Advertise>> searchAdvertiseByCriteria(
			@RequestParam(value = "category", required = false) Integer categoryId,
			@RequestParam(value = "postedBy", required = false) String postedBy,
		    @RequestParam(value = "dateCondition", required = false) String dateCondition, //need to check
			@RequestParam(value = "onDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate onDate,
			@RequestParam(value = "fromDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fromDate,
			@RequestParam(value = "toDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate toDate,
			@RequestParam(value = "sortBy", required = false) String sortBy,
			@RequestParam(value = "startIndex", required = false) Integer startIndex,
			@RequestParam(value = "records", required = false) Integer records) {
		
		List<Advertise> searchAdvertiseByCriteria = this.advertisesService.searchAdvertiseByCriteria(categoryId, postedBy,
				 dateCondition,
				onDate, fromDate, toDate, sortBy, startIndex, records);

		return new ResponseEntity(searchAdvertiseByCriteria, HttpStatus.OK);

	}

	@GetMapping(value = "/search/{searchText}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "This REST endpoint shows advertises for the given search text.")
	public ResponseEntity<List<Advertise>> getAdvertiseBySearchText(@PathVariable("searchText") String searchText) {

		List<Advertise> advertiseBySearchText = this.advertisesService.getAdvertiseBySearchText(searchText);
		
		return new ResponseEntity(advertiseBySearchText, HttpStatus.OK);
	}

	@GetMapping(value = "/{postId}", produces = {MediaType.APPLICATION_JSON_VALUE,MediaType.APPLICATION_XML_VALUE})
	@ApiOperation(value = "This REST endpoint shows advertise for the given id.")
	public ResponseEntity<Advertise> getAdvertiseByPostId(@PathVariable("postId") int id,
			@RequestHeader("auth-token") String userAuthtoken) {
		
		Advertise advertiseByPostId = this.advertisesService.getAdvertiseByPostId(id, userAuthtoken);
		
		return new ResponseEntity<>(advertiseByPostId, HttpStatus.OK);
	}
}

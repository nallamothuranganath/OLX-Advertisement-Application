package com.olx.service;

import java.time.LocalDate;
import java.util.List;

import com.olx.dto.Advertise;

public interface AdvertisesService {

	public Advertise createNewAdvertise(Advertise adverise, String advCreatetoken);

	public Advertise updateAdvertise(int advertiseId, Advertise adverise, String advUpdtoken);

	public List<Advertise> getUserAdvertises(String userAuthtoken);

	public Advertise getUserAdvertiseById(int id, String userAuthtoken);

	public boolean deleteUserAdretiseById(int id, String userAuthtoken);

	public List<Advertise> searchAdvertiseByCriteria(Integer categoryId
			, String postedBy, 
			String dateCondition,
			LocalDate onDate
			, LocalDate fromDate, LocalDate toDate
			, String sortBy
			, Integer startIndex, Integer records
			);

	public List<Advertise> getAdvertiseBySearchText(String searchText);

	public Advertise getAdvertiseByPostId(int id, String userAuthtoken);
	
}

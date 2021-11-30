package com.olx.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.olx.dto.Advertise;
import com.olx.dto.SearchCriteria;
import com.olx.dto.SearchOperation;
import com.olx.entity.AdvertiseEntity;
import com.olx.exception.InavlidDateRangeException;
import com.olx.exception.InvalidAdvertiseIdException;
import com.olx.exception.InvalidCategoryIdException;
import com.olx.exception.InvalidDateConditionException;
import com.olx.exception.InvalidDateConditionURIException;
import com.olx.exception.InvalidFilterCriteriaURIException;
import com.olx.exception.InvalidStatusIdException;
import com.olx.exception.InvalidUserAdvertiseIdException;
import com.olx.exception.InvalidUserException;
import com.olx.exception.NoAdvertiseFoundException;
import com.olx.repository.AdvertiseRepository;
import com.olx.security.JwtUtil;

@Service
public class AdvertisesServiceImpl implements AdvertisesService {

	static int advId;
	@Autowired
	ModelMapper modelMapper;

	@Autowired
	AdvertiseRepository advertiseRepository;
	
	@Autowired
	MasterDataDelegate masterDataDelegate;

	@Autowired
	LoginDelegate loginDelegate;
	
	@Autowired
	JwtUtil jwtUtil;
	
	
	
	private List<Advertise> getAdvertiseDtoList(List<AdvertiseEntity> advertiseList) {
		
		if(advertiseList.isEmpty()) {
		
			throw new NoAdvertiseFoundException("No Advertises found.");
		}
		else {
		List<Advertise> advertiseDtoList = new ArrayList<>();
		for (AdvertiseEntity advertiseEntity : advertiseList) {
			Advertise advertiseDto = this.modelMapper.map(advertiseEntity, Advertise.class);
			//advertiseDto.setStatusName(advertiseRepository.getStatusById(advertiseEntity.getStatus()));  //need to change to rest template
			advertiseDto.setStatusName(masterDataDelegate.getStatusById(advertiseEntity.getStatus()));
			advertiseDto.setCategory(masterDataDelegate.getCategoryName(advertiseEntity.getCategoryId()));
			//advertiseEntity.setActive("0");
			advertiseDtoList.add(advertiseDto);
		}
		return advertiseDtoList;
		}
	}
	
	private Advertise getAdvertiseDto(Advertise advertise, String advCreatetoken) {
	
		int categoryId = advertise.getCategoryId();
		if(masterDataDelegate.getCategoryName(categoryId) != null) {
			advertise.setCreated_date(LocalDate.now());
			advertise.setModified_date(LocalDate.now());
			//advertise.setUsername(advCreatetoken);
			String clinetUserName = getUserName(advCreatetoken);
			System.out.println("clinetUserName: "+clinetUserName);
			advertise.setUsername(clinetUserName);
			System.out.println("advertise.getStatus(): "+advertise.getStatus());
			if(advertise.getStatus() == 0) {
			advertise.setStatus(2); 
			}
			else {
				if(masterDataDelegate.getStatusById(advertise.getStatus()) != null) { //this is to handle the case when PUT request send invalid request id. In POST we don't send request id.
				advertise.setStatus(advertise.getStatus());	
				}
				else {
					throw new InvalidStatusIdException(advertise.getStatus());
				}
			}
			
			AdvertiseEntity advertiseEntity = this.modelMapper.map(advertise, AdvertiseEntity.class);
			advertiseEntity.setPosted_by(clinetUserName);
			advertiseEntity.setActive("0");
			advertiseEntity = this.advertiseRepository.save(advertiseEntity);

			Advertise advertiseDto = this.modelMapper.map(advertiseEntity, Advertise.class);
			
			String categoryName = masterDataDelegate.getCategoryName(categoryId);
			advertiseDto.setCategory(categoryName);
			//advertiseDto.setStatusName(advertiseRepository.getStatusById(advertiseDto.getStatus())); //need to change to rest template
			System.out.println("advertise.getStatus(): "+advertise.getStatus());
			advertiseDto.setStatusName(masterDataDelegate.getStatusById(advertise.getStatus()));
			return advertiseDto;
		}	
		else {
			throw new InvalidCategoryIdException(categoryId);
		}
	}
	
	private String getUserName(String advCreatetoken) {
		return jwtUtil.extractUsername(advCreatetoken.substring(7, advCreatetoken.length()));
	}

	@Override
	public Advertise createNewAdvertise(Advertise advertise, String advCreatetoken) {
		if(!loginDelegate.isValidToken(advCreatetoken)) {
			throw new InvalidUserException(advCreatetoken);
		}
		else {
		return getAdvertiseDto(advertise, advCreatetoken);
		}
	}

	@Override
	public Advertise updateAdvertise(int advertiseId, Advertise advertise, String advUpdtoken) {
		if(!loginDelegate.isValidToken(advUpdtoken)) {
			throw new InvalidUserException(advUpdtoken);
		}
		else {
		Optional<AdvertiseEntity> opAdvertiseEntity = advertiseRepository.findById(advertiseId);
		if (opAdvertiseEntity.isPresent()) {
			if(opAdvertiseEntity.get().getUsername().equalsIgnoreCase(getUserName(advUpdtoken))) {
			advertise.setId(opAdvertiseEntity.get().getId());
			return getAdvertiseDto(advertise, advUpdtoken);
			}
			else {
				throw new InvalidUserAdvertiseIdException();
			}
		}
		
		else {		
			throw new InvalidAdvertiseIdException(advertiseId);
		}
		}
		
	}

	@Override
	public List<Advertise> getUserAdvertises(String userAuthtoken) {
		if(!loginDelegate.isValidToken(userAuthtoken)) {
			throw new InvalidUserException(userAuthtoken);
		}
		else {
		List<AdvertiseEntity> advertiseEntities = advertiseRepository.findByUsername(getUserName(userAuthtoken));
		return getAdvertiseDtoList(advertiseEntities);
		}
	}

	@Override
	public Advertise getUserAdvertiseById(int id, String userAuthtoken) {

		if (!loginDelegate.isValidToken(userAuthtoken)) {
			throw new InvalidUserException(userAuthtoken);
		} else {

			Optional<AdvertiseEntity> opAdvertiseEntity = advertiseRepository.findById(id);
			if (opAdvertiseEntity.isPresent()) {
				AdvertiseEntity advertiseEntity = advertiseRepository.findByIdAndUsername(id,
						getUserName(userAuthtoken));
				if (advertiseEntity != null) {
					Advertise advertise = modelMapper.map(advertiseEntity, Advertise.class);
					advertise.setStatusName(masterDataDelegate.getStatusById(advertiseEntity.getStatus()));
					return advertise;
				} else {
					throw new InvalidUserAdvertiseIdException();
				}

			} else {

				throw new InvalidAdvertiseIdException(id);
			}
		}
	}

	@Override
	public boolean deleteUserAdretiseById(int id, String userAuthtoken) {

		if(!loginDelegate.isValidToken(userAuthtoken)) {
			throw new InvalidUserException(userAuthtoken);
		}
		else {
			Optional<AdvertiseEntity> opAdvertiseEntity = advertiseRepository.findById(id);
			if (opAdvertiseEntity.isPresent()) {	
		AdvertiseEntity advertiseEntity = advertiseRepository.findByIdAndUsername(id, getUserName(userAuthtoken));

		if (advertiseEntity != null) {
			advertiseRepository.delete(advertiseEntity);
			return true;
		} else {
			throw new InvalidUserAdvertiseIdException();
		}
			}
		else {
			
			throw new InvalidAdvertiseIdException(id);
		}
		}
	}

	@Override
	public List<Advertise> searchAdvertiseByCriteria(Integer categoryId, String postedBy,
			 String dateCondition,
			LocalDate onDate, LocalDate fromDate, LocalDate toDate, String sortBy, Integer startIndex,
			Integer records) {

		System.out.println("categoryId: "+categoryId);
		System.out.println("postedBy: "+postedBy);
		System.out.println("dateCondition: "+dateCondition);
		System.out.println("onDate: "+onDate);
		System.out.println("fromDate: "+fromDate);
		System.out.println("toDate: "+toDate);
		System.out.println("sortBy: "+sortBy);
		System.out.println("startIndex: "+startIndex);
		
		if(categoryId == null && postedBy == null && dateCondition == null && onDate == null && fromDate == null && toDate == null && sortBy == null && startIndex == null) {
			throw new InvalidFilterCriteriaURIException("Filter Criteria URI should send values for any of the fields in (category, postedBy, dateCondition, onDate, fromDate, toDate, sortBy, startIndex)");
			
		}
		
		GenericSpesification<AdvertiseEntity> genericSpesification = new GenericSpesification<>();

		List<AdvertiseEntity> advEntityList = null;
		PageRequest pageRequest = null;
		
		if(categoryId != null) {
		genericSpesification.add(new SearchCriteria("categoryId", categoryId, SearchOperation.EQUAL));
		}
		
		if(postedBy != null) {
		genericSpesification.add(new SearchCriteria("posted_by", postedBy, SearchOperation.EQUAL));
		}
		
		if(dateCondition != null) {
			
			if(dateCondition.equalsIgnoreCase("equals")) {
				
				if(onDate != null)
				{
				genericSpesification.add(new SearchCriteria("created_date", onDate, SearchOperation.EQUAL));
				}
				else {
					throw new InvalidDateConditionException("dateCondition should send onDate when condition is equals");	
				}
			}
			
			else if(dateCondition.equalsIgnoreCase("lessthan")) {
				if(fromDate != null)
				{
				genericSpesification.add(new SearchCriteria("created_date", fromDate, SearchOperation.LESS_THAN_DATE));
				}
				else {
					throw new InvalidDateConditionException("dateCondition should send fromDate when condition is lessthan");	
				}
			}
			else if(dateCondition.equalsIgnoreCase("greaterthan")) {
				if(fromDate != null)
				{
				genericSpesification.add(new SearchCriteria("created_date", fromDate, SearchOperation.GREATER_THAN_DATE));
				}
				else {
					throw new InvalidDateConditionException("dateCondition should send fromDate when condition is greaterthan");	
				}
			}
			else if(dateCondition.equalsIgnoreCase("between")){
				
				if(fromDate != null && toDate != null)
				{
					if(toDate.isBefore(fromDate)) {
						throw new InavlidDateRangeException(fromDate, toDate);
					}else {
					//genericSpesification.add(new SearchCriteria("created_date", fromDate, SearchOperation.GREATER_THAN_DATE));
					//genericSpesification.add(new SearchCriteria("created_date", toDate, SearchOperation.LESS_THAN_DATE));
						genericSpesification.add(new SearchCriteria("created_date", fromDate, SearchOperation.GREATER_THAN_EQUAL_DATE));
						genericSpesification.add(new SearchCriteria("created_date", toDate, SearchOperation.LESS_THAN_EQUAL_DATE));	
					}
				}
				
				else {
					throw new InvalidDateConditionException("dateCondition should send fromDate and toDate when condition is between");	
				}
			}
			
			else {
				throw new InvalidDateConditionURIException("dateCondition should be equals or lessthan or greaterthan or between");
			}
			
		}
		
		if(dateCondition == null && onDate != null) {
		genericSpesification.add(new SearchCriteria("created_date", onDate, SearchOperation.EQUAL));
		}

		if (dateCondition == null && fromDate != null && toDate != null) {
			if(toDate.isBefore(fromDate)) {
				throw new InavlidDateRangeException(fromDate, toDate);
			}else {
			genericSpesification.add(new SearchCriteria("created_date", fromDate, SearchOperation.GREATER_THAN_EQUAL_DATE));
			genericSpesification.add(new SearchCriteria("created_date", toDate, SearchOperation.LESS_THAN_EQUAL_DATE));
			}
		} else if (dateCondition == null && fromDate != null) {
			genericSpesification.add(new SearchCriteria("created_date", fromDate, SearchOperation.GREATER_THAN_EQUAL_DATE));
		} else if (dateCondition == null && toDate != null) {
			genericSpesification.add(new SearchCriteria("created_date", toDate, SearchOperation.LESS_THAN_EQUAL_DATE));
		}
		if (startIndex != null && records != null && sortBy != null) {
			pageRequest = PageRequest.of(startIndex, records, Direction.DESC, sortBy);
			advEntityList = advertiseRepository.findAll(genericSpesification, pageRequest).toList();
		} 
		else if(startIndex != null && records != null && sortBy == null){
			pageRequest = PageRequest.of(startIndex, records);
			advEntityList = advertiseRepository.findAll(genericSpesification, pageRequest).toList();
		}
		else if (sortBy != null) {
			Sort by = Sort.by(new Sort.Order(Direction.DESC, sortBy));
			advEntityList = advertiseRepository.findAll(genericSpesification, by);
		} else {
			advEntityList = advertiseRepository.findAll(genericSpesification);
		}

		return getAdvertiseDtoList(advEntityList);

	}

	@Override
	public List<Advertise> getAdvertiseBySearchText(String searchText) {

		List<AdvertiseEntity> advEntityList = advertiseRepository.findAdvBySearch(searchText);
		return getAdvertiseDtoList(advEntityList);
	}

	@Override
	public Advertise getAdvertiseByPostId(int id, String userAuthtoken) {
		
		if(!loginDelegate.isValidToken(userAuthtoken)) {
			throw new InvalidUserException(userAuthtoken);
		}
		else {

		Optional<AdvertiseEntity> findByPostId = advertiseRepository.findById(id);
		
		if(findByPostId.isPresent()){
			
		AdvertiseEntity advEntity = findByPostId.get();
		Advertise advertise = this.modelMapper.map(advEntity, Advertise.class);
		//advertise.setStatusName(advertiseRepository.getStatusById(advEntity.getStatus()));
		advertise.setStatusName(masterDataDelegate.getStatusById(advEntity.getStatus()));
		String categoryName = masterDataDelegate.getCategoryName(advEntity.getCategoryId())
				//advertiseRepository.getCategyById(advEntity.getCategoryId())
				;
		System.out.println("categoryName: " + categoryName);
		advertise.setCategory(categoryName);
		return advertise;
		}
		else {
			throw new InvalidAdvertiseIdException(id);	
		}
		}
	}

}

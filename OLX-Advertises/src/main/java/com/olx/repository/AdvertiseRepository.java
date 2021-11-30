package com.olx.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.olx.entity.AdvertiseEntity;

public interface AdvertiseRepository extends JpaRepository<AdvertiseEntity, Integer>, JpaSpecificationExecutor<AdvertiseEntity> {

	//@Query(value="select name from `olx-masterdata`.categories where id = :categoryId", nativeQuery=true)
	//public String getCategyById(@Param("categoryId")int categoryId);
	
	//@Query(value="select status from `olx-masterdata`.adv_status where id = :statusId", nativeQuery=true)
	//public String getStatusById(@Param("statusId")int statusId);
	
	public List<AdvertiseEntity> findByUsername(String userName);
	
	public AdvertiseEntity findByIdAndUsername(int id, String userName);
	
	@Query("SELECT adv FROM AdvertiseEntity adv WHERE adv.title LIKE %:value% OR adv.description LIKE %:value%")
	public List<AdvertiseEntity> findAdvBySearch(@Param("value")String value);
	
	@Query("SELECT COUNT(adv) FROM AdvertiseEntity adv")
	public String findNoOfAdvertises();
	
	@Query("SELECT COUNT(adv) FROM AdvertiseEntity adv WHERE adv.status=2")
	public String findOpenAdvertises();
	
	@Query("SELECT COUNT(adv) FROM AdvertiseEntity adv WHERE adv.status=1")
	public String findClosedAdvertises();
	
	@Query("SELECT username, COUNT(adv) FROM AdvertiseEntity adv GROUP BY adv.username")
	//public List<Map<String, Integer>> findFirstUserAdvertisesCnt();
	//public Map<String, Integer> findFirstUserAdvertisesCnt();
	public List<Object[]> findFirstUserAdvertisesCnt();
}

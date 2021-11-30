package com.olx.service;

import java.util.List;

import com.olx.dto.AdvStatus;
import com.olx.dto.Category;

public interface MasterDataService {
	
	public List<Category> getAllCategories();
	
	public List<AdvStatus> getAllAdvertiseStatus();

	public String getCategoryName(int categoryId);
	
	public String getStatusById(int statusId);
}

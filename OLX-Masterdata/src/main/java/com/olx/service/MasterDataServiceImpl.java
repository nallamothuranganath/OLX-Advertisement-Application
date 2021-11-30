package com.olx.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olx.dto.AdvStatus;
import com.olx.dto.Category;
import com.olx.entity.AdvStatusEntity;
import com.olx.entity.CategoryEntity;
import com.olx.repository.CategoryRepository;
import com.olx.repository.StatusRepository;

@Service
public class MasterDataServiceImpl implements MasterDataService {
	
	@Autowired
	ModelMapper modelMapper;
	
	@Autowired
	CategoryRepository categoryRepository;
	
	@Autowired
	StatusRepository statusRepository;

	@Override
	public List<Category> getAllCategories() {
		
		List<CategoryEntity> categoryEntities = categoryRepository.findAll();
		List<Category> categoryDtoList = new ArrayList<>();
		
		TypeMap<CategoryEntity, Category> typeMap = this.modelMapper.typeMap(CategoryEntity.class, Category.class);
		
		typeMap.addMappings((mapper) -> {mapper.map(source -> source.getDesc(), Category::setDescription);});
		
		for(CategoryEntity categoryEntity : categoryEntities) {
			Category categoryDto = this.modelMapper.map(categoryEntity, Category.class);
			categoryDtoList.add(categoryDto);
		}
		
		return categoryDtoList;
	}

	@Override
	public List<AdvStatus> getAllAdvertiseStatus() {
		
		List<AdvStatusEntity> advStatusEntities = statusRepository.findAll();
		List<AdvStatus> advStatusDtoList = new ArrayList<>();
		
		TypeMap<AdvStatusEntity, AdvStatus> typeMap = modelMapper.typeMap(AdvStatusEntity.class, AdvStatus.class);
		
		//typeMap.addMappings((mapper) -> mapper.map(source -> source.getStatus(), AdvStatus::setStatus)); //not required here as dto and entity names are matching
		
		for(AdvStatusEntity advStatusEntity : advStatusEntities) {
			AdvStatus advStatusDto = this.modelMapper.map(advStatusEntity, AdvStatus.class);
			advStatusDtoList.add(advStatusDto);
		}
		
		return advStatusDtoList;
	}
	
	public String getCategoryById(int categoryId) {

		CategoryEntity categoryEntity = categoryRepository.findById(categoryId).get();
		return categoryEntity.getCategory();
	}

	@Override
	public String getCategoryName(int categoryId) {
		return categoryRepository.findById(categoryId).get().getCategory();
	}

	@Override
	public String getStatusById(int statusId) {
		
		return statusRepository.findById(statusId).get().getStatus();
	}

}

package com.olx.utils;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.olx.dto.Category;
import com.olx.entity.CategoryEntity;

@Service
public class MasterDataConverter {
	
	//@Autowired
	//ModelMapper modelMapper;
	
	public Category convertCategoryEntityIntoCategoryDto(CategoryEntity categoryEntity) {
		
		/*
		Category categoryDto = new Category();
		categoryDto.setId(categoryEntity.getId());
		categoryDto.setCategory(categoryEntity.getCategory());
		categoryDto.setDescription(categoryEntity.getDesc());
		*/
		
		//Category categoryDto = modelMapper
	
		return null;
	}

}

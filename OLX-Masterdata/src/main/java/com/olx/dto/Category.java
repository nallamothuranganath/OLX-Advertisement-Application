package com.olx.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Category DTO holding different advertise categories")
public class Category {

	@ApiModelProperty(value="Unique identifier for the category")
	private int id;
	@ApiModelProperty(value="category name")
	private String category;
	@ApiModelProperty(value="category description")
	private String description;
}

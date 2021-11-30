package com.olx.dto;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@JsonPropertyOrder({ "id", "title", "price", "category", "description", "username", "created_date", "modified_date",
		"statusName" })
@ApiModel(value = "Advertise DTO holding different advertises")
public class Advertise {

	@ApiModelProperty(value = "Unique identifier for the advertise")
	private int id;
	@ApiModelProperty(value = "Advertise title")
	private String title;
	@ApiModelProperty(value = "Advertise category id")
	private int categoryId;
	@ApiModelProperty(value = "Advertise status id")
	@JsonProperty("statusId")
	private int status; // need to check this value is string in response - value: OPEN
	@ApiModelProperty(value = "Advertise price")
	private double price;
	@ApiModelProperty(value = "Advertise description")
	private String description;
	// private blob photo; --what is the javatype for blob
	@ApiModelProperty(value = "Advertise creation date")
	private LocalDate created_date;
	@ApiModelProperty(value = "Advertise modified date")
	private LocalDate modified_date;
	// private Active active; //is this correct way of defining enum
	// private String posted_by;
	@ApiModelProperty(value = "Advertise created by user")
	private String username;

	// Adding below additional fields as status and category are ids
	// @JsonProperty("categoryName")
	private String category;
	@JsonProperty("status")
	private String statusName;

	// @JsonIgnore
	public int getCategoryId() {
		return categoryId;
	}

	// @JsonIgnore
	public int getStatus() {
		return status;
	}

}

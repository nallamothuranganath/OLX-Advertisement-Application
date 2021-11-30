package com.olx.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Advertise Status DTO holding advertises status")
public class AdvStatus {

	@ApiModelProperty(value="Unique identifier for the status")
	private int id;
	@ApiModelProperty(value="Advertise status")
	private String status;
}

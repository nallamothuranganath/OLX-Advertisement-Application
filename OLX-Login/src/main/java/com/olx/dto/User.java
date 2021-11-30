package com.olx.dto;

import lombok.Data;

@Data
public class User {
	
	private int id;
	private String userName;
	private String password;
	private String roles;
	private boolean active;
	private String firstName;
	private String lastName;
	
	public void setActive(boolean active) {
		//this.active = Boolean.valueOf(active);
		this.active = active;
	}
}

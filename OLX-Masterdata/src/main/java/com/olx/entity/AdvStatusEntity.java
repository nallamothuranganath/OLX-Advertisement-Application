package com.olx.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "adv_status")
public class AdvStatusEntity {

	@Id
	@Column(name = "id")
	private int id;
	
	@Column(name = "status")
	private String status;
	
	public AdvStatusEntity() {
		
	}

	public AdvStatusEntity(int id, String status) {
		this.id = id;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "AdvStatusEntity [id=" + id + ", status=" + status + "]";
	}
	
	
}

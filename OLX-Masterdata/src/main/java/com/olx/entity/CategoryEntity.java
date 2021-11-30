package com.olx.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categories")
public class CategoryEntity {
	
	@Id
	@Column
	private int id;
	
	@Column(name = "name")
	private String category;
	
	@Column(name = "description")
	private String desc;
	
	public CategoryEntity() {
		
	}

	public CategoryEntity(int id, String category, String desc) {
		super();
		this.id = id;
		this.category = category;
		this.desc = desc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "CategoryEntity [id=" + id + ", category=" + category + ", desc=" + desc + "]";
	}
	
	

}

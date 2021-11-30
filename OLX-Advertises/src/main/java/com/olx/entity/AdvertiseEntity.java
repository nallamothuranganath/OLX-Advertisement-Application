package com.olx.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "advertises")
//this entity class is created for Advertise POJO
public class AdvertiseEntity {

	@Id
	@Column(name = "id")
	@GeneratedValue
	//(strategy = GenerationType.AUTO)
	private int id;

	@Column(name = "title")
	private String title;
		
	@Column(name = "category")
	private int categoryId;

	@Column(name = "status")
	private int status; // need to check this value is string in response - value: OPEN

	@Column(name = "price")
	private double price;

	@Column(name = "description")
	private String description;

	@Column(name = "photo")
	private byte[] photo; // blob in the DB

	@Column(name = "created_date")
	@CreatedDate
	private LocalDate created_date;

	@Column(name = "modified_date")
	//@LastModifiedDate
	private LocalDate modified_date;

	@Column(name = "active")
	private String active; // enum('0','1') in the DB that means only 0 and 1 values are allowed in this
							// field

	@Column(name = "posted_by")
	private String posted_by;

	@Column(name = "username")
	private String username;
	
	public AdvertiseEntity() {
		
	}

	public AdvertiseEntity(int id, String title, int category, int status, double price, String description,
			LocalDate created_date, LocalDate modified_date, String username) {
		this.id = id;
		this.title = title;
		this.categoryId = category;
		this.status = status;
		this.price = price;
		this.description = description;
		this.created_date = created_date;
		this.modified_date = modified_date;
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	//@JsonIgnore
	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int category) {
		this.categoryId = category;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public LocalDate getCreated_date() {
		return created_date;
	}

	public void setCreated_date(LocalDate created_date) {
		this.created_date = created_date;
	}

	public LocalDate getModified_date() {
		return modified_date;
	}

	public void setModified_date(LocalDate modified_date) {
		this.modified_date = modified_date;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getPosted_by() {
		return posted_by;
	}

	public void setPosted_by(String posted_by) {
		this.posted_by = posted_by;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "AdvertiseEntity [id=" + id + ", title=" + title + ", category=" + categoryId + ", status=" + status
				+ ", price=" + price + ", description=" + description + ", created_date=" + created_date
				+ ", modified_date=" + modified_date + ", active=" + active + ", posted_by=" + posted_by + ", username="
				+ username + "]";
	}


	

}

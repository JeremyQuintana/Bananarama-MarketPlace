package com.sept.rest.webservices.restfulwebservices.post;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class PostSubmission {
	private String description;
	private String title;
	private String price;
	private String category;
	private String photo;
	
	public PostSubmission(String title, String description, String price, String category, String photo) {
		this.description = description;
		this.title = title;
		this.price = price;
		this.category = category;
		this.photo = photo;
	}
	
	public String getDescription() {
		return description;
	}
	public String getTitle() {
		return title;
	}
	public String getPrice() {
		return price;
	}
	public String getCategory() {
		return category;
	}
	public String getPhoto() {
		return photo;
	}
	
}

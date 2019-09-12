package com.sept.rest.webservices.restfulwebservices.todo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PostItem {
	private String description;
	private String title;
	private String price;
	private String category;	// temporary change to array
	private String photo; // temporary change type
	@Id
	int id;
	
	public PostItem(String description, String title, String price, String category, String photo)
	{
		this.description = description;
		this.title = title;
		this.price = price;
		this.category = category;
		this.photo = photo;
	}
	public String toString() {
		return description + " " + title + " " + price + " " + category + " " + photo;
	}

}

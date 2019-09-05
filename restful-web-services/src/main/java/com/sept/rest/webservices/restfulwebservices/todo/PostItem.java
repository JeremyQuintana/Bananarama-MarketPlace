package com.sept.rest.webservices.restfulwebservices.todo;

public class PostItem {
	private String description;
	private String name;
	private int cost;
	private String catagory;	// temporary change to array
	private String photo; // temporary change type
	
	public void print() {
		System.out.println(this.description + " " + this.name + " " + this.cost + " " + this.catagory + " " + this.photo);
	}

	//need getters for use of request body turning json into java object
	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public int getCost() {
		return cost;
	}
	
	public String getCatagory() {
		return catagory;
	}

	public String getPhoto() {
		return photo;
	}
}

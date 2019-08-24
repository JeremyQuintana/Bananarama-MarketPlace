package com.sept.rest.webservices.restfulwebservices.todo;

public class PostItem {
	private String description;
	private String name;
	private String cost;
	
	public void print() {
		System.out.println(this.description + " " + this.name + " " + this.cost);
	}

	//need getters for use of request body turning json into java object
	public String getDescription() {
		return description;
	}

	public String getName() {
		return name;
	}

	public String getCost() {
		return cost;
	}
}

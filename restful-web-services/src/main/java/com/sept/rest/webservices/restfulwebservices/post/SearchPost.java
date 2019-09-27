package com.sept.rest.webservices.restfulwebservices.post;



public class SearchPost {
	
	// variables need to be above constructor for json
	/*to change*/
	private String description;
	private String category;
	
	
	public void print() {
		System.out.println(this.description + " " + this.category+ " ");
	}
	

	public String getdescription(){
		return description;
	}

	public String getcategory() {
		return category;
	}
}
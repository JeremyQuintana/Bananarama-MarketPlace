package com.sept.rest.webservices.restfulwebservices.post;



public class SearchPost {
	
	// variables need to be above constructor for json
	/*to change*/
	private String description;
	private String category;
	private String sort;
	
	
	
	public void print() {
		System.out.println(this.description + " " + this.category+ " ");
	}
	public void printsort() {
		System.out.println(this.sort);
	}
	
	public String getsort() {
		return sort;
	}

	public String getdescription(){
		return description;
	}

	public String getcategory() {
		return category;
	}
}
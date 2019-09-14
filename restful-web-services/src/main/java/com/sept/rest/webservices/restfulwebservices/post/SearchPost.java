package com.sept.rest.webservices.restfulwebservices.post;



public class SearchPost {
	
	// variables need to be above constructor for json
	/*to change*/
	private String SearchWord;
	private String Category;
	
	
	public void print() {
		System.out.println(this.SearchWord + " " + this.Category+ " ");
	}
	

	public String getSearchWord(){
		return SearchWord;
	}

	public String getCategory() {
		return Category;
	}
}

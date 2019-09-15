package com.sept.rest.webservices.restfulwebservices.post;



public class SearchPost {
	
	// variables need to be above constructor for json
	/*to change*/
	private String search_words;
	private String category;
	
	
	public void print() {
		System.out.println(this.search_words + " " + this.category+ " ");
	}
	

	public String getsearch_words(){
		return search_words;
	}

	public String getcategory() {
		return category;
	}
}
package com.sept.rest.webservices.restfulwebservices.post;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins="http://localhost:3000")
@RestController
public class PostController {
	
	@Autowired 
	private PostRepository db;
	
	// show all posts when viewing marketplace
	@GetMapping("/posts")				/*WRONG URLS>>>???*/    /*some methods seem simpler than requireed*/
	public List<Post> getAllPosts()
	{
		return db.findAll();
	}
	
	//HERE IS WHERE THE CONTENTS ON SEARCH COME THROUGH
	@PostMapping("/searchitem")
	public void Search_Post(@RequestBody SearchPost search) {
		//String description= search.getdescription();
	//	String category= search.getcategory();
	//	for (Post post : db.findByCategory(category))
		//	System.out.println(post);
	}
	
	//HERE IS WHERE TO "SEND THE RESULTS"
	@GetMapping("/posts/searchBy/{description}{category}")	
	public List<Post> getfindByDescriptionAndCategory(@RequestBody SearchPost search, @PathVariable String description, @PathVariable String category)
	{
		return db.findByDescriptionAndCategory(description, category);
	}		
	/*@GetMapping("/posts/searchBy/{category}")	
	public List<Post> getfindByCategory(@PathVariable String category)
	{
	
	 return db.findByCategory(category);
	}		
	*/
	
	

	// adds a post to marketplace
	@PostMapping("/postitem")
	public Post addPost(@RequestBody Post post)
	{
		return db.save(post);
	}
	
	
	// when need to open a post in marketplace
	@RequestMapping("/posts/{id}")
	public Post getPost(@PathVariable Long id)
	{
		return db.findById(id).get();
	}
	
	// when need to open a post in marketplace
	@PutMapping("/posts/{id}")
	public Post updatePost(@PathVariable Long id, @RequestBody Post post)
	{
		return db.save(post);
	}
	
	@DeleteMapping("/posts/{id}")
	public void deletePost(@PathVariable Long id) 
	{
		db.deleteById(id);
	}
}



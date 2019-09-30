package com.sept.rest.webservices.restfulwebservices.post;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins="http://localhost:3000")
@RestController
public class PostController {
	
	@Autowired 
	private PostRepository db;
	
	
	@GetMapping("/posts")			
	public List<Post> getAllPosts()
	{
		return db.findAll();
	}


	@GetMapping("/posts/searchBy/{description}/{category}")	
	public List<Post> searchResults(@PathVariable String description, @PathVariable String category) 
	{
		
		List<Post> posts = db.findAll();
		Collections.sort(posts);
		
		if (!description.equals("undefined"))	{posts.retainAll(db.findByDescriptionContaining(description));}
		if (!category.equalsIgnoreCase("all"))	{posts.retainAll(db.findByCategory(category));}
		
		return posts;
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
	@PutMapping("/posts/{id}")
	public Post updatePost(@PathVariable Long id, @RequestBody Post post)
	{
		return db.save(post);
	}
		
		
	@DeleteMapping("/posts/{id}")
	public void deletePost(@PathVariable Long id) 
	{
		System.out.println("HI");
		db.deleteById(id);
	}
		

}
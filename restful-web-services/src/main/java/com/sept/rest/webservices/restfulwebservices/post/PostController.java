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



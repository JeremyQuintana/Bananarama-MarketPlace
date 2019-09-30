package com.sept.rest.webservices.restfulwebservices.post;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sept.rest.webservices.restfulwebservices.jwt.JwtTokenUtil;


@CrossOrigin(origins="http://localhost:3000")
@RestController
public class PostController {
	
	@Autowired
    private JwtTokenUtil jwtTokenUtil;
	
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
	public Post addPost(@RequestBody Post post, HttpServletRequest request)
	{
		final String ownerId = jwtTokenUtil.getUsernameFromToken(request.getHeader("Authorization").substring(7));
		System.out.println("Request Post From ID: " + ownerId);
		
		post.setOwner(ownerId);
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
	public Post updatePost(@PathVariable Long id, @RequestBody Post post, HttpServletRequest request)
	{
		final String ownerId = jwtTokenUtil.getUsernameFromToken(request.getHeader("Authorization").substring(7));
		System.out.println("Request Edit Post From ID: " + ownerId);
		
		final String itemOwnerId = db.findById(id).map(Post::getOwnerId).get();
		if (itemOwnerId.equals(ownerId)) {
			System.out.println("Successful Edit Post");
			post.setOwner(itemOwnerId);
			return db.save(post);
		} else {
			System.out.println("Error Update: Edit User ID " + ownerId + " does not match Item Owner ID " + itemOwnerId);
			return null;
		}
	}
	
	@DeleteMapping("/posts/{id}")
	public void deletePost(@PathVariable Long id) 
	{
		db.deleteById(id);
	}
}



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
		System.out.println("Request Post From ID: " + getOwnerId(request));
		
		post.setOwner(getOwnerId(request));
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
	public Post updatePost(@PathVariable Long id, @RequestBody Post edit, HttpServletRequest request)
	{
		if (!correctOwner(id, request)) {
			return null;
		}
		edit.setOwner(getOwnerId(request));
		edit.setId(id);
		return db.save(edit);
	}
	
	@DeleteMapping("/posts/{id}")
	public void deletePost(@PathVariable Long id,  HttpServletRequest request) 
	{
		if (correctOwner(id, request))
			db.deleteById(id);
	}
	
	
	
	
	
	
	
	private String getOwnerId(HttpServletRequest request)
	{
		return jwtTokenUtil.getUsernameFromToken(request.getHeader("Authorization").substring(7));
	}
	
	private boolean correctOwner(Long id, HttpServletRequest request)
	{
		Post realPost = db.findById(id).get();
		String loggedOwner =getOwnerId(request);
		
		if (!realPost.getOwnerId().equals(loggedOwner))
			throw new NullPointerException("Error Update: Edit User ID " + loggedOwner + " does not match Item Owner ID " + realPost.getOwnerId());
		return true;
	}
}



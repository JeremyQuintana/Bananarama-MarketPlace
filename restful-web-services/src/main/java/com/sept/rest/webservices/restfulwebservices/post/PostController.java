package com.sept.rest.webservices.restfulwebservices.post;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.sept.rest.webservices.restfulwebservices.jwt.JwtTokenUtil;
import com.sept.rest.webservices.restfulwebservices.post.Post.Status;





@CrossOrigin(origins="${spring.crossorigin.url}")
@RestController
public class PostController {

	@Autowired
    private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private PostService service;
	
	private ImageController imageController = new ImageController();

	// posts that are for sale
	@GetMapping("/posts")
	public List<Post> getAllAvailablePosts()
	{
		return service.getAllAvailable();
	}
	
	
	@GetMapping("/posts/searchBy/{description}/{category}/{sort}")
	public List<Post> Sort(@PathVariable String description, @PathVariable String category, @PathVariable String sort) {
			
		// sort all available posts
		List<Post> sorted = service.sortAll(sort);
		sorted.retainAll(service.getAllAvailable());
		return service.filterByDescriptionAndCategory(description, category, sorted);
	}


	// adds a post to marketplace
	// security check: if you are NOT the owner, will make the post in your name
	@PostMapping("/postitem")
	public Post addPost(@RequestBody Post post, HttpServletRequest request)
	{
		// we cannot affort to 
		String photo = post.getPhoto();
		post.setPhoto(null);
		post = service.update(post);
		post.setOwner(getOwnerId(request));
		
		imageController.uploadImage(photo, post.getId() + "");
		return post;
	}
	
	@PutMapping("/posts/{id}")
	public Post updatePost(@PathVariable Long id, @RequestBody Post edit, HttpServletRequest request)
	{
		return addPost(edit, request);
	}


	
	@DeleteMapping("/posts/{id}")
	public void deletePost(@PathVariable Long id, HttpServletRequest request)
	{	
		if (correctOwner(id, request))
			service.delete(service.get(id));
	}
	
	@GetMapping("/posts/{id}")
	public Post getPost(@PathVariable Long id)
	{
		return service.get(id);
	}
	
	@PostMapping("/posts/{id}/{status}")
	public Post updatePostStatus(@PathVariable Long id, @PathVariable Status status, HttpServletRequest request)
	{	
		Post post = service.get(id);
		post.setStatus(status);
		return correctOwner(id, request) ? service.update(post) : null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
//	reused /posts/{id}/{status} (already done for sold, available)
//	@PostMapping("/postsdelete")
//	public void deletePosts(@RequestBody Post post, HttpServletRequest request)
//	{	
//		if (correctOwner(post.getId(), request))
//			post.setStatus(Status.DELETED);
//	}
	
	@PostMapping("/postspermdelete")
	public void DeletePosts(@RequestBody Post post, HttpServletRequest request)
	{	
		if (correctOwner(post.getId(), request)) {
			service.delete(service.get(post.getId()));
			imageController.deleteImage(post.getId().toString());
		}
	}
	


	
	//logic for account history incase u need to sort them into groups like all marked as sold, all marked as deleted etc
	// directly putting status, as you need to mention this in the url anyway
	
	@GetMapping("/{ownerId}/posts/{status}")
	public List<Post> getCurrentPosts(@PathVariable String ownerId, @PathVariable Status status) 
	{
		System.out.println("requested status: " + status);
		for (Post post : service.getOwnerPosts(ownerId, status))
		{
			System.out.println(post);
		}
		return service.getOwnerPosts(ownerId, status);
	}
			
		
	


	
	
	
	
	
	
	
	
	
	
	
	
	private String getOwnerId(HttpServletRequest request)
	{
		return jwtTokenUtil.getUsernameFromToken(request.getHeader("Authorization").substring(7));
	}
	
	private boolean correctOwner(Long id, HttpServletRequest request)
	{
		Post realPost = service.get(id);
		String loggedOwner =getOwnerId(request);
		
		if (!realPost.getOwnerId().equals(loggedOwner))
			throw new NullPointerException("Error Update: Edit User ID " + loggedOwner + " does not match Item Owner ID " + realPost.getOwnerId());
		return true;
	}




}

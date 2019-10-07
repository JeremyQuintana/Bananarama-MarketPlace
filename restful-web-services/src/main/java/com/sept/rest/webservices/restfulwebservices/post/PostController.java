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
	@PostMapping("/postitem")
	public Post addPost(@RequestBody Post post, HttpServletRequest request)
	{
		post.setOwner(getOwnerId(request));
		return service.update(post);
	}

	// when need to open a post in marketplace
	@PutMapping("/posts/{id}")
	public Post updatePost(@PathVariable Long id, @RequestBody Post edit, HttpServletRequest request)
	{
		return correctOwner(id, request) ? service.update(edit) : null;
	}


	@PostMapping("/postsdelete")
	public void deletePosts(@RequestBody Post post, HttpServletRequest request)
	{	
		service.markedasdelete(post.getId());
	}
	
	@PostMapping("/postssold")
	public void soldPosts(@RequestBody Post post, HttpServletRequest request)
	{	
		service.markedassold(post.getId());
	}
	
	@PostMapping("/postspermdelete")
	public void DeletePosts(@RequestBody Post post, HttpServletRequest request)
	{	
		service.delete(post.getId());
	}
	
	@GetMapping("/posts/{id}")
	public Post getPost(@PathVariable Long id)
	{
		return service.get(id);
	}
	
	//logic for account history incase u need to sort them into groups like all marked as sold, all marked as deleted etc

	@GetMapping("/{ownerId}/posts")
	public List<Post> getCurrentPosts(@PathVariable String ownerId) {
		return service.getAllAvailableByOwner(ownerId);
	}
	//@GetMapping("/{ownerId}/posts")
	//public List<Post> getSoldPosts(@PathVariable String ownerId) {
	//	return service.getAllSoldByOwner(ownerId);
	//}
//	@GetMapping("/{ownerId}/posts")
//	public List<Post> getDeletedPosts(@PathVariable String ownerId) {
//		return service.getAllDeletedByOwner(ownerId);
//	}
			
		
	
	
	//endhere
	
	
	
	
	
	
	
	
	
	
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

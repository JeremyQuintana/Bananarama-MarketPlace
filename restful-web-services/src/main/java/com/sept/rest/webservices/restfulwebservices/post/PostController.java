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
import com.sept.rest.webservices.restfulwebservices.user.UserService;





@CrossOrigin(origins="${spring.crossorigin.url}")
@RestController
public class PostController {

	// refactor the user authentication with this class
	@Autowired
    private UserService checker;

	@Autowired
	private PostService service;
	
	// cannot use the database due to space constraints, so using google bucket
	// to store photos
	private ImageController imageController = new ImageController();

	// posts that are for sale
	@GetMapping("/posts")
	public List<Post> getAllAvailablePosts()
	{
		return service.getAllAvailable();
	}
	
	// can also handle for empty input for either parameters
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
		post.setOwner(checker.getOwnerId(request));
		post = service.update(post);
		imageController.uploadImage(photo, post.getId() + "");
		return post;
	}
	
	// security decision: users can change the id, however any bad-will changes will create a post in their name
	@PutMapping("/posts/{id}")
	public Post updatePost(@PathVariable Long id, @RequestBody Post edit, HttpServletRequest request)
	{
		String photo = edit.getPhoto();
		edit.setPhoto(null);
		edit.setId(id);
		if (checker.getOwnerId(request).equals(edit.getOwnerId())) {
			edit = service.update(edit);
			imageController.uploadImage(photo, edit.getId() + "");
		} else {
			throw new NullPointerException("Incorrect ID");
		}
		return edit;
	}


	@DeleteMapping("/posts/{id}")
	public void deletePost(@PathVariable Long id, HttpServletRequest request)
	{	
		Post toDelete = service.get(id);
		// will not do anything if the owner does not match
		if (checker.isEqual(toDelete.getOwnerId(), request)) 
			service.delete(service.get(id));
	}
	
	@GetMapping("/posts/{id}")
	public Post getPost(@PathVariable Long id)
	{
		return service.get(id);
	}
	
	
	// a user can update their post to sold, (temp) deleted, or back to available
	@PostMapping("/posts/{id}/{status}")
	public Post updatePostStatus(@PathVariable Long id, @PathVariable Status status, HttpServletRequest request)
	{	
		Post post = service.get(id);
		post.setStatus(status);
		return checker.isEqual(post.getOwnerId(), request) ? service.update(post) : null;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//logic for account history incase u need to sort them into groups like all marked as sold, all marked as deleted etc
	@GetMapping("/{ownerId}/posts/{status}")
	public List<Post> getCurrentPosts(@PathVariable String ownerId, @PathVariable Status status) 
	{
		return service.getOwnerPosts(ownerId, status);
	}
			


}

package com.sept.rest.webservices.restfulwebservices.post;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javadb.DatabaseRef;


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
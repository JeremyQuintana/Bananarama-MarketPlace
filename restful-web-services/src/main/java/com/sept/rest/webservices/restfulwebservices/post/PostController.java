package com.sept.rest.webservices.restfulwebservices.post;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
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


	//this is where the search results get sent to 
	@GetMapping("/posts/searchBy/{description}/{category}")	
	public List<Post> getfindByDescriptionAndCategory(@PathVariable String description, @PathVariable String category) {

		if (!category.equals("all") && !description.equals("undefined")) {
			return db.findByDescriptionContainingAndCategory(description, category);
		}
		else if (description.equals("undefined")) {
			return db.findByCategory(category);
		}
		else if (category.equals("all") && !description.equals("undefined")) {
			return db.findByDescriptionContaining(description);
			
		}
		
		else {
			return db.findAll();
		}

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
	}
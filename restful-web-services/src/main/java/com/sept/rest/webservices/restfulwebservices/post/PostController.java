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

	
	@GetMapping("/posts/searchBy/{description}/{category}")	
	public List<Post> getByDescriptionAndCategory(@PathVariable String description, @PathVariable String category) 
	{
		ArrayList<Post> posts = new ArrayList<>(db.findAll());
		if (!description.equals("undefined")) {
			posts.retainAll(db.findByDescriptionContaining(description));
		}
		if (!category.equals("all")) {
			posts.retainAll(db.findByCategory(category));
		}
		
		return posts;
	}
	
	

	@GetMapping("/posts/searchBy/{description}/{category}/{sort}")	
	public List<Post> Sort(@PathVariable String description, @PathVariable String category, @PathVariable String sort) {
		System.out.print(sort);
		
		if(sort.equals("High")){
			ArrayList<Post> sortsPost = new ArrayList<>(db.findAllByOrderByPriceDesc());
			System.out.println("hight");
			if (!description.equals("undefined")) {
				sortsPost.retainAll(db.findByDescriptionContaining(description));
			}
			
			if (!category.equals("all")) {
				sortsPost.retainAll(db.findByCategory(category));
			}
			return sortsPost;
		}
		
		if(sort.equals("Low")){
			ArrayList<Post> sortsPost = new ArrayList<>(db.findAllByOrderByPriceAsc());
			System.out.println("low");
			if (!description.equals("undefined")) {
				sortsPost.retainAll(db.findByDescriptionContaining(description));
				}
			
			if (!category.equals("all")) {
				sortsPost.retainAll(db.findByCategory(category));
			}
			return sortsPost;
		}
		
		if(sort.equals("Old")){
			ArrayList<Post> sortsPost = new ArrayList<>(db.findAllByOrderByDatePostedAsc());
			System.out.println("Date");
			if (!description.equals("undefined")) {
				sortsPost.retainAll(db.findByDescriptionContaining(description));
			}
			
			if (!category.equals("all")) {
				sortsPost.retainAll(db.findByCategory(category));
			}
			return sortsPost;
		}
		
		
		ArrayList<Post> sortsPost = new ArrayList<>(db.findAllByOrderByDatePostedDesc());
		System.out.println("Date");
		if (!description.equals("undefined")) {
			System.out.print("1");
			
			sortsPost.retainAll(db.findByDescriptionContaining(description));
		}
		
		if (!category.equals("all")) {
			System.out.print("2");
			
			sortsPost.retainAll(db.findByCategory(category));
		}
		
		System.out.println("returning New Date sorted");
		return sortsPost;
	}
		
		

	
		
		
		

	/*
	
	//this is where the search results get sent to 
		@GetMapping("/posts/searchBy/{description}/{category}/{sort}")	
		public List<Post> Sort(@PathVariable String description, @PathVariable String category, @PathVariable String sort) {
			System.out.print(sort);
			ArrayList<Post> posts = new ArrayList<>(db.findAll());
			if(sort.equals("High")) {	
				if (!category.equals("all") && !description.equals("undefined")) {
					System.out.println("1");
					return db.findByDescriptionContainingAndCategoryOrderByPriceDesc(description, category);
				}
				else if (!category.equals("undefined") && description.equals("undefined")) {
					System.out.println("High no description");
					return db.findByCategoryOrderByPriceDesc(category);
				}
				else if (category.equals("all") && !description.equals("undefined")) {
					System.out.println("3");
					return db.findByDescriptionContainingOrderByPriceDesc(description);
					
				}
				
				else {
					return db.findAllByOrderByPriceDesc();
				}
			}
			
			else if (sort.equals("low")) {
				if (!category.equals("all") && !description.equals("undefined")) {
					System.out.println("1");
					return db.findByDescriptionContainingAndCategoryOrderByPriceAsc(description, category);
				}
				else if (!category.equals("undefined") && description.equals("undefined")) {
					System.out.println("2");
					return db.findByCategoryOrderByPriceAsc(category);
				}
				else if (category.equals("all") && !description.equals("undefined")) {
					System.out.println("3");
					return db.findByDescriptionContainingOrderByPriceAsc(description);
					
				}
				
				else {
					System.out.println("4");
					return db.findAllByOrderByPriceAsc();
				}
				
			}
			else {return null;}
	
			
		}
*/
	
//	@PostMapping("/searchitemsort")
	//public Post sortSort(@RequestBody SearchPost search) {
		//search.print();
		//search.printsort();
		//return null;
	//}
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
			System.out.println("HI");
			db.deleteById(id);
		}
		

	}
package com.sept.rest.webservices.restfulwebservices.post;
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
import org.springframework.web.bind.annotation.RestController;

import javadb.DatabaseRef;


@CrossOrigin(origins="http://localhost:3000")
@RestController
public class PostController {


	@Autowired 
	private PostRepository db;
	
	//random stuff to save
	
	// show all posts when viewing marketplace
	/*@GetMapping("/posts")				
	public String[][] getAllPosts()
	{
		
		String[][] posts = new String[db.findAll().size()][5];
		int i=0;
		for (Post post : db.findAll())
		{
			String[] postStr = {Long.toString(post.getId()), post.getTitle(), post.getDescription(), post.getOwnerId(), post.getPrice()};
			posts[i++] = postStr;
		}
		return posts;
	}*/
	@GetMapping("/posts")			
	public List<Post> getAllPosts()
	{
		for (Post post : db.findByDescriptionAndCategory("fandangled mess", "Annoyingly Unnexplained"))
			System.out.println(post);
		return db.findAll();
	}
	
	//HERE IS WHERE THE CONTENTS ON SEARCH COME THROUGH
	@PostMapping("/searchitem")
	public void Search_Post(@RequestBody SearchPost search) {
		
		search.print();
	}
	
	//HERE IS WHERE TO "SEND THE RESULTS"
	@GetMapping("/posts/searchBy{description}and{category}")	
	public List<Post> getfindByDescriptionAndCategory(@PathVariable String description, @PathVariable String category)
	{
		return db.findByDescriptionAndCategory(description, category);
	}		
	

	// adds a post to marketplace
	@PostMapping("/postitem")
	public Post addPost(@RequestBody Post post)
	{
		return db.save(post);
	}
	
	
	// when need to open a post in marketplace
	@RequestMapping("/market/{id}")
	public Post getPost(@PathVariable Long id)
	{
		return db.findById(id).get();
	}
	
	// when need to open a post in marketplace
	@PutMapping("/market/{id}")
	public Post updatePost(@PathVariable Long id, @RequestBody Post post)
	{
		return db.save(post);
	}
	
	@DeleteMapping("/market/{id}")
	public void deletePost(@PathVariable Long id) 
	{
		db.deleteById(id);
	}
}



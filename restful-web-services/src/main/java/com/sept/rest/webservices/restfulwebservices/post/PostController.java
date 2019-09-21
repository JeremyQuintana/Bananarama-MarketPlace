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

//	HERE IS WHERE THE CONTENTS ON SEARCH COME THROUGH
	@PostMapping("/searchitem")
	public void Search_Post(@RequestBody SearchPost search) {
		String description= search.getdescription();
		String category= search.getcategory();
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter("SEARCH.txt");
		
		
		PrintWriter writer = new PrintWriter(fileWriter);
			
			writer.print(description +",");
			writer.print(category);
		
			
			writer.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	//this is where the search results get sent to 
	@GetMapping("/posts/searchBy/{description}/{category}")	
	public List<Post> getfindByDescriptionAndCategory(@PathVariable String description, @PathVariable String category) 
	{
		if (category.equals("All"))
			return db.findByDescription(description);
		return db.findByDescriptionAndCategory(description, category);
	}	
	
	
	// adds a post to marketplace
	@PostMapping("/postitem")
	public Post addPost(@RequestBody Post post)
	{
		return db.save(post);
	@GetMapping("/posts/searchBy")
	public List<Post> getbyfindByDescriptionAndCategory() throws IOException
	
	{String [] data = new String[2];
	String descriptions;
	String categorys;
		BufferedReader br;
	try {
		br = new BufferedReader(new FileReader("SEARCH.txt"));
	
	String line= "";
	while((line=br.readLine()) != null)  {
		data = line.split(",");
	}
	descriptions = data[0];
	categorys = data[1];
	br.close();
	System.out.println("variables are what");
	System.out.println(descriptions);
	System.out.println(categorys);
	//for (Post list : db.findByDescriptionAndCategory(descriptions, categorys))
		//System.out.println(list);
		//System.out.println("Aworking?");
	if (descriptions.equals("")  && categorys.equals("") ) {
		System.out.println("returning NULL");
		return null;
		

	// when need to open a post in marketplace
	@RequestMapping("/posts/{id}")
	public Post getPost(@PathVariable Long id)
	{
		return db.findById(id).get();
	}
	else if (descriptions.equals("")) {
		System.out.println("no desceriptions");
		return db.findByCategory(categorys);
	}
	else if (categorys.equals("all")) {
		System.out.println("no category");
		return db.findByDescriptionContaining(descriptions);
	}
	else 
	{System.out.println("bothworking");
		return db.findByDescriptionContainingAndCategory(descriptions, categorys);
	}
	
	}
	
	 catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
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
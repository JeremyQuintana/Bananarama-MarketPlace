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


@CrossOrigin(origins="http://localhost:3000")
@RestController
public class PostController {

	@Autowired
    private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private PostRepository db;



	@GetMapping("/posts")
	public List<Post> getAllPosts()
	{
		//ArrayList<Post> posts = new ArrayList<>(db.findAll());
		return db.findAll();
		//posts.retainAll(db.findByStatus("AVAILABLE"));
	//	for (int i = 0; i < posts.size(); i++) {
		//	System.out.println(posts.get(i));}
		//return posts;

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


		if(sort.equals("High")){
			ArrayList<Post> sortsPost = new ArrayList<>(db.findAll());
			if (!description.equals("undefined")) {
				sortsPost.retainAll(db.findByDescriptionContaining(description));
			}

			if (!category.equals("all")) {
				sortsPost.retainAll(db.findByCategory(category));
			}

			Collections.sort(sortsPost, new SortPost());
			Collections.reverse(sortsPost);
			return sortsPost;
		}


		if(sort.equals("Low")){
			ArrayList<Post> sortsPost = new ArrayList<>(db.findAll());
			if (!description.equals("undefined")) {
				sortsPost.retainAll(db.findByDescriptionContaining(description));
			}

			if (!category.equals("all")) {
				sortsPost .retainAll(db.findByCategory(category));
			}

			Collections.sort(sortsPost, new SortPost());
			return sortsPost;
		}


		if(sort.equals("Old")){
			ArrayList<Post> sortsPost = new ArrayList<>(db.findAllByOrderByDatePostedAsc());
			System.out.println("Date");
			if (!description.equals("undefined")) {
				sortsPost .retainAll(db.findByDescriptionContaining(description));
			}

			if (!category.equals("all")) {
				sortsPost .retainAll(db.findByCategory(category));
			}
			return sortsPost;
		}


		ArrayList<Post> sortsPost = new ArrayList<>(db.findAllByOrderByDatePostedDesc());
		System.out.println("Date");
		if (!description.equals("undefined")) {
			System.out.print("1");

			sortsPost .retainAll(db.findByDescriptionContaining(description));
		}

		if (!category.equals("all")) {
			System.out.print("2");

			sortsPost .retainAll(db.findByCategory(category));
		}

		System.out.println("returning New Date sorted");
		return sortsPost;
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
		System.out.println("HI");
		db.deleteById(id);
	}

	@GetMapping("/posts/{id}")
	public Post getPost(@PathVariable Long id)
	{
		return db.findById(id).get();

	}


}

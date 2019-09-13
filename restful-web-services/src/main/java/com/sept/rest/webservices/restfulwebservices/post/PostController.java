package com.sept.rest.webservices.restfulwebservices.post;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	private PostService postService;
	
//	@PostMapping(value = "/postitem")
//	public void postItem(@RequestBody Post item) throws SQLException {
//		System.out.println("sent item to backend. " + item.toString());
////		new DatabaseRef().sell_item("s1234567", item.getName(), item.getDescription(), item.getPrice(), item.getCategory());
//	}
	
	
	
	
	
	
//	// show all posts when viewing marketplace
//	@RequestMapping("/posts")				
//	public String[][] getAllPosts()
//	{
//		List<Post> postList = postService.getAll();
//		String[][] posts = new String[postList.size()][postList.get(0)];
//		for (Post post : postService.getAll())
//		{
//			
//		}
//		return postService.getAll();
//	}
//	
	// show all posts when viewing marketplace
	@RequestMapping("/posts")				/*WRONG URLS>>>???*/    /*some methods seem simpler than requireed*/
	public List<Post> getAllPosts()
	{
		return postService.getAll();
	}
	
	// adds a post to marketplace
	@PostMapping("/postitem")
	public void addPost(@RequestBody Post post)
	{
		System.out.println(post);
		postService.add(post);
	}
	
	
	// when need to open a post in marketplace
	@RequestMapping("/market/{id}")
	public Post getPost(@PathVariable Long id)
	{
		return postService.get(id);
	}
	
	// when need to open a post in marketplace
	@PutMapping("/market/{id}")
	public void updatePost(@PathVariable Long id, @RequestBody Post post)
	{
		postService.update(id, post);
	}
	
	@DeleteMapping("/market/{id}")
	public void deletePost(@PathVariable Long id) {

		postService.delete(id);
	}
}

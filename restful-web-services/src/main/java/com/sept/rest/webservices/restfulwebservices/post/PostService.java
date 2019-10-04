package com.sept.rest.webservices.restfulwebservices.post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public class PostService {

	@Autowired
	private PostRepository db;
//	private Map<Integer, Post> posts = new HashMap<>();
//	private int id = 0;
// collecting values from database so these don't matter

	public Post get(Long id) 	
	{
		return db.findById(id).get();
	}
	
	public List<Post> getAll() 	
	{
		return db.findAll();
	}
	
//	post.setId(id);
//	posts.put(id++, post);
	public void add(Post post) 	
	{
		db.save(post);
	}
	// old: posts.put(post.getId(), post);
	public Post update(Post post) 
	{
		return db.save(post);
	}	

	public void delete(Long id)
	{
		db.deleteById(id);
	}
	
	
	
	
	
	
	
	public List<Post> getAllUserPosts(String ownerId)
	{
		return db.findByOwnerId(ownerId);
	}
	
	public List<Post> sortAll(String sort)
	{
		List<Post> posts = db.findAll();
		switch (sort)
		{
			case "Old":		posts = db.findAllByOrderByDatePostedAsc();				break;
			case "Date":	posts = db.findAllByOrderByDatePostedDesc();			break;
			case "High":	Collections.sort(posts, Collections.reverseOrder());	break;
			case "Low": 	Collections.sort(posts);								break;	
		}
		return posts;
	}
	
	public List<Post> filterByDescriptionAndCategory(String desc, String category, List<Post> posts)
	{
		if (!desc.equals("undefined")) {
			posts.retainAll(db.findByDescriptionContaining(desc));
		}

		if (!category.equals("all")) {
			posts.retainAll(db.findByCategory(category));
		}
		return posts;
	}
}
package javadb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.util.Arrays;
import org.springframework.stereotype.Service;

@Service
public class PostService {

	private Map<Integer, Post> posts = new HashMap<>();
	private int id = 0;
	

	public Post get(int id) 	
	{
		return posts.get(id);
	}
	
	public List<Post> getAll() 	
	{
		return new ArrayList<>(posts.values());
	}
	
	public void add(Post post) 	
	{
		post.setId(id);
		posts.put(id++, post);
	}
	
	public void update(int id, Post post) 
	{
		posts.put(post.getId(), post);
	}

	public Post delete(int id)
	{
		return posts.remove(id);
	}
	
//private Map<Post> posts = new HashMap<>();
//	
//
//	public Post get(int id) 	
//	{
//		return posts.stream().filter(post -> post.getId() == id).findFirst().get();
//	}
//	
//	public List<Post> getAll() 	
//	{
//		return posts;
//	}
//	
//	public void add(Post post) 	
//	{
//		posts.add(post);
//	}
//	
//	public void update(int id, Post post) 
//	{
//		for (int i=0; i<posts.size(); i++)
//			if (post.getId() == id)
//				posts.set(i, post);
//	}
//
//	public Post delete(int id)
//	{
//		posts
//	}
		
	
}

package javadb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

	@Autowired
	private PostRepository db;
//	private Map<Integer, Post> posts = new HashMap<>();
//	private int id = 0;
// collecting values from database so these don't matter

	public Post get(int id) 	
	{
		return db.findById(id).get();
	}
	
	// old: posts.values()
	public List<Post> getAll() 	
	{
		// convert iterator -> list of posts and return
		List<Post> posts = new ArrayList<>();
		db.findAll().forEach(posts::add);
		return posts;
		
	}
	
//	post.setId(id);
//	posts.put(id++, post);
	public void add(Post post) 	
	{
		db.save(post);
	}
	// old: posts.put(post.getId(), post);
	public void update(int id, Post post) 
	{
			/*MAY WORK, cuz using id to check existence*/
		db.save(post);
	}	

	public void delete(int id)
	{
		db.deleteById(id);
	}
		
	
}
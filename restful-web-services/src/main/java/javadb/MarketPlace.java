package javadb;

import java.util.ArrayList;
import java.util.List;

import com.sept.rest.webservices.restfulwebservices.post.Post;


public class MarketPlace {

	public MarketPlace(List<Post> posts)
	{
		this.posts = posts;
	}
	
	public MarketPlace()
	{
		this.posts = new ArrayList<>();
	}
	
	private List<Post> posts;
	
	public void addPost(Post post)
	{
		posts.add(post);
	}
}


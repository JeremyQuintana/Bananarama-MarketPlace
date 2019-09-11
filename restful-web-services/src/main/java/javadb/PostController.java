package javadb;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.sept.rest.webservices.restfulwebservices.todo.Todo;
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


@CrossOrigin(origins="http://localhost:3000")
@RestController
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@PostMapping(value = "/postitem")
	public void postItem(@RequestBody PostItem item) throws SQLException {
		new DatabaseRef().sell_item("s1234567", item.getName(), item.getDescription(), item.getCost(), item.getCatagory());
	}
	
	
	
	
	
	
	
	// show all posts when viewing marketplace
	@RequestMapping("/market")				/*WRONG URLS>>>???*/    /*some methods seem simpler than requireed*/
	public List<Post> getAllPosts()
	{
		return postService.getAll();
	}
	
	// adds a post to marketplace
	@PostMapping("/market")
	public void addPost(@RequestBody Post post)
	{
		postService.add(post);
	}
	
	
	// when need to open a post in marketplace
	@RequestMapping("/market/{id}")
	public Post getPost(@PathVariable int id)
	{
		return postService.get(id);
	}
	
	// when need to open a post in marketplace
	@PutMapping("/market/{id}")
	public void updatePost(@PathVariable int id, @RequestBody Post post)
	{
		postService.update(id, post);
	}
	
	@DeleteMapping("/market/{id}")
	public void deletePost(@PathVariable int id) {

		postService.delete(id);
	}
}

package com.sept.rest.webservices.restfulwebservices.user;
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

import com.sept.rest.webservices.restfulwebservices.post.Post;
import com.sept.rest.webservices.restfulwebservices.post.PostService;

import javadb.DatabaseRef;


@CrossOrigin(origins="http://localhost:3000")
@RestController
public class UserController {

	@Autowired
	private PostService postService;
	
	
//	@GetMapping("/{ownerId}/posts")
//	public List<Post> getAllUserPosts(@PathVariable String ownerId)
//	{
//		return postService.getAllUserPosts(ownerId);
//	}
}



package com.sept.rest.webservices.restfulwebservices.todo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {

	// and with this, the method is already implemented
	// jpa MAGICALLY uses the method name to know what to look for
	public List<Post> findByCategory(String category);
}

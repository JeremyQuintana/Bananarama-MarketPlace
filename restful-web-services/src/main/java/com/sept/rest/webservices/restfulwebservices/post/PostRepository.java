package com.sept.rest.webservices.restfulwebservices.post;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	// and with this, the method is already implemented
	// jpa MAGICALLY uses the method name to know what to look for
	public List<Post> findByCategory(String category);
}
package com.sept.rest.webservices.restfulwebservices.post;

import java.util.List;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sept.rest.webservices.restfulwebservices.post.Post;
import com.sept.rest.webservices.restfulwebservices.post.Post.Status;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	// and with this, the method is already implemented
	// jpa MAGICALLY uses the method name to know what to look for


	public List<Post> findByCategory(String category);
	public List<Post> findByDescriptionContaining(String description);
	public List<Post> findAllByOrderByDatePostedDesc();
	public List<Post> findAllByOrderByDatePostedAsc();
	public List<Post> findByOwnerId(String ownerId);
	public List<Post> findByStatus(Status available);
	
	@Transactional
	@Modifying
	@Query(value = "update Post p set status = :status where p.id= :id")
	void updateStatus(@Param("id") long id, @Param("status") Status status);
	
	@Transactional
	@Modifying
	@Query(value = "Delete Post p where p.id= :id")
	void deletePost(@Param("id") long id);
	


}

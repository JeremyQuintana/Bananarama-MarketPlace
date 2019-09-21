package com.sept.rest.webservices.restfulwebservices.post;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	// and with this, the method is already implemented
	// jpa MAGICALLY uses the method name to know what to look forshit so epics just 
	
	public List<Post> findAllByOrderByPriceAsc();
	public List<Post> findAllByOrderByPriceDesc();

	public List<Post> findByCategory(String category);
	public List<Post> findByCategoryOrderByPriceAsc(String category);
	public List<Post> findByCategoryOrderByPriceDesc(String category);
	
//	public List<Post> findByCategoryOrderByDate_PostedAsc(String category);
	public List<Post> findByDescriptionContaining(String description);
	public List<Post> findByDescriptionContainingOrderByPriceAsc(String description);
	public List<Post> findByDescriptionContainingOrderByPriceDesc(String description);
//	public List<Post> findByDescriptionContainingOrderByDate_PostedAsc(String description);
	public List<Post> findByDescriptionContainingAndCategory(String description, String category);
	public List<Post> findByDescriptionContainingAndCategoryOrderByPriceAsc(String description, String category);
	public List<Post> findByDescriptionContainingAndCategoryOrderByPriceDesc(String description, String category);
//	public List<Post> findByDescriptionContainingAndCategoryOrderByDate_PostedAsc(String description, String category);
}

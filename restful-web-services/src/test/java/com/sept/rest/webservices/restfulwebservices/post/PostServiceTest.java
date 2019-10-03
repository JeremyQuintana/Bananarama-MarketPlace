package com.sept.rest.webservices.restfulwebservices.post;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
class PostServiceTest {

	@MockBean
	private PostRepository db;
	@Autowired
	private PostService service;
	private static Post post1;
	private static Post post2;
	private static Post post3;
	private static Post post4;
	private  Post post5;
	private  Post post6;
	private List<Post> posts;

	@BeforeEach
	void setUp() {
		post1 = new Post(new Long(2), "s1234567", "Appleberry's", "Delicious", "2.222", "Fruit");
		post2 = new Post(new Long(5), "s1234567", "doodoo boo", "Cleans the backend leaving no traces", "9999", "Baby Wipes");
		post3 = new Post(new Long(6), "s3717497", "Pill", "Pickpocketed off Morpheus", "2", "Medecine");
		post4 = new Post(new Long(7), "s3717497", "Pill", "Pickpocketed off Morpheus", "3", "Food");
		post5 = new Post(new Long(8), "s3717497", "Pill", "Pickpocketed off Mateo", "4", "Food");
		post6 = new Post(new Long(9), "s3717497", "Pill", "Pickpocketed off Mateo", "5", "Medecine");		
		
		
		posts = new ArrayList<>();
		posts.add(post1);
		posts.add(post2);
		posts.add(post3);
		posts.add(post4);
		posts.add(post5);
		posts.add(post6);
	}

	@Test
	void testGet() {
		Long id = post1.getId();
	    Optional<Post> optionalPost = Optional.of(post1);
	    Mockito.when(db.findById(id)).thenReturn(optionalPost);
		assertEquals(post1, service.get(id));
	}

	@Test
	void testGetAllUserPosts() {
		List<Post> expected = new ArrayList<>();
		for (Post post : posts)
			if (post.getOwnerId().equals("s1234567"))
				expected.add(post);
		Mockito.when(db.findByOwnerId("s1234567")).thenReturn(expected);
		assertEquals(expected, service.getAllUserPosts("s1234567"));
	}

	@Test
	void testSortPrice() {

		List<Post> sortedAsc = new ArrayList<>();
		sortedAsc.add(post3);
		sortedAsc.add(post1);
		sortedAsc.add(post4);
		sortedAsc.add(post5);
		sortedAsc.add(post6);
		sortedAsc.add(post2);
		
		List<Post> sortedDesc = new ArrayList<>();
		sortedDesc.add(post2);
		sortedDesc.add(post6);
		sortedDesc.add(post5);
		sortedDesc.add(post4);
		sortedDesc.add(post1);
		sortedDesc.add(post3);
		
//		System.out.println("Expected: ");
//		for (Post post : sortedAsc)
//			System.out.println(post);
//		
//		System.out.println("\n\n\n\nExpected: ");
//		for (Post post : sortedDesc)
//			System.out.println(post);
		
		
		
		System.out.println("\nActual: ");
		for (Post post : service.sortAll("High"))
			System.out.println(post);
		System.out.println("\nActual: ");
		for (Post post : service.sortAll("Low"))
			System.out.println(post);
		
		Mockito.when(db.findAll()).thenReturn(posts);
		assertEquals(sortedAsc, service.sortAll("Low"));
		assertEquals(sortedDesc, service.sortAll("High"));
		
	}
	

	@Test
	void testFilter() {
		List<Post> matchDesc = new ArrayList<>();
		List<Post> matchDescAndCate = new ArrayList<>();
		
		for (Post post : posts)
			for (String word :post.getDescription().split(" "))
				if (word.equals("Pickpocketed"))
				{
					matchDesc.add(post);
					if (post.getCategory().equals("Medecine"))
						matchDescAndCate.add(post);
					break;
				}
		Mockito.when(db.findByDescriptionContaining("Pickpocketed")).thenReturn(matchDesc);
		Mockito.when(db.findByCategory("Medecine")).thenReturn(matchDescAndCate);
		assertEquals(matchDesc, service.filterByDescriptionAndCategory("Pickpocketed", "all", posts));
		assertEquals(matchDescAndCate, service.filterByDescriptionAndCategory("Pickpocketed", "Medecine", posts));
			
	}
	
	@Test
	void testSortWithWrongCondition() {
		
		Mockito.when(db.findAll()).thenReturn(posts);
		
		assertEquals(posts, service.sortAll("donkey"));
		assertEquals(posts, service.sortAll("broomstick"));
		assertEquals(posts, service.sortAll("Homeopathy"));
	}
	
	@Test
	void testFilterEmptyConditions() {
		Mockito.when(db.findAll()).thenReturn(posts);
		String rightDesc = "undefined";
		String rightCate = "all";
		
		assertEquals(new ArrayList<>(), service.filterByDescriptionAndCategory("donkey", rightCate, posts));
		assertEquals(new ArrayList<>(), service.filterByDescriptionAndCategory(rightDesc, "mokey", posts));
		assertEquals(posts, service.filterByDescriptionAndCategory(rightDesc, rightCate, posts));
	}

}

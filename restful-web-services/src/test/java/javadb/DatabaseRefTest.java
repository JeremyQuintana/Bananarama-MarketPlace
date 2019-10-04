package javadb;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;
import java.util.Collections;
import java.util.LinkedList;

import org.junit.AfterClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.TestPropertySource;

import com.sept.rest.webservices.restfulwebservices.post.Post;
import com.sept.rest.webservices.restfulwebservices.post.Post.Status;

@TestPropertySource(properties = "spring.datasource.url= ${spring.datasource.urltest}")
class DatabaseRefTest {

	
	private static DatabaseRef db;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		db = new DatabaseRef();
	}
	
	@Test
	void testConnection() {
		assertTrue(db.canConnect());
	}

	
	@Test
	void testCheckId() throws SQLException {
		assertTrue(db.checkId("s1234567"));
		assertFalse(db.checkId("12v5908"));
	}
	
	@Test
	void testCheckPassword() throws SQLException {
		// with correct and wrong password
		assertTrue(db.checkPassword("s1234567", "123456"));
		assertFalse(db.checkPassword("s1234567", "4m90348m0v"));
	}
	
	@Test
	void testCheck_for_sale() throws SQLException {
		for (Post post : db.check_for_sale().values())
			assertEquals(post.getStatus(), Status.AVAILABLE);
	}
	
	@Test
	void testSearchCategory() throws SQLException {
		for (Post post : db.searchCategory("Automotive").values())
			assertEquals(post.getCategory(), "Automotive");
	}
	
	@Test
	void testSearchHistory() throws SQLException {
		for (Post post : db.sale_history("s1234567").values())
			assertEquals(post.getOwnerId(), "s1234567");
	}
	
	@Test
	void testAddPost() throws SQLException {
		
	
		db.sell_item("s1234567", "Dog", "Used dog", "2", "Dogs");
		
		// the inserted item is located at the last id
		LinkedList<Integer> sorted = new LinkedList<>(db.posts.keySet());
		Collections.sort(sorted);
		int lastId = sorted.getLast();
		
		
		Post insertedPost = db.posts.get(lastId);
		assertEquals(insertedPost.getOwnerId(), "s1234567");
		assertEquals(insertedPost.getTitle(), "Dog");
		assertEquals(insertedPost.getDescription(), "Used dog");
		assertEquals(insertedPost.getCategory(), "Dogs");
		assertEquals(insertedPost.getPrice(), "2");
		
		// remove all traces of post
		db.posts.remove(lastId);
		DatabaseRef.update("delete from sale where PostID = " + lastId);
		
	}

}

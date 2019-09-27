package javadb;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.sept.rest.webservices.restfulwebservices.post.Post;
import com.sept.rest.webservices.restfulwebservices.post.Post.Column;
import com.sept.rest.webservices.restfulwebservices.post.Post.Status;

class PostTest {

	
	private static DatabaseRef db;
	private static Post post;
	
	@BeforeAll
	// to avoid altering real table, create a test table
	static void setUpBeforeClass() throws Exception {
		
		Post.TABLE_NAME = "saleTest";
		
		db = new DatabaseRef();
		DatabaseRef.update("CREATE TABLE saleTest LIKE sale");
		
		for (int i=0; i++<8;)
			DatabaseRef.update("insert into saleTest(ID, Item_Name, Item_Description, Price, Status, Category) "
										+ "VALUES ('s3717497','dam','Used dam', 2, 'a', 'Automotive');");
		
		// choose ONE post out of list as test subject
		post = db.getPosts("select * from saleTest").get(4);
		
	}

	
	
	@AfterAll
	// delete the database test post table
	static void tearDownAfterClass() throws Exception {
		
		DatabaseRef.update("drop table saleTest");
	}



	@Test
	void testEdit() throws SQLException {
		post.edit(Column.NAME, "EditedName");
		assertEquals(post.getTitle(), "EditedName");
		testDatabaseCellChange(Column.NAME, post.getId(), "EditedName");
	}
	
	@Test
	void testDelete() throws SQLException {
		post.delete();
		
		assertEquals(post.getStatus(), Status.DELETED);
		testDatabaseCellChange(Column.STATUS, post.getId(), "D");
	}
	
	@Test
	void testSold() throws SQLException {
		post.sold();
		
		assertEquals(post.getStatus(), Status.SOLD);
		testDatabaseCellChange(Column.STATUS, post.getId(), "S");
	}
	
	// actually check if database changed
	private void testDatabaseCellChange(Column column, Long id, String edit) throws SQLException
	{
		ResultSet data = DatabaseRef.query(String.format("select %s from saleTest where PostID =%d", column.key(), id));
		data.next();
		assertEquals(data.getString(1), edit);
	}
}

package javadb;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.sept.rest.webservices.restfulwebservices.todo.Post.Column;

import javadb.ChatBase.Overhead;
import javadb.ChatBase.Text;

class ChatBaseTest {

	private static ChatBase db;
	private static List<String> users;
	
	@BeforeAll
	// to avoid altering real table, create a test table, that gets deleted later
	static void setUpBeforeClass() throws Exception {
		
	
		db = new ChatBase();
		DatabaseRef.update(String.format("CREATE TABLE %s LIKE %s", "overheadTest", ChatBase.OVERHEAD_TABLE));
		DatabaseRef.update(String.format("CREATE TABLE %s LIKE %s", "textTest", ChatBase.TEXT_TABLE));
		
		ChatBase.OVERHEAD_TABLE = "overheadTest";
		ChatBase.TEXT_TABLE = "textTest";
		
		getAllUsers();
	}

	private static void getAllUsers() throws SQLException
	{
		ResultSet data = DatabaseRef.query(String.format("select %s from %s", "ID", "login"));
		users = new ArrayList<>();
		while (data.next())
			users.add(data.getString(1));
		System.out.println(users);
		
		if (users.size() < 4)
			throw new SQLException("not enough users to start testing");
	}
	
	
	@AfterAll
	static void tearDownAfterClass() throws Exception 
	{	
		DatabaseRef.update("drop table overheadTest");
		DatabaseRef.update("drop table textTest");
	}
	
	@BeforeEach
	// remove traces to avoid conflicting with future tests
	void clearTables() throws SQLException 
	{
		DatabaseRef.update(String.format("delete from overheadTest"));
		DatabaseRef.update(String.format("delete from textTest"));
	}
	
	
	
	
	
	
	
	
	
	

	@Test
	void createChat() throws SQLException 
	{
		db.createChat(users.get(0), users.get(1));
		
		// check if database has changed
		ResultSet data = db.pointToLastValue(ChatBase.OVERHEAD_TABLE);
		assertEquals(users.get(0), data.getString(2));
		assertEquals(users.get(1), data.getString(3));
	}
	
	@Test
	void cannotCreateChatWithWrongUsers() throws SQLException 
	{
		assertThrows(NullPointerException.class, () -> {
			db.createChat("notAuserInDatabase", users.get(0));
	    });
	}
	
	@Test
	void cannotCreate2ChatsWithSameUsers() throws SQLException {
		
		db.createChat(users.get(0), users.get(1));
		db.createChat(users.get(2), users.get(1));
		
		assertThrows(NullPointerException.class, () -> {
			db.createChat(users.get(1), users.get(0));
	    });
		
	}
	
	
	
	
	
	
	

	@Test
	void addText() throws SQLException 
	{
			Overhead overhead = db.createChat(users.get(0), users.get(2));
			db.addText("Howdy, pardner", overhead.getChatID(), users.get(2));
			// check if database has changed
			ResultSet data = db.pointToLastValue(ChatBase.TEXT_TABLE);
			assertEquals(data.getInt(1), overhead.getChatID());
			assertEquals(data.getString(2), "Howdy, pardner");
			assertEquals(data.getString(3), users.get(2));
	}
	
	@Test
	void cannotAddTextWithWrongDetails() throws SQLException
	{
		Overhead overhead = db.createChat(users.get(0), users.get(2));
		assertThrows(NullPointerException.class, () -> {db.addText("Howdy, pardner", -1, users.get(2));});
		assertThrows(NullPointerException.class, () -> {db.addText("Howdy, pardner", overhead.getChatID(), "notAuserInDatabase");});
	}

	@Test
	void testRetrievetexts() throws SQLException {
		Overhead overhead = db.createChat(users.get(0), users.get(2));
		db.addText("Howdy, pardner", overhead.getChatID(), users.get(2));
		db.addText("... seen .....", overhead.getChatID(), users.get(0));
		db.addText("I want a divorce.", overhead.getChatID(), users.get(0));
		int chatID = overhead.getChatID();
		
		List<Text> texts = db.retrievetexts(chatID);
		assertEquals(texts.size(), 3);
		for (Text text : texts)
			assertEquals(chatID, text.getChatID());
	}

}

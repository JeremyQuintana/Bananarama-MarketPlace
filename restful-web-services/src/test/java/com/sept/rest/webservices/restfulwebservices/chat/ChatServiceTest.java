package com.sept.rest.webservices.restfulwebservices.chat;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import com.sept.rest.webservices.restfulwebservices.post.Post;
import com.sept.rest.webservices.restfulwebservices.post.PostRepository;
import com.sept.rest.webservices.restfulwebservices.post.PostService;

@SpringBootTest
@TestPropertySource(properties = "spring.datasource.url= ${spring.datasource.urltest}")
class ChatServiceTest {

	@MockBean
	private ChatRepository db;
	@Autowired
	private ChatService service;
	
	private static String reuben;
	private static String jeremy;
	private static String mitch;
	private static String mateo;
	private static String val;
	private List<Chat> chats;
	
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception 
	{
		reuben = "s3717497";
		jeremy = "s3719476";
		mitch = "s3707187";
		mateo = "s2182391";
		val = "s3179201";
	}
	
	@BeforeEach
	void setUp() throws Exception 
	{
		
		chats = new ArrayList<Chat>();
		chats.add(new Chat("Hey got some bad news", reuben, mateo));
		chats.add(new Chat("The backend doesn't work.", reuben, mateo));
		chats.add(new Chat("WAAAAT, whyyyy", mateo, reuben));		
		
		// a common user, to test robustness of methods
		chats.add(new Chat("I wanna buy the banana", reuben, mitch));
		chats.add(new Chat("sorry, it's priceless", mitch, reuben));
		
		
		// continuation of previous chat, but order is changed
		// this may confuse the chat returning scheme
		chats.add(new Chat("dw, the good news is that we can still test the frontend"
				+ " as you should test if you can retrieve this block first", reuben, mateo));
		
		// set the id based on its array list index
		for (int i=0; i<chats.size(); i++)
			chats.get(i).setId(new Long(i));
	}
	

	// testing successful return of chats between 2 users unordered
	@Test
	void testAllChats() 
	{
		List<Chat> expected = new ArrayList<>();
		for (Chat chat : chats)
			if (chat.getSender().equals(reuben) && chat.getReceiver().equals(mateo)
			||	chat.getSender().equals(mateo) && chat.getReceiver().equals(reuben))
				expected.add(chat);
		// order doesn't matter
		Mockito.when(db.findall(reuben, mateo)).thenReturn(expected);
		Mockito.when(db.findall(mateo, reuben)).thenReturn(expected);
		// does not return chats between reuben and mitch
		// all chats return in same order, and same size
		assertEquals(expected, service.allChats(reuben, mateo));
		assertEquals(expected, service.allChats(mateo, reuben));
	}
	
	//  you cannot chat with yourself, negative testing
	@Test
	void testCannotChatWithSelf() 
	{
		Mockito.when(db.findall(reuben, reuben)).thenReturn(new ArrayList<>());
		assertThrows(IllegalArgumentException.class, () -> {service.allChats(reuben, reuben);});
	}
	
	
	// testing empty chat return if user hasn't chatted before
	@Test
	void testNoChatsFound() 
	{
		// order doesn't matter
		Mockito.when(db.findall(reuben, val)).thenReturn(new ArrayList<>());
		Mockito.when(db.findall(val, reuben)).thenReturn(new ArrayList<>());
		// does not return chats between reuben and mitch
		// all chats return in same order, and same size
		assertEquals(new ArrayList<>(), service.allChats(reuben, mateo));
		assertEquals(new ArrayList<>(), service.allChats(mateo, reuben));
	}

	
	
	@Test
	void testAllChatsAfterId() 
	{
		// we need to check for various last ids
		for (int id = 0; id <= chats.size() + 1; id++)
		{
			// order doesn't matter
			Mockito.when(db.findall(reuben, mateo)).thenReturn(new ArrayList<>(chats));
			Mockito.when(db.findall(mateo, reuben)).thenReturn(new ArrayList<>(chats));
			
			final long lastID = new Long(2);
			chats.removeIf(chat -> chat.getId() <= lastID);
			// order doesn't matter
			Mockito.when(db.findByIdGreaterThan(lastID)).thenReturn(chats);
			Mockito.when(db.findByIdGreaterThan(lastID)).thenReturn(chats);
			
			assertEquals(chats, service.allChatsAfterId(lastID, reuben, mateo));
			assertEquals(chats, service.allChatsAfterId(lastID, mateo, reuben));
		}
	}
	
	
	// any user that the owner has a chat with before
	@Test
	void testAllUsersInteractedWith() {
		String owner = reuben;
		Set<String> users = new HashSet<>();
		for (Chat chat : chats)
		{
			// if the receiver is the owner, the sender is someone else (the user)
			// and vice versa
			String receiver = chat.getReceiver();
			String sender = chat.getSender();
			users.add(sender.equals(owner) ? receiver : sender);
		}
		Mockito.when(db.findBySenderOrReceiver(reuben, reuben)).thenReturn(chats);
		assertEquals(new ArrayList<>(users), service.allUsersInteractedWith(owner));
	}

}

package com.sept.rest.webservices.restfulwebservices.chat;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;

import com.sept.rest.webservices.restfulwebservices.jwt.JwtTokenUtil;

import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@TestPropertySource(properties = "spring.datasource.url= ${spring.datasource.urltest}")
@SpringBootTest
@AutoConfigureMockMvc
class ChatControllerTest {
	
	@MockBean
	private ChatService service;
	@Autowired
	private ChatController controller;
	@Autowired
	private MockMvc mvc;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	private static String reuben;
	private static String jeremy;
	private static String mitch;
	private static String mateo;
	private static String val;
	private List<Chat> chats;
	private static MockHttpServletRequest mockAuthentication;
	
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
		mockAuthentication = new MockHttpServletRequest();
	}

	@Test
	void testLoadChat() throws Exception 
	{
		List<Chat> expected = new ArrayList<>();
		for (Chat chat : chats)
			if (chat.getSender().equals(reuben) && chat.getReceiver().equals(mateo)
			||	chat.getSender().equals(mateo) && chat.getReceiver().equals(reuben))
				expected.add(chat);

		Mockito.when(service.allChats(reuben, mateo)).thenReturn(expected);
		Mockito.when(service.allChats(mateo, reuben)).thenReturn(expected);
		
		// does not return chats between reuben and mitch
		// all chats return in same order, and same size
		assertEquals(expected, controller.loadChat(reuben, mateo));
		assertEquals(expected, controller.loadChat(mateo, reuben));
	}
	
	// can't load a chat with self
	@Test
	void testFailLoadBetweenSameUser() throws Exception 
	{
		Mockito.when(service.allChats(val, val)).thenThrow(IllegalArgumentException.class);
		assertThrows(IllegalArgumentException.class, ()->{controller.loadChat(val, val);});
	}

	@Test
	void testDeleteChatUnauthenticatedUser() 
	{
		// the request owner does not match the frontend owner, invalid.
		addOwnerIDToHeader("NOT the correct user");
		assertThrows(NullPointerException.class, ()->{controller.deleteChat(reuben, mateo, mockAuthentication);});
	}


	@Test
	void testUserList() 
	{
		List<String> reubensUsers = new ArrayList<>();
		reubensUsers.add(mitch);
		reubensUsers.add(mateo);
		Mockito.when(service.allUsersInteractedWith(reuben)).thenReturn(reubensUsers);
		assertEquals(reubensUsers, controller.userList(reuben));
	}

	private void addOwnerIDToHeader(String ownerId)
	{
		mockAuthentication.addHeader("Authorization", "Bearer: " + jwtTokenUtil.generateToken(ownerId));
	}
}

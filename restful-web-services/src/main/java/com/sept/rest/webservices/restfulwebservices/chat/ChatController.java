package com.sept.rest.webservices.restfulwebservices.chat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sept.rest.webservices.restfulwebservices.jwt.JwtTokenUtil;
import com.sept.rest.webservices.restfulwebservices.post.Post;
import com.sept.rest.webservices.restfulwebservices.user.UserService;



// OLD 
//chat_overhead:
//Chat!D		User1 		User2 		
//-------------------------------
//1			s123456		s238197		
//2			s821712		s128719`	
//3			s201938		s281929
//
//
//chat_text
//ChatID		Text			Sender
//-----------------------------------------------------------
//3			Howdy				s281929
//1			Hey its duncan...	s123456
//2			Straight fire ...	s128719
//1			Dont talk to m...	s238197

// NEW
//Text			Receiver			Sender
//-------------------------------------------
//Howdy 		s1234567			s1299929
//Hey its dun..	s3717777			s1234567
//Straight fi.. s1234567			s1234567
//Dont talk t.. s1234567			s1234567



@CrossOrigin(origins="${spring.crossorigin.url}")
@RestController
public class ChatController {
	
	// to handle owner authentication requests
	// contains the jwtUtilToken
	@Autowired
    private UserService checker;
	
	@Autowired
	private ChatService service;
	
	@GetMapping("/chat/{user1}and{user2}")
	public List<Chat> loadChat(@PathVariable String user1, @PathVariable String user2) throws SQLException
	{					
		return service.allChats(user1, user2);
	}
	
	//when you want the latest chats between 2 users 
	// give the last chat that is stored frontend 
	// and any chats after will be returned 
	@GetMapping("/chat/{user1}and{user2}afterID{lastId}") 
	public List<Chat> loadChatAfter(@PathVariable String user1, @PathVariable String user2, @PathVariable Long lastId) throws SQLException 
	{ 
		return service.allChatsAfterId(lastId, user1, user2);
	}
	
	// only deletes chat that the user sen
	@DeleteMapping("/chat/{sender}and{receiver}")
	public void deleteChat(@PathVariable String sender, @PathVariable String receiver, HttpServletRequest request) 
	{
		if (checker.isEqual(sender, request)) 
			service.deleteAllChats(sender, receiver);
	}
	
	
	
	@PostMapping("/chat")
	public void addChat(@RequestBody Chat chat) throws SQLException
	{					
		service.add(chat);
	}

	// returns a list of owner id's
	@GetMapping("/chat/{owner}") 
	public List<String> userList(@PathVariable String owner)
	{
		System.out.println(service.allUsersInteractedWith(owner));
		return service.allUsersInteractedWith(owner);
	}
	

	
	
	
	
	
	
	
	
	

}
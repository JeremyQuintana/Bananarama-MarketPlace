package com.sept.rest.webservices.restfulwebservices.chat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;



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
	
	@Autowired
	private ChatService service;
	
	
	
	
	@GetMapping("/chat/{user1}and{user2}")
	public List<Chat> loadChat(@PathVariable String user1, @PathVariable String user2) throws SQLException
	{					
//		for (Chat chat : service.allChats(user1, user2))
//			System.out.println(chat);
		return service.allChats(user1, user2);
	}
	
	
	
	@PostMapping("/chat")
	public void addChat(@RequestBody Chat chat) throws SQLException
	{															
		service.addChat(chat);
	}

	
	
	
	
	
	
	
	
//	// sender matches a user from one of the overheads
//	private boolean senderANDChatIDCorrect(Long id, String sender) throws SQLException
//	{
//		return overheadDB.findByChatIdAndUser1OrUser2(id, sender).isEmpty();
//		
//		return valuesExist(String.format("SELECT count(*) FROM %s WHERE %s = %d AND (%s = '%s' OR %s = '%s')", 
//		OVERHEAD_TABLE, "ChatID", id,  "User1", sender, "User2", sender));
//	}
//	
//	public boolean usersExist(String user1, String user2) throws SQLException
//	{
//		return 	valuesExist(String.format("SELECT count(*) FROM %s WHERE %s = '%s'", "login", "ID", user1))
//		&&	valuesExist(String.format("SELECT count(*) FROM %s WHERE %s = '%s'", "login", "ID", user2));
//	}
//	// can't create multiple chats between same users
//	public boolean overheadAlreadyExists(String user1, String user2) throws SQLException
//	{
//		return service.getId(user1, user2);
//		return valuesExist(String.format("SELECT count(*) FROM %s WHERE (%s = '%s' AND %s = '%s') OR (%s = '%s' AND %s = '%s')", 
//		OVERHEAD_TABLE, "User1", user1, "User2", user2, "User2", user1, "User1", user2));
//	
//	}
	
	

}
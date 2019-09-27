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

@CrossOrigin(origins="http://localhost:3000")
@RestController
public class ChatController {
	
	@Autowired
	private ChatService service;
	
	
	
	
	@GetMapping("/chat/{user1}and{user2}")
	public List<Text> createOrLoadChat(@PathVariable String user1, @PathVariable String user2) throws SQLException
	{					
//		if (!usersExist(user1, user2))				
//			throw new NullPointerException("Users do not exist in database.");
//		
//		// users have chatted before
//		if (overheadAlreadyExists(user1, user2))
//			return allTexts(user1, user2);
			
		// insert user1, user2 into overhead repository
		overheadDB.save(new Overhead(user1, user2));
		// new chat so no messages
		return new ArrayList<>();
	}
	
	
	
	@PostMapping("/chat")
	public void addText(@RequestBody Text text) throws SQLException
	{															
		// both chat id and sender should already exist
		if (!senderANDChatIDCorrect(text.getChatID(), text.getSender()))	
			throw new NullPointerException("wrong sender in chat.");
		
		textDB.save(text);
	}

	
	
	
	
	
	
	
	
	// sender matches a user from one of the overheads
	private boolean senderANDChatIDCorrect(Long id, String sender) throws SQLException
	{
		return overheadDB.findByChatIdAndUser1OrUser2(id, sender).isEmpty();
		
		return valuesExist(String.format("SELECT count(*) FROM %s WHERE %s = %d AND (%s = '%s' OR %s = '%s')", 
		OVERHEAD_TABLE, "ChatID", id,  "User1", sender, "User2", sender));
	}
	
	public boolean usersExist(String user1, String user2) throws SQLException
	{
		return 	valuesExist(String.format("SELECT count(*) FROM %s WHERE %s = '%s'", "login", "ID", user1))
		&&	valuesExist(String.format("SELECT count(*) FROM %s WHERE %s = '%s'", "login", "ID", user2));
	}
	// can't create multiple chats between same users
	public boolean overheadAlreadyExists(String user1, String user2) throws SQLException
	{
		return service.getId(user1, user2);
		return valuesExist(String.format("SELECT count(*) FROM %s WHERE (%s = '%s' AND %s = '%s') OR (%s = '%s' AND %s = '%s')", 
		OVERHEAD_TABLE, "User1", user1, "User2", user2, "User2", user1, "User1", user2));
	
	}
	
	

}
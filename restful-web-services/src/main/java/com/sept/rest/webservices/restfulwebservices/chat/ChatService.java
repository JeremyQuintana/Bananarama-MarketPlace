package com.sept.rest.webservices.restfulwebservices.chat;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.sept.rest.webservices.restfulwebservices.post.Post;
import com.sept.rest.webservices.restfulwebservices.post.PostRepository;

@Service
public class ChatService {

	@Autowired
	private ChatRepository db;

	
	
	public List<Chat> allChats(String user1, String user2) throws SQLException
	{
		List<Chat> allChats = db.findBySenderAndReceiver(user1, user2);
		allChats.addAll(db.findBySenderAndReceiver(user2, user1));
		return allChats;
	}
	
	public void addChat(Chat chat)
	{
		db.save(chat);
	}
}

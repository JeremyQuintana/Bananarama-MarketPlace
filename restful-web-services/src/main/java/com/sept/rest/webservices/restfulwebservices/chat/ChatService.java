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


	
	public List<Chat> allChats(String user1, String user2) 
	{
		//List<Chat> allChats = db.findBySenderAndReceiver(user1, user2);
		//allChats.addAll(db.findBySenderAndReceiver(user2, user1));
		List<Chat> allChats = db.findall(user1, user2);
		for (int i = 0; i < allChats.size(); i++) {
			System.out.println(allChats.get(i));
		}
		return allChats;
	}
	
	public List<Chat> allChatsAfterId(Long lastID, String user1, String user2) 
	{
		List<Chat> chats = allChats(user1, user2);
		chats.retainAll(getChatsAfter(lastID));
		return chats;
	}
	
	public void deleteAllChats(String user1, String user2)
	{
		db.deleteBySenderAndReceiver(user1, user2);
		db.deleteBySenderAndReceiver(user2, user1);
	}
	
	public void add(Chat chat)
	{
		db.save(chat);
	}
	
	public List<Chat> getChatsAfter(Long id)
	{
		return db.findByIdGreaterThan(id);
	}
	
}

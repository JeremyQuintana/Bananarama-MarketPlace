package com.sept.rest.webservices.restfulwebservices.chat;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

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


	// returns all chats between the 2 users, regardless of who is user1/user2
	public List<Chat> allChats(String user1, String user2) 
	{
		if (user1.equals(user2))
			throw new IllegalArgumentException("You can't chat with yourself!");
		return db.findall(user1, user2);
	}
	
	// this is so we can call the latest chats frontend, whenever frontend makes a call
	// while a chat is ongoing
	public List<Chat> allChatsAfterId(Long lastID, String user1, String user2) 
	{
		List<Chat> chats = allChats(user1, user2);
		chats.retainAll(getChatsAfter(lastID));
		return chats;
	}
	
	// ONLY delete those that the sender sent.
	public void deleteAllChats(String sender, String receiver)
	{
		db.deleteBySenderAndReceiver(sender, receiver);
	}
	
	public void add(Chat chat)
	{
		db.save(chat);
	}
	
	public List<Chat> getChatsAfter(Long id)
	{
		return db.findByIdGreaterThan(id);
	}
	

	// any user the owner has had a chat with before
	public List<String> allUsersInteractedWith(String owner)
	{
		Set<String> users = new HashSet<>();
		for (Chat chat : db.findBySenderOrReceiver(owner, owner))
		{
			// if the receiver is the owner, the sender is someone else (the user)
			// and vice versa
			String receiver = chat.getReceiver();
			String sender = chat.getSender();
			users.add(sender.equals(owner) ? receiver : sender);
		}
		return new ArrayList<String>(users);
	}
	
}

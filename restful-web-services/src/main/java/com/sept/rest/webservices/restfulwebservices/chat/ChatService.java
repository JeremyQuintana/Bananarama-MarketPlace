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
	private TextRepository textDB;
	@Autowired
	private OverheadRepository overheadDB;

	
	public Long getId(String user1, String user2)
	{
		return overheadDB.findFirstByUser1AndUser2(user1,  user2).getChatID();
	}
	
	public List<Text> allTexts(String user1, String user2) throws SQLException
	{
		return textDB.findByChatId(getId(user1, user2));
	}
}

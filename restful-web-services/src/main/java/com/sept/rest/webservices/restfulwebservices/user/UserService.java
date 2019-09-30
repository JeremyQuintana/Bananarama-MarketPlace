package com.sept.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.sept.rest.webservices.restfulwebservices.post.PostService;

@Service
public class UserService {

//	@Autowired
//	private JpaRepository<User, String> db;

	// return logged in user
	public User getLoggedUser() 	
	{
		return null;
//		return db.findAll().get(0);
	}
	
	public void logout()
	{
		
//		db.deleteAll();
	}
	
	public void login(User user)
	{
//		if (db.count() > 0)
//			throw new IllegalArgumentException("a user has already been logged in.");
//		db.save(user);
	}

}

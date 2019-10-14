package com.sept.rest.webservices.restfulwebservices.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.sept.rest.webservices.restfulwebservices.jwt.JwtTokenUtil;
import com.sept.rest.webservices.restfulwebservices.post.PostService;

@Service
public class UserService {

////	@Autowired
////	private JpaRepository<User, String> db;
//
//	// return logged in user
//	public User getLoggedUser() 	
//	{
//		return null;
////		return db.findAll().get(0);
//	}
//	
//	public void logout()
//	{
//		
////		db.deleteAll();
//	}
//	
//	public void login(User user)
//	{
////		if (db.count() > 0)
////			throw new IllegalArgumentException("a user has already been logged in.");
////		db.save(user);
//	}
	@Autowired
    private JwtTokenUtil jwtTokenUtil;
	
	public String getOwnerId(HttpServletRequest request)
	{
		return jwtTokenUtil.getUsernameFromToken(request.getHeader("Authorization").substring(7));
	}
	
	public boolean isEqual(String owner, HttpServletRequest request)
	{
		String loggedOwner =getOwnerId(request);
		
		if (!owner.equals(loggedOwner)) {
			throw new NullPointerException("Error Update: Edit User ID " + loggedOwner + " does not match Item Owner ID " + owner);
		}
		return true;
	}

}

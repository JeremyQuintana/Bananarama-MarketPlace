package com.sept.rest.webservices.restfulwebservices.user;

import java.awt.image.BufferedImage;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

import com.fasterxml.jackson.annotation.JsonIgnore;

//import com.sept.rest.webservices.restfulwebservices.todo.Post.Column;

import javadb.DatabaseRef;

@Entity
public class User {
	
	// variables need to be above constructor for json
	private String name;
	private String age;
	private String email;
	
	// a bug prevents the above values being stored in a constructor, 
	// used to work before but now doesn't
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;
	
	// annoyingly jpa 2.0 needs this
	public User(){}
	
	@JsonIgnore
	public User(String name, String age, String email)
	{
		this.name = name;
		this.age = age;
		this.email = email;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public String toString()
	{
		return id + " " + name + " " +age + " " + email + " ";
	}
	
	public String getId()			{return id;}
	public void setId(String id)	{this.id = id;}
	
}

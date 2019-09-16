package com.sept.rest.webservices.restfulwebservices.chat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chat_overhead")
public class Overhead
{
	@Id
	@GeneratedValue
	private Long id;
	private String user1;
	private String user2;
	
	// annoyingly jpa 2.0 needs this
	public Overhead(){}
	
	public Overhead(String user1, String user2)
	{
		this.user1 = user1;
		this.user2 = user2;
	}
	
	public Long getChatID() {return id;}
}

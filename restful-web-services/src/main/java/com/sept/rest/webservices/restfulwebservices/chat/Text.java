package com.sept.rest.webservices.restfulwebservices.chat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "chat_text")
public class Text
{
	@Id
	@GeneratedValue
	private Long chatID;
	private String text;
	private String sender;
	
	// annoyingly jpa 2.0 needs this
	public Text(){}
	
	public Text(String text, String sender)
	{
		this.text = text;
		this.sender = sender;
	}
	
	public Long getChatID() {return chatID;}
	public String getSender() {return sender;}
}

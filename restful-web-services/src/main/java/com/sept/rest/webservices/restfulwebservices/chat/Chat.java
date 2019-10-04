package com.sept.rest.webservices.restfulwebservices.chat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "chat_text")
public class Chat
{
	private String text;
	private String sender;
	private String receiver;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	// annoyingly jpa 2.0 needs this
	public Chat(){}
	
	@JsonIgnore
	public Chat(String text, String sender, String receiver)
	{
		this.text = text;
		this.sender = sender;
		this.receiver = receiver;
	}
	
	public String toString()
	{
		return String.format("Text: %s, Sender: %s, Receiver: %s", text, sender, receiver);
	}
	
	public String getSender() {return sender;}
	public String getReceiver() {return receiver;}
	public String getText() {return text;}
}

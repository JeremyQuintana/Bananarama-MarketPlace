package com.sept.rest.webservices.restfulwebservices.chat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "chat_text")
public class Text
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String text;
	private String sender;
	
	// annoyingly jpa 2.0 needs this
	public Text(){}
	
	@JsonIgnore
	public Text(String text, String sender)
	{
		this.text = text;
		this.sender = sender;
	}
	
	public Long getId() {return id;}
	public String getSender() {return sender;}
}

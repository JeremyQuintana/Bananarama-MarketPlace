package com.sept.rest.webservices.restfulwebservices.chat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sept.rest.webservices.restfulwebservices.post.Post;

@Entity
@Table
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
		return String.format("Id: %d, Text: %s, Sender: %s, Receiver: %s", id, text, sender, receiver);
	}
	
	@Override
	public int hashCode() 
	{
		return 31 + ((id == null) ? 0 : id.hashCode());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) 					return true;
		if (obj == null) 					return false;
		if (getClass() != obj.getClass())	return false;
		Chat other = (Chat) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public String getSender() {return sender;}
	public String getReceiver() {return receiver;}
	public String getText() {return text;}
}

package com.sept.rest.webservices.restfulwebservices.chat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository  extends JpaRepository<Chat, Long> 
{
	public List<Chat> findBySenderAndReceiver(String sender, String receiver);
	public void deleteBySenderAndReceiver(String sender, String receiver);
	public List<Chat> findByIdGreaterThan(Long id);
}

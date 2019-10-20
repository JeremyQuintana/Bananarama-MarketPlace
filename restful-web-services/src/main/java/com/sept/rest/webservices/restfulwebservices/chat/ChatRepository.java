package com.sept.rest.webservices.restfulwebservices.chat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface ChatRepository  extends JpaRepository<Chat, Long> 
{
	public List<Chat> findBySenderAndReceiver(String sender, String receiver);
	public List<Chat> findBySenderOrReceiver(String sender, String receiver);
	@Transactional
	public void deleteBySenderAndReceiver(String sender, String receiver);
	public List<Chat> findByIdGreaterThan(Long id);
	
	// regardless of order, get the chat if the 2 users match
	@Transactional
	@Modifying
	@Query(value = "select p from Chat p where p.sender = :user1 and p.receiver = :user2 or  p.sender = :user2 and p.receiver = :user1")
	List<Chat> findall(@Param("user1") String user1, @Param("user2") String user2);
}

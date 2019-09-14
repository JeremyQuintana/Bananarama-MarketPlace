package com.sept.rest.webservices.restfulwebservices.chat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TextRepository  extends JpaRepository<Text, Long> 
{
//	public List<Text> findByChatId(Long id);
}

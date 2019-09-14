package com.sept.rest.webservices.restfulwebservices.chat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OverheadRepository extends JpaRepository<Overhead, Long> {

	// and with this, the method is already implemented
	// jpa MAGICALLY uses the method name to know what to look for
	public Overhead findFirstByUser1AndUser2(String user1, String user2);
	
	/*wrong name sender??*/
//	public List<Overhead> findByIdAndSender(Long id, String sender);
}

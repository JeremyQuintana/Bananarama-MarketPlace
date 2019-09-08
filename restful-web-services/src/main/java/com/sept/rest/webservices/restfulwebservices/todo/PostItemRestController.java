package com.sept.rest.webservices.restfulwebservices.todo;
import javadb.DatabaseRef;

import java.sql.SQLException;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="http://localhost:3000")
@RestController
public class PostItemRestController {
	@PostMapping(value = "/postitem")
	public void postItem(@RequestBody PostItem item) throws SQLException {
		DatabaseRef db= new DatabaseRef();
		
	  
	   	DatabaseRef.sell_item("s1234567", item.getName(), item.getDescription(), item.getCost(), item.getCatagory());
	}
		
}

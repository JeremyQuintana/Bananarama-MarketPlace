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
<<<<<<< HEAD
	   	int price = item.getCost();	
	   	DatabaseRef.sell_item("s1234567", item.getName(), item.getDescription(), price, item.getCatagory());
=======
		
	  
	   	DatabaseRef.sell_item("s1234567", item.getName(), item.getDescription(), item.getCost(), item.getCatagory());
>>>>>>> 7d048200088bd06d64836a40a054edf49994ad3d
	}
		
}

package com.sept.rest.webservices.restfulwebservices.todo;


import java.sql.SQLException;
import javadb.DatabaseRef;
import javadb.Post;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins="http://localhost:3000")
@RestController
public class PostItemRestController {
	
	public static void main(String[] args)
	{
		
	}
	@PostMapping(value = "/postitem")
	public void postItem(@RequestBody Post item) throws SQLException {
//		DatabaseRef db= new DatabaseRef();
//	   	int price = item.getCost();	
//	   	DatabaseRef.sell_item("s1234567", item.getName(), item.getDescription(), price, item.getCatagory());
	}
		
}

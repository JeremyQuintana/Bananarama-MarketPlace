package com.sept.rest.webservices.restfulwebservices.market;
import javadb.Database;
import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sept.rest.webservices.restfulwebservices.todo.Todo;

import javadb.Database;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class MarketResourceHardcoded {
	
//	// Send the posts array from this backend to the frontend via localhost:8080/posts
//	@GetMapping("/posts")
//	public String[][] getAllTodos() throws Exception {
//		// Thread.sleep(3000);
//		Database db = new Database();
//		Database.check_for_salewrite();
//		
//		return Database.check_for_sale();
//}
}

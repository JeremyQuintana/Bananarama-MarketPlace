package com.sept.rest.webservices.restfulwebservices.market;

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

<<<<<<< HEAD
=======
import javadb.Database;

>>>>>>> 7d048200088bd06d64836a40a054edf49994ad3d
@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class MarketResourceHardcoded {
	
<<<<<<< HEAD
	// Send the posts array from this backend to the frontend via localhost:8080/posts
	@GetMapping("/posts")
	public String[][] getAllTodos() {
		// Thread.sleep(3000);
		return new String[][]{{"1", "Confetti", "Beautiful multicolored confetti. Used but like new.", "georgemichael99", "$20"},
		    {"2", "Green Capsicum", "Giant green capsicum. Found it at the beach. Still contains some sand.", "DONNYT1946", "$100"},
		    {"3", "Small Blue Star", "Blue star, fell from the sky into my backyard. Fits in pocket. Still warm.", "not_an_alien", "$0.50"},
		    {"4", "Vines - 50ft", "Green climbing vines. Organic, just cut. Perfect for a wedding.", "tree_hater", "$25.47"}};
=======
	 //Send the posts array from this backend to the frontend via localhost:8080/posts
	@GetMapping("/posts")
 public String[][] getAllTodos() throws Exception {
		// Thread.sleep(3000);
		Database db= new Database();
		Database.check_for_salewrite();
		return Database.check_for_sale(); 
				
>>>>>>> 7d048200088bd06d64836a40a054edf49994ad3d
	}
}

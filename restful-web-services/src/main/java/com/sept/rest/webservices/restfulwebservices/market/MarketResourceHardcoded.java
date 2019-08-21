package com.sept.rest.webservices.restfulwebservices.market;

import java.net.URI;
import java.sql.Array;
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
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.sept.rest.webservices.restfulwebservices.todo.Todo;

//@CrossOrigin(origins = "http://localhost:3000")
@CrossOrigin(origins = "http://localhost:8080/posts")
@RestController 
public class MarketResourceHardcoded {
	

	
	@GetMapping("/posts")
	public String[][] getAllTodos() {
		// Thread.sleep(3000);
	//	Database.check_for_sale();
		return new String[][]{{"1", "Beautiful multicolored confetti. Used but like new.", "georgemichael99", "$20"},
		    {"2", "Green Capsicum", "Giant green capsicum. Found it at the beach. Still contains some sand.", "DONNYT1946", "$100"},
		    {"3", "Small Blue Star", "Blue star, fell from the sky into my backyard. Fits in pocket. Still warm.", "not_an_alien", "$0.50"},
		    {"4", "Vines - 50ft", "Green climbing vines. Organic, just cut. Perfect for a wedding.", "tree_hater", "$25.47"}};

	}
}
// "Confetti",
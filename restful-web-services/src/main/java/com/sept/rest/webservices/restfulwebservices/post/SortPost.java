package com.sept.rest.webservices.restfulwebservices.post;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.*; 
import java.lang.*; 
import java.io.*; 
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Sort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javadb.DatabaseRef;



public class SortPost implements Comparator<Post> {
	@Override
	public int compare(Post o1, Post o) {
	 double price1=Double.valueOf(o1.getPrice());
	 double price2=Double.valueOf(o.getPrice());
		return (int) (price1 -price2);
	}

	

	}

	 

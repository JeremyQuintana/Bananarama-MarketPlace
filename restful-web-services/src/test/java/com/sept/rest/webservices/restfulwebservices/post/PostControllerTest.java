package com.sept.rest.webservices.restfulwebservices.post;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
//@WebMvcTest
@AutoConfigureMockMvc
class PostControllerTest {

	private static Post mockPost1;
	private static Post mockPost2;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		mockPost1 = new Post("s1234567", "Appleberry's", "Delicious", "2.222", "Fruit");
		mockPost1 = new Post("s1234567", "doodoo boo", "Cleans the backend leaving no traces", "9999", "Baby Wipes");
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}

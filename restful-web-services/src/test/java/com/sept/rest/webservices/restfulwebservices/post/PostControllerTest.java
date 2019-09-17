package com.sept.rest.webservices.restfulwebservices.post;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrint;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


/*we cannot test the link and request*/

@RunWith(SpringRunner.class)
@SpringBootTest
// this don't work, application context error
//@WebMvcTest(PostController.class)
@AutoConfigureMockMvc
class PostControllerTest {

	@MockBean
	private PostRepository db;
	@Autowired
	private PostController controller;
	
	@Autowired
	private MockMvc mvc;
	
	private static Post mockPost1;
	private static Post mockPost2;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		mockPost1 = new Post(new Long(2), "s1234567", "Appleberry's", "Delicious", "2.222", "Fruit");
		mockPost1 = new Post(new Long(5), "s1234567", "doodoo boo", "Cleans the backend leaving no traces", "9999", "Baby Wipes");
	}

	@Test
	void testAddPost() {
		Mockito.when(db.save(mockPost1)).thenReturn(mockPost1);
		assertEquals(mockPost1, controller.addPost(mockPost1));
	}
	
	@Test
	void testGetAllPosts() throws Exception {
		List<Post> posts = new ArrayList<>();
		posts.add(mockPost1);
		posts.add(mockPost2);
		
		Mockito.when(db.findAll()).thenReturn(posts);
		
		/*this isn't working as @...(secure = false) HAS BEEN DEPRECATED*/
//		mvc.perform(get("/posts")
//			      .accept(MediaType.APPLICATION_JSON))
//			      .andExpect(status().isOk())
//			      .andExpect(jsonPath("$.employees").exists())
//			      .andExpect(jsonPath("$.employees[*].employeeId").isNotEmpty());
		assertEquals(controller.getAllPosts(), posts);
	}

	@Test
	void testGetPost() throws Exception {
		
		Long id = mockPost1.getId();
//	    final Post post = new Post(new Long(2), "", " ", "", "", "");
	    Optional<Post> optionalPost = Optional.of(mockPost1);
	    Mockito.when(db.findById(id)).thenReturn(optionalPost);
//		Mockito.when(post).thenReturn(mockPost1);
		
		assertEquals(mockPost1, controller.getPost(id));
	}
	
	@Test
	void testUpdatePost() throws Exception 
	{
		Mockito.when(db.save(mockPost1)).thenReturn(mockPost1);
		assertEquals(mockPost1, controller.updatePost(mockPost1.getId(), mockPost1));
	}
	
	
//	@Test
//	public void testGetTicketByEmail() throws Exception {
//		Ticket mockTicket = new Ticket();
//		mockTicket.setTicketId(1);
//		mockTicket.setPassengerName("Martin Bingel");
//		mockTicket.setSourceStation("Kolkata");
//		mockTicket.setDestStation("Delhi");
//		mockTicket.setBookingDate(new Date());
//		mockTicket.setEmail("martin.s2017@gmail.com");
//		
//		String expectedJson = this.mapToJson(mockTicket);
//		
//		Mockito.when(ticketBookingService.getTicketByEmail(Mockito.anyString())).thenReturn(mockTicket);
//		
//		String URI = "/api/tickets/email/martin.s2017@gmail.com";
//		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
//				URI).accept(
//				MediaType.APPLICATION_JSON);
//
//		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
//		String outputInJson = result.getResponse().getContentAsString();
//		assertThat(outputInJson).isEqualTo(expectedJson);
//	
//	}
//
//	/**
//	 * Maps an Object into a JSON String. Uses a Jackson ObjectMapper.
//	 */
//	private String mapToJson(Object object) throws JsonProcessingException {
//		ObjectMapper objectMapper = new ObjectMapper();
//		return objectMapper.writeValueAsString(object);
//	}
}

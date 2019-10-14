package com.sept.rest.webservices.restfulwebservices.post;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcPrint;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.sept.rest.webservices.restfulwebservices.jwt.JwtTokenUtil;
import com.sept.rest.webservices.restfulwebservices.jwt.resource.JwtTokenResponse;


/*we cannot test the link and request*/

@RunWith(SpringRunner.class)
@TestPropertySource(properties = "spring.datasource.url= ${spring.datasource.urltest}")
@SpringBootTest
// this don't work, application context error
//@WebMvcTest(PostController.class)
@AutoConfigureMockMvc
class PostControllerTest {

	@MockBean
	private PostService service;
	@Autowired
	private PostController controller;
	private ImageController imageCtrl = Mockito.mock(ImageController.class);
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	private static Post post1;
	private static Post post2;
	private static Post post3;
	private static MockHttpServletRequest mockAuthentication;
	private static List<Post> posts;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		post1 = new Post(new Long(2), "s1234567", "Appleberry's", "Delicious", "2.222", "Fruit");
		post2 = new Post(new Long(5), "s1234567", "doodoo boo", "Cleans the backend leaving no traces", "9999", "Baby Wipes");
		post3 = new Post(new Long(6), "s3717497", "Pill", "Pickpocketed off Morpheus", "2", "Medecine");
		
		
		posts = new ArrayList<>();
		posts.add(post1);
		posts.add(post2);
		posts.add(post3);
		
	}
	
	@BeforeEach
	void setUp() {
		mockAuthentication = new MockHttpServletRequest();
	}

//	@Test
//	void testAddPost() {
//		addOwnerIDToHeader("s1234567");
//		Mockito.when(service.update(post1)).thenReturn(post1);
//		Post post = controller.addPost(post1, mockAuthentication);
//		
//		assertEquals(post1.getDescription(), post.getDescription());
//		assertEquals(post1.getPrice(), post.getPrice());
//		assertEquals(post1.getTitle(), post.getTitle());
//		assertEquals(post1.getCategory(), post.getCategory());
//	}
	
	@Test
	void testGetAllPosts() throws Exception {
		List<Post> posts = new ArrayList<>();
		posts.add(post1);
		posts.add(post2);
		
		Mockito.when(service.getAllAvailable()).thenReturn(posts);
		
		/*this isn't working as @...(secure = false) HAS BEEN DEPRECATED*/
//		mvc.perform(get("/posts")
//			      .accept(MediaType.APPLICATION_JSON))
//			      .andExpect(status().isOk())
//			      .andExpect(jsonPath("$.employees").exists())
//			      .andExpect(jsonPath("$.employees[*].employeeId").isNotEmpty());
		assertEquals(controller.getAllAvailablePosts(), posts);
	}

	@Test
	void testGetPost() throws Exception {
		
		Long id = post1.getId();
	    Mockito.when(service.get(id)).thenReturn(post1);
		assertEquals(post1, controller.getPost(id));
	}
	
//	@Test
//	void testUpdatePost() throws Exception 
//	{
//		addOwnerIDToHeader("s1234567");
//		Mockito.when(service.update(post1)).thenReturn(post1);
//		Mockito.when(service.get(post1.getId())).thenReturn(post1);
//
//		Post post = controller.updatePost(post1.getId(), post1, mockAuthentication);
//		
//		assertEquals(post1.getDescription(), post.getDescription());
//		assertEquals(post1.getPrice(), post.getPrice());
//		assertEquals(post1.getTitle(), post.getTitle());
//		assertEquals(post1.getCategory(), post.getCategory());
//		
//	}
	
	@Test 
	void testUpdatePostInvalidID() throws Exception 
	{
		addOwnerIDToHeader("random");
		Mockito.when(service.update(post1)).thenReturn(post1);
		Mockito.when(service.get(post1.getId())).thenReturn(post1);
		
		assertThrows(NullPointerException.class, () -> {
				controller.updatePost(post1.getId(), post1, mockAuthentication);
		});
	}
	
	
	
	
	@Test
	void testSort()
	{
		
//		Mockito.when().thenReturn();
		
	}
	
	private void addOwnerIDToHeader(String ownerId)
	{
		mockAuthentication.addHeader("Authorization", "Bearer: " + jwtTokenUtil.generateToken(ownerId));
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

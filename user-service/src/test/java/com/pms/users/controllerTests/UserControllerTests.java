package com.pms.users.controllerTests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.pms.users.controller.UserController;
import com.pms.users.model.User;
import com.pms.users.service.UserService;

@WebMvcTest(value = UserController.class)
public class UserControllerTests {
	@MockBean
	private UserService userService;
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeEach
	public void setup() {
//		User user= new User(101,"sp","7350593612","sp@gmail.com","pass","ROLE_DOCTOR");
//		User user1= new User(102,"sp1","9350593612","sp1@gmail.com","pass2","ROLE_DOCTOR");
//		List<User> userList=null;
//		userList.add(user1);
//		userList.add(user);

	}
//	@Test
//	public void saveUserTests() throws Exception {
//		
//		when(userService.saveUser(ArgumentMatchers.any())).thenCallRealMethod();
//		ObjectMapper mapper=new ObjectMapper();
//		User user= new User("sp","7350593612","sp@gmail.com","pass");
//		String userJson=mapper.writeValueAsString(user);
//		
//		MockHttpServletRequestBuilder reqBuilder=MockMvcRequestBuilders.post("/user/add")
//																		.contentType(MediaType.APPLICATION_JSON)
//																		.content(userJson);
//		
//		ResultActions perform=mockMvc.perform(reqBuilder);
//		MvcResult andReturn=perform.andReturn();
//		MockHttpServletResponse response=andReturn.getResponse();
//		int status=response.getStatus();
//		assertEquals(201,status);
//	}
	
//	@Test
//	public void getUsersTests() throws Exception{
//		User user= new User(101,"sp","7350593612","sp@gmail.com","pass","ROLE_DOCTOR");
//		User user1= new User(102,"sp1","9350593612","sp1@gmail.com","pass2","ROLE_DOCTOR");
//		List<User> userList=null;
//		userList.add(user1);
//		userList.add(user);
//		when(userService.getAll()).thenReturn(userList);
//		MockHttpServletRequestBuilder reqBuilder=MockMvcRequestBuilders.get("/user/getAll");
//
//ResultActions perform=mockMvc.perform(reqBuilder);
//
//MvcResult andReturn=perform.andReturn();
//MockHttpServletResponse response=andReturn.getResponse();
//int status=response.getStatus();
//assertEquals(200,status);
//		
//	}
	
	
	
	
	
	
	
}

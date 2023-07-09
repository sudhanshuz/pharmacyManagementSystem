package com.pms.users.controllerTests;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.pms.users.controller.UserController;
import com.pms.users.service.SequenceGeneratorService;
import com.pms.users.service.UserService;

@WebMvcTest(value = UserController.class)
public class UserControllerTests {
	@MockBean
	private UserService userService;
	@Autowired
	private MockMvc mockMvc;
	
	private UserController userController;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	@BeforeEach
	public void setup() {
//		User user= new User(101,"sp","7350593612","sp@gmail.com","pass","ROLE_DOCTOR");
//		User user1= new User(102,"sp1","9350593612","sp1@gmail.com","pass2","ROLE_DOCTOR");
//		List<User> userList=null;
//		userList.add(user1);
//		userList.add(user);
		


	}
	
}

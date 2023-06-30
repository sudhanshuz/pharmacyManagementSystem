package com.pms.users.serviceTests;

import static org.junit.jupiter.api.Assertions.*;


import java.util.List;
import java.util.Optional;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.pms.users.exceptions.ResourceNotFoundException;
import com.pms.users.model.User;
import com.pms.users.repository.UserRepository;
import com.pms.users.service.UserService;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceTest {
@Autowired
UserService userService;
@MockBean
private UserRepository userRepository;

@BeforeEach
private void setup() {

}
                                                         
@Test()
public void getUserByUserIdTest() throws ResourceNotFoundException {
	Optional<User> user=Optional.of(new User(1,"sp","7350593612","sp@gmail.com","pass","ROLE_DOCTOR"));
	
	Mockito.when(userRepository.findById(1)).thenReturn(user);
	assertEquals(user,userService.getUserByUserId(1));
}

@Test()
public void getUserByUserIdwhenIdisInvalid() throws ResourceNotFoundException {
	Optional<User> user=Optional.of(new User(2,"sp","7350593612","sp@gmail.com","pass","ROLE_DOCTOR"));
	
	Mockito.when(userRepository.findById(2)).thenReturn(user);
	Exception e= new ResourceNotFoundException("invalid user Id");
	assertEquals(e.getMessage(),userService.getUserByUserId(1));
}

}
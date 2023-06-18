package com.pms.users.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pms.users.model.User;
import com.pms.users.repository.UserRepository;
import com.pms.users.service.SequenceGeneratorService;
import com.pms.users.service.UserService;
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/add")
	//@PreAuthorize("hasAuthority('ADMIN')")
	public User saveUser(@RequestBody User user) {
		user.setUserId(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME));
		return userService.saveUser(user);
	}
	
	@GetMapping("/getAll")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<User> getUsers() {
		return userService.getAll();
	}
	
	@GetMapping("/getUserById/{userId}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public User getUserByUserId(@PathVariable String userId) {
		return userService.getUserByUserId(Long.parseLong(userId));
	}
	
	@PutMapping("/edit")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public User editUser(@RequestBody User user) {
		return userService.editUser(user);
	}
	@DeleteMapping("deleteById/{userId}")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public User deleteUserById(@PathVariable String userId) {
		return userService.deleteUserById(Long.parseLong(userId));
	}
	@GetMapping("/getByName/{name}")
	public User findByUserName(@PathVariable String name) {
		return userService.getByName(name);
		
	}
	
}

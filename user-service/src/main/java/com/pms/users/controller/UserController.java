package com.pms.users.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pms.users.model.User;
import com.pms.users.service.SequenceGeneratorService;
import com.pms.users.service.UserService;
@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private SequenceGeneratorService sequenceGeneratorService;
	
	@PostMapping("/add")
	public User saveUser(@RequestBody User user) {
		user.setUserId(sequenceGeneratorService.generateSequence(User.SEQUENCE_NAME));
		return userService.saveUser(user);
	}
	
	@GetMapping("/getAll")
	public List<User> getUsers() {
		return userService.getAll();
	}
	
	@GetMapping("/getUserById/{userId}")
	public User getUserByUserId(@PathVariable String userId) {
		return userService.getUserByUserId(Long.parseLong(userId));
	}
	
	@PutMapping("/edit")
	public User editUser(@RequestBody User user) {
		return userService.editUser(user);
	}
	@DeleteMapping("deleteById/{userId}")
	public User deleteUserById(@PathVariable String userId) {
		return userService.deleteUserById(Long.parseLong(userId));
	}
	
}

package com.pms.users.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pms.users.exceptions.ResourceNotFoundException;
import com.pms.users.model.User;
import com.pms.users.repository.UserRepository;
@Service
public class UserService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public User saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.insert(user);
	}

	public List<User> getAll() {
		return userRepository.findAll();
	}

	public Optional<User> getUserByUserId(int userId) throws ResourceNotFoundException {
		Optional<User> user=userRepository.findById(userId);
		if(user.isEmpty()) {
			throw new ResourceNotFoundException("invalid user Id");
		}
		return user;
	}

	public User editUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	public User deleteUserById(int userId) throws ResourceNotFoundException {
		User user=userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("invalid user Id"));
		userRepository.deleteById(userId);
		return user;
	}

	public Optional<User> getByName(String name) {
		return userRepository.findByName(name);
	}
	

}

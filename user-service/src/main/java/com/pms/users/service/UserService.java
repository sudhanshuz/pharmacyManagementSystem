package com.pms.users.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

	public User getUserByUserId(long userId) {
		User user=userRepository.findById(userId).orElse(null);
		return user;
	}

	public User editUser(User user) {
		return userRepository.save(user);
	}

	public User deleteUserById(long userId) {
		User user=userRepository.findById(userId).orElse(null);
		userRepository.deleteById(userId);
		return user;
	}

	public Optional<User> getByName(String name) {
		return userRepository.findByName(name);
	}
	

}

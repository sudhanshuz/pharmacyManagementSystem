package com.pms.users.config;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.pms.users.model.User;
import com.pms.users.repository.UserRepository;
@Service
public class UserInfoUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	

	
	@Override
	public UserDetails loadUserByUsername(String username) {
		User user=userRepo.findByName(username);
		if(user==null) {
			throw new UsernameNotFoundException("not found");
		}
		return new UserInfoDetails(user);}
}

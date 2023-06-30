package com.pms.users.config;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.pms.users.model.User;
import com.pms.users.repository.UserRepository;
@Service
@CrossOrigin("*")
public class UserInfoUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;
	
//	@Autowired
//	public UserInfoUserDetailsService(UserRepository userRepo) {
//		this.userRepo=userRepo;
//	}
	
	@Override
	public UserDetails loadUserByUsername(String username) {
		Optional<User> user=userRepo.findByName(username);
	return user.map(UserInfoDetails::new)
			.orElseThrow(()->new UsernameNotFoundException("invalid username"));	
	}
}

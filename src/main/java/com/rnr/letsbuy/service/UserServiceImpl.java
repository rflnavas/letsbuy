package com.rnr.letsbuy.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.rnr.letsbuy.exception.UserNotFoundException;
import com.rnr.letsbuy.model.User;
import com.rnr.letsbuy.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService, UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;
	
	public User getUserByEmail(String email) throws UserNotFoundException{
		return userRepository.findByEmail(email);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = Optional.ofNullable(userRepository.findByEmail(username));
		if(!user.isPresent()){
			throw new UserNotFoundException("User with email : " + username + " wasn't found");
		}
		return new UserDetailsImpl(user.get());
	}
}

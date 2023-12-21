package com.example.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.repository.UserRepository;

@Service
public class MyUserDetailService implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByUsername(username);
		// return user.map(UserPrincipal::new).orElseThrow(() -> new
		// UsernameNotFoundException("user not found"));
		if (user == null)
			throw new UsernameNotFoundException("User 403");
		return new UserPrincipal(user);

	}

	public User addUser(User user) {
		// TODO Auto-generated method stub

		return userRepository.save(user);
	}

	public User getByUsername() {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(null);
	}

	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	public User updateUser(User user) throws UsernameNotFoundException {
		// TODO Auto-generated method stub

		Optional<User> optional = userRepository.findById((int) user.getId());

		if (optional.isPresent()) {
			User _user = userRepository.findById(user.getId()).get();
			_user.setUsername(user.getUsername());
			_user.setPassword(user.getPassword());
			_user.setRoles(user.getRoles());

			return userRepository.save(_user);
		} else {
			throw new UsernameNotFoundException("User not found with the matching ID!");
		}

		// return optional.get();
	}

}

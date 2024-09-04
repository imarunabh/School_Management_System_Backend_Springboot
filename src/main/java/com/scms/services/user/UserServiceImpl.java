package com.scms.services.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scms.entities.User;
import com.scms.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
  @Autowired
  private UserRepository userRepository;

	@Override
	public User findById(Long id) {
		 Optional<User> user = userRepository.findById(id);
		 if(user.isPresent()) {
			 return user.get();
		 }
		 return null;
		
	}

}

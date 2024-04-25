package com.socgen.crudapi.service.impl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.socgen.crudapi.Exception.ResourceNotFoundException;
import com.socgen.crudapi.entity.User;
import com.socgen.crudapi.repository.UserRepository;
import com.socgen.crudapi.service.UserService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public User getUserById(Long userId) {
	   Optional<User> singleUser  = userRepository.findById(userId);
	   if(singleUser.isPresent()) {
			return singleUser.get();
	   }
	   else
	   {
		   throw new ResourceNotFoundException("Record not found for " + userId);
	   }
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public void deleteUser(Long userId) {
		User existingUser = this.userRepository.findById(userId)
				.orElseThrow(()->new ResourceNotFoundException("User not found for the given" + userId));
	   userRepository.deleteById(userId);
		
	}

	@Override
	public User updateUser(User user) {
		Optional <User> userDb = this.userRepository.findById(user.getId());
		if (userDb.isPresent()) {
		User existingUser = userDb.get();
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		User updateUser = userRepository.save(existingUser);
		return updateUser;
		}
		else
		{
			throw new ResourceNotFoundException("Record not found for " + user.getId());
		}
		
	}

}

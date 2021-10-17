package com.acechat.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acechat.model.User;
import com.acechat.repository.UserRepository;

@Transactional
@Service("UserService")
public class UserService {
	private UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public void save(User user) {
		this.userRepository.save(user);
	}
	
	public User findByUsernameAndPassword(User user) {
		return this.userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
	}
	
	public void merge(User user) {
		this.userRepository.setUserInfoById(user.getName(),user.getPassword(),user.getProfilepic(),user.getUserid());;
	}
	
	public List<User> searchusers(User user){
		return this.userRepository.findByNameContaining(user.getName());
	}

	

	public User getone(User user) {
		user= this.userRepository.findByUserid(user.getUserid());
		return user;
	}

	public List<User> getall() {
		return this.userRepository.findAll();
	}
	
}

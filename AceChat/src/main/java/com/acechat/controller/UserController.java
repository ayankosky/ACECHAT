package com.acechat.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acechat.model.User;
import com.acechat.service.UserService;

@RestController("userController")
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;	
	}
	
	
	@PostMapping(path ="/newaccount",consumes = MediaType.APPLICATION_JSON_VALUE)
	public void newuser(@RequestBody User user) {
		this.userService.save(user);
	}
	
	@PostMapping(path="/login",consumes=MediaType.APPLICATION_JSON_VALUE)
	public int login(@RequestBody User user) {
		user=this.userService.findByUsernameAndPassword(user);
		try {
		return user.getUserid();
		}catch(java.lang.NullPointerException e) {
			return 0;
		}
	}
	
	@PostMapping(path="/updateaccount",consumes=MediaType.APPLICATION_JSON_VALUE)
	public void updateaccount(@RequestBody User user) {
		User currentuser = this.userService.getone(user);
		if(user.getProfilepic() == "") {
			user.setProfilepic(currentuser.getProfilepic());
		}
		if(user.getPassword()=="") {
			user.setPassword(currentuser.getPassword());
		}
		if(user.getName()=="") {
			user.setName(currentuser.getName());
		}
		this.userService.merge(user);
	}
	
	@PostMapping(path ="/searchusers",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> searchusers(@RequestBody User user) {
		List<User> list = this.userService.searchusers(user);
		for(int i=0;i<list.size();i++) {
			list.get(i).setUsername(null);
			list.get(i).setPassword(null);
		}
		return list;
	}
	
	@GetMapping(path ="/getall",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<User> getall() {
		return this.userService.getall();
	}
	
	@PostMapping(path ="/getone",produces = MediaType.APPLICATION_JSON_VALUE)
	public User getone(@RequestBody User user) {
		user = this.userService.getone(user);
		user.setPassword(null);
		return user;
	}
}

package com.acechat.test.service;


import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.acechat.controller.UserController;
import com.acechat.model.User;
import com.acechat.repository.UserRepository;
import com.acechat.service.UserService;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mockitoSession;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@TestInstance(Lifecycle.PER_CLASS)
@ContextConfiguration(locations = "classpath:testApplicationContexts.xml")
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class TestUserService {
	
	private MockMvc mockMvc;
	
	@InjectMocks
	UserService userService;
	
	@Mock
	UserRepository userRepository;
	
	
	
	
	@BeforeAll
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userService).build();
	}
	
	@Test
	public void testSave() {
		User u1 = new User();
		userService.save(u1);
		
		verify(userRepository, times(1)).save(u1);
				
	}
	
	@Test
	public void testSearchusers() {
		List<User> userList = new ArrayList<User>();
		User u1 = new User( "name", "user", "pass", "pic", 1);
		User u2 = new User( "name2", "user2", "pass2", "pic2", 2);
		userList.add(u1);
		userList.add(u2);
		Mockito.when(userRepository.findByNameContaining(u1.getName())).thenReturn(userList);
		
		userList = userService.searchusers(u1);
		Assertions.assertEquals(2, userList.size());
		verify(userRepository, times(1)).findByNameContaining(u1.getName());
	}
	
	@Test
	public void testGetall() {
		List<User> userList = new ArrayList<User>();
		User u1 = new User( "name", "user", "pass", "pic", 1);
		User u2 = new User( "name2", "user2", "pass2", "pic2", 2);
		userList.add(u1);
		userList.add(u2);
		Mockito.when(userRepository.findAll()).thenReturn(userList);
		
		userList = userService.getall();
		Assertions.assertEquals(2, userList.size());
		verify(userRepository, times(1)).findAll();
	}
	
	@Test
	public void testGetone() {
		User u1 = new User( "user", "pass", "name", "pic", 1);
		
		Mockito.when(userRepository.findByUserid(u1.getUserid())).thenReturn(u1);
		
		u1 = userService.getone(u1);
		
		Assertions.assertEquals("name", u1.getName());
		Assertions.assertEquals("user", u1.getUsername());
		Assertions.assertEquals("pass", u1.getPassword());
		Assertions.assertEquals("pic", u1.getProfilepic());
		Assertions.assertEquals(1, u1.getUserid());
	}
	
	@Test
	public void testMerge() {
		User u1 = new User( "user", "pass", "name", "pic", 1);
		
		userService.merge(u1);
		
		verify(userRepository, times(1)).setUserInfoById(u1.getName(), u1.getPassword(), u1.getProfilepic(), u1.getUserid());
	}
	
	@Test
	public void testFindByUsernameAndPassword() {
		User u1 = new User( "user", "pass", "name", "pic", 1);
		
		Mockito.when(userRepository.findByUsernameAndPassword(u1.getName(), u1.getPassword())).thenReturn(u1);
		
		userService.findByUsernameAndPassword(u1);
		
		Assertions.assertEquals("name", u1.getName());
		Assertions.assertEquals("user", u1.getUsername());
		Assertions.assertEquals("pass", u1.getPassword());
		Assertions.assertEquals("pic", u1.getProfilepic());
		Assertions.assertEquals(1, u1.getUserid());
		
		verify(userRepository, times(1)).findByUsernameAndPassword(u1.getUsername(), u1.getPassword());
	}
	
//	@SuppressWarnings("unchecked")
//	@Test public void testFailFindByUserNameAndPassword() {
//		User u1 = new User( "name", "user", "pass", "pic", 1);
//		
//		Mockito.when(userRepository.findByUsernameAndPassword("name", "root")).thenThrow(Exception.class, SQLException.class);
//		
//		userService.findByUsernameAndPassword(u1);
//		
//		verify(userService, times(1)).findByUsernameAndPassword(u1);
//	}
}

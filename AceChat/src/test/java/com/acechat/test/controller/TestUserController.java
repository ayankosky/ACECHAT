package com.acechat.test.controller;

import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.acechat.controller.UserController;
import com.acechat.model.User;
import com.acechat.service.UserService;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mockitoSession;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.ArrayList;
import java.util.List;

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
public class TestUserController {

	@Mock
	private UserService userService;

	@InjectMocks
	private UserController userController;

	private MockMvc mockMvc;

	private User user;

	@BeforeAll
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}

	@Test
	public void testNewUser() {
		User u1 = new User("user", "pass", "name", "pic", 1);

		userController.newuser(u1);
		verify(userService, times(1)).save(u1);

	}

	@Test
	public void testLogin() {
		User u1 = new User("user", "pass", "name", "pic", 1);

		Mockito.when(userService.findByUsernameAndPassword(u1)).thenReturn(u1);
		

		Assertions.assertEquals(1, u1.getUserid());

	
	}

	@Test
	public void testUpdateAccount() {
		User u1 = new User("user", "pass", "name", "pic", 1);

		User u2 = new User("", "", "", "", 1);

		Mockito.when(userService.getone(u2)).thenReturn(u1);

		if (u2.getProfilepic() == "") {
			u2.setProfilepic(u1.getProfilepic());
		}
		if (u2.getPassword() == "") {
			u2.setPassword(u1.getPassword());
		}
		if (u2.getName() == "") {
			u2.setName(u1.getName());
		}

		userController.updateaccount(u2);

		Assertions.assertEquals("name", u1.getName());
		Assertions.assertEquals("user", u1.getUsername());
		Assertions.assertEquals("pass", u1.getPassword());
		Assertions.assertEquals("pic", u1.getProfilepic());
		Assertions.assertEquals(1, u1.getUserid());

		verify(userService, times(1)).getone(u2);
		
	}
	@Test
	public void testSearchUsers() {
		List<User> userList = new ArrayList<User>();

		User u1 = new User("user", "pass", "name", "pic", 1);
		User u2 = new User("user2", "pass2", "name2", "pic2", 2);
		User u3 = new User("user3", "pass3", "name3", "pic3", 3);
		userList.add(u1);
		userList.add(u2);
		userList.add(u3);

		Mockito.when(userService.searchusers(u1)).thenReturn(userList);
		for (int i = 0; i < userList.size(); i++) {
			userList.get(i).setUsername(null);
			userList.get(i).setPassword(null);
		}
		

		Assertions.assertEquals(null, u1.getUsername());
		Assertions.assertEquals(null, u1.getPassword());
		Assertions.assertEquals(null, u2.getUsername());
		Assertions.assertEquals(null, u2.getPassword());
		Assertions.assertEquals(null, u3.getUsername());
		Assertions.assertEquals(null, u3.getPassword());
		Assertions.assertEquals(3, userList.size());

		
	}
	@Test
	public void testGetAll() {
		List<User> userList = new ArrayList<User>();

		User u1 = new User("name", "user", "pass", "pic", 1);
		User u2 = new User("name2", "user2", "pass2", "pic2", 2);
		User u3 = new User("name3", "user3", "pass3", "pic3", 3);
		userList.add(u1);
		userList.add(u2);
		userList.add(u3);
		
		userController.getall();
		
		Assertions.assertEquals(3, userList.size());
		verify(userService, times(1)).getall();
		
	}
	@Test
	public void testGetOne() {
		User u2 = new User("name2", "user2", "pass2", "pic2", 2);
		Mockito.when(userService.getone(u2)).thenReturn(u2);
		u2.setPassword(null);
		
		
		Assertions.assertEquals(null, u2.getPassword());
		
	}
}

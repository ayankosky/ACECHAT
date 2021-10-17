package com.acechat.test.service;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.mockito.Mockito.times;

import com.acechat.model.Friend;
import com.acechat.model.User;
import com.acechat.repository.FriendRepository;
import com.acechat.service.FriendService;

@TestInstance(Lifecycle.PER_CLASS)
@ContextConfiguration(locations = "classpath:testApplicationContexts.xml")
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class TestFriendService {

	@InjectMocks
	private FriendService friendService;
	@Mock
	private FriendRepository friendRepository;
	private MockMvc mockMvc;

	@BeforeAll
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(friendService).build();
	}
	
	@Test
	public void testRequest() {
		User u1 = new User( "user", "pass", "name", "pic", 1);
		User u2 = new User("user2", "pass2", "name", "pic", 2);
		Friend friend = new Friend(1,u2,u1,"");
		friend.setStatus("Pending");
		friendService.request(friend);
		
		Assertions.assertEquals("Pending", friend.getStatus());
		verify(friendRepository, times(1)).save(friend);
	}
	
	@Test
	public void testRequestUpdate() {
		User u1 = new User( "user", "pass", "name", "pic", 1);
		User u2 = new User("user2", "pass2", "name", "pic", 2);
		Friend friend = new Friend(1,u2,u1,"");
		
		friend.setStatus("Friends");
		friendService.requestupdate(friend);
		
		Assertions.assertEquals("Friends", friend.getStatus());
		verify(friendRepository, times(1)).setFriendInfoById("Friends", friend.getFriendtableid());
	}
	
	@Test
	public void testDeletefriend() {
		User u1 = new User( "user", "pass", "name", "pic", 1);
		User u2 = new User("user2", "pass2", "name", "pic", 2);
		Friend friend = new Friend(1,u2,u1,"");
		
		friendService.deletefriend(friend);
		
		verify(friendRepository, times(1)).deleteByFriendtableid(friend.getFriendtableid());
			
	}
	
	@Test
	public void testGetAll() {
		User u1 = new User( "user", "pass", "name", "pic", 1);
		User u2 = new User("user2", "pass2", "name", "pic", 2);
		User u3 = new User("user3", "pass3", "name3", "pic3", 3);
		Friend friend = new Friend(1,u2,u1,"");
		Friend friend2 = new Friend(2, u1, u3, "");
		List<Friend> friendList = new ArrayList<Friend>();
		
		friendList.add(friend2);
		friendList.add(friend);
		
		Mockito.when(friendRepository.findByRequesteridOrRequesteeid(friend.getRequesterid(), friend.getRequesteeid())).thenReturn(friendList);
		
		Assertions.assertEquals(2, friendList.size());
		
	}
	
}

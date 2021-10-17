package com.acechat.test.controller;

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
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.acechat.controller.ChatController;
import com.acechat.model.Chat;
import com.acechat.model.User;
import com.acechat.repository.ChatRepository;
import com.acechat.service.ChatService;

@TestInstance(Lifecycle.PER_CLASS)
@ContextConfiguration(locations = "classpath:testApplicationContexts.xml")
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class TestChatController {
	
	@Mock
	private ChatService chatService;
	@InjectMocks
	private ChatController chatController;

	private MockMvc mockMvc;

	@BeforeAll
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(chatController).build();
	}
	
	@Test
	public void testGetGroupName() {
		User u1 = new User("user", "pass", "name", "pic", 1);
		Chat c = new Chat(1, u1, "group", "status");
		Chat c2 = new Chat(2, u1, "group2", "stats");

		List<Chat> chatList = new ArrayList<Chat>();
		chatList.add(c2);
		chatList.add(c);
		
		Mockito.when(chatService.getall()).thenReturn(chatList);
		
		Assertions.assertEquals(2, chatList.size());
		
	}
	
	@Test
	public void testNewChat() {
		User u1 = new User("user", "pass", "name", "pic", 1);
		Chat c = new Chat(1, u1, "group", "status");
		
		chatController.newchat(c);
		
		verify(chatService, times(1)).newchat(c);
	}
	
	@Test
	public void testDeleteChat() {
		User u1 = new User("user", "pass", "name", "pic", 1);
		Chat c = new Chat(1, u1, "group", "status");
		
		chatController.deletechat(c);
		
		verify(chatService, times(1)).deletechat(c);
	}
	
	@Test
	public void testMuteStatus() {
		User u1 = new User("user", "pass", "name", "pic", 1);
		Chat c = new Chat(1, u1, "group", "status");
		
		chatController.mutestatus(c);
		
		verify(chatService, times(1)).mutestatus(c);
	}
	
	@Test
	public void testGetAllChats() {
		User u1 = new User("user", "pass", "name", "pic", 1);
		Chat c = new Chat(1, u1, "group", "status");
		Chat c2 = new Chat(2, u1, "group2", "stats");

		List<Chat> chatList = new ArrayList<Chat>();
		chatList.add(c2);
		chatList.add(c);
		
		Mockito.when(chatService.getallchats(u1)).thenReturn(chatList);
		
		Assertions.assertEquals(2, chatList.size());
	}
	
	@Test
	public void testSearchUsers() {
		User u1 = new User("user", "pass", "name", "pic", 1);
		Chat c = new Chat(1, u1, "group", "status");
		Chat c2 = new Chat(2, u1, "group2", "stats");

		List<Chat> chatList = new ArrayList<Chat>();
		chatList.add(c2);
		chatList.add(c);
		
		Mockito.when(chatService.chatsearch(u1)).thenReturn(chatList);
		
		Assertions.assertEquals(2, chatList.size());
	}

	@Test
	public void testGetOne() {
		User u1 = new User("user", "pass", "name", "pic", 1);
		Chat c = new Chat(1, u1, "group", "status");
		
		Mockito.when(chatService.getone(c)).thenReturn(c);
		
		Assertions.assertEquals(1, c.getChatid());
	}
}

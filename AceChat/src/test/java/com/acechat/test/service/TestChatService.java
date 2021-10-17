package com.acechat.test.service;

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

import com.acechat.model.Chat;
import com.acechat.model.User;
import com.acechat.repository.ChatLogRepository;
import com.acechat.repository.ChatRepository;
import com.acechat.service.ChatLogService;
import com.acechat.service.ChatService;

@TestInstance(Lifecycle.PER_CLASS)
@ContextConfiguration(locations = "classpath:testApplicationContexts.xml")
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class TestChatService {

	@Mock
	private ChatRepository chatRepository;
	@InjectMocks
	private ChatService chatService;

	private MockMvc mockMvc;

	@BeforeAll
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(chatService).build();
	}

	@Test
	public void testGetAll() {
		User u1 = new User("user", "pass", "name", "pic", 1);
		Chat c = new Chat(1, u1, "group", "status");
		Chat c2 = new Chat(2, u1, "group2", "stats");

		List<Chat> chatList = new ArrayList<Chat>();
		List<Chat> chatList2 = new ArrayList<Chat>();

		chatList.add(c2);
		chatList.add(c);
		Mockito.when(chatRepository.findAll()).thenReturn(chatList);
		Chat c3 = new Chat();

		for (int i = 0; i < chatList.size(); i++) {
			c3.setGroupname(chatList.get(i).getGroupname());
			chatList2.add(c3);
		}

		Assertions.assertEquals(2, chatList.size());
		Assertions.assertEquals(2, chatList2.size());
		

	}

	@Test
	public void testNewChat() {
		User u1 = new User("user", "pass", "name", "pic", 1);
		Chat c = new Chat(1, u1, "group", "status");

		chatService.newchat(c);

		verify(chatRepository, times(1)).save(c);

	}

	@Test
	public void testDeleteChat() {
		User u1 = new User("user", "pass", "name", "pic", 1);
		Chat c = new Chat(1, u1, "group", "status");

		chatService.deletechat(c);

		verify(chatRepository, times(1)).deleteByChatid(c.getChatid());
	}

	@Test
	public void testMuteStatus() {
		User u1 = new User("user", "pass", "name", "pic", 1);
		Chat c = new Chat(1, u1, "group", "status");

		chatService.mutestatus(c);

		verify(chatRepository, times(1)).setChatInfoById(c.getStatus(), c.getChatid());

	}
	
	@Test
	public void getAllChats() {
		User u1 = new User("user", "pass", "name", "pic", 1);
		Chat c = new Chat(1,u1,"group","status" );
		Chat c2 = new Chat(2, u1, "group2", "stats");
		List<Chat> chatList = new ArrayList<Chat>();

		chatList.add(c2);
		chatList.add(c);
		
		Mockito.when(chatRepository.findByUserid(u1)).thenReturn(chatList);
		
		Assertions.assertEquals(2, chatList.size());
	}
	
	@Test
	public void testChatSearch() {
		User u1 = new User("user", "pass", "name", "pic", 1);
		Chat c = new Chat(1,u1,"group","status" );
		Chat c2 = new Chat(2, u1, "group2", "stats");
		List<Chat> chatList = new ArrayList<Chat>();
		
		chatList.add(c2);
		chatList.add(c);
		Mockito.when(chatRepository.findByGroupnameContainingAndUserid(u1.getName(), u1)).thenReturn(chatList);
		
		Assertions.assertEquals(2, chatList.size());
		
	}
	
	@Test
	public void testGetOne() {
		User u1 = new User("user", "pass", "name", "pic", 1);
		Chat c = new Chat(1,u1,"group","status" );
		
		Mockito.when(chatRepository.findByChatid(c.getChatid())).thenReturn(c);
		
		Assertions.assertEquals(1, c.getChatid());
		
	}

}

package com.acechat.test.controller;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.sql.Timestamp;
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

import com.acechat.controller.ChatLogController;
import com.acechat.model.ChatLog;
import com.acechat.model.User;
import com.acechat.service.ChatLogService;

@TestInstance(Lifecycle.PER_CLASS)
@ContextConfiguration(locations = "classpath:testApplicationContexts.xml")
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class TestChatLogController {
	@Mock
	private ChatLogService chatLogService;
	@InjectMocks
	private ChatLogController chatLogController;
	
	private MockMvc mockMvc;
	@BeforeAll
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(chatLogController).build();
	}
	
	@Test
	public void testNewMessage() {
		Timestamp ts = new Timestamp(0);
		User u1 = new User("user", "pass", "name", "pic", 1);
		ChatLog chatLog = new ChatLog(1, u1, "hi", ts, "group");
		
		chatLog.setSenttime(ts);
		chatLogController.newmessage(chatLog);
		
		verify(chatLogService, times(1)).newmessage(chatLog);
	}
	@Test
	public void testGetAllChatLogs() {
		Timestamp ts = new Timestamp(0);
		User u1 = new User("user", "pass", "name", "pic", 1);
		ChatLog chatLog = new ChatLog(1, u1, "hi", ts, "group");
		ChatLog chatLog2 = new ChatLog(2, u1, "hello", ts, "group2");
		
		List<ChatLog> chatLogList = new ArrayList<ChatLog>();
		chatLogList.add(chatLog2);
		chatLogList.add(chatLog);
		
		Mockito.when(chatLogService.getallchatlogs(chatLog2)).thenReturn(chatLogList);
		
		Assertions.assertEquals(2, chatLogList.size());
		
		
		
	}

}

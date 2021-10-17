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

import com.acechat.model.Chat;
import com.acechat.model.User;
import com.acechat.service.ChatService;

@RestController("ChatController")
@RequestMapping("/chat")
@CrossOrigin(origins = "*")
public class ChatController {
	private ChatService chatService;
	
	
	@Autowired
	public ChatController(ChatService chatService) {
		this.chatService= chatService;
	}
	
	@PostMapping(path="/getgroupname")
	public List<Chat> getgroupname() {
		return this.chatService.getall();	
	}
	
	@PostMapping(path="/newchat", consumes= MediaType.APPLICATION_JSON_VALUE)
	public void newchat(@RequestBody Chat chat) {
		this.chatService.newchat(chat);
	}
	
	@PostMapping(path="/deletechat", consumes= MediaType.APPLICATION_JSON_VALUE)
	public void deletechat(@RequestBody Chat chat) {
		this.chatService.deletechat(chat);
	}
	
	@PostMapping(path="/mutestatus", consumes= MediaType.APPLICATION_JSON_VALUE)
	public void mutestatus(@RequestBody Chat chat) {
		this.chatService.mutestatus(chat);
		
	}
	
	@PostMapping(path ="/getallchats",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Chat> getallchats(@RequestBody User chat) {
		return this.chatService.getallchats(chat);
	}
	
	@PostMapping(path ="/searchchats",produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Chat> searchusers(@RequestBody User user) {
		return this.chatService.chatsearch(user);
	}
	
	@PostMapping(path ="/getone",produces = MediaType.APPLICATION_JSON_VALUE)
	public Chat getone(@RequestBody Chat chat) {
		return this.chatService.getone(chat);
	}
}

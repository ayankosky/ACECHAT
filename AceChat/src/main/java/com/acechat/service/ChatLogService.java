package com.acechat.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acechat.model.ChatLog;
import com.acechat.repository.ChatLogRepository;

@Transactional
@Service("ChatLogService")
public class ChatLogService {
	private ChatLogRepository chatlogRepository;

	@Autowired
	public ChatLogService(ChatLogRepository chatlogRepository) {
		this.chatlogRepository = chatlogRepository;
	}
	
	public void newmessage(ChatLog chatlog) {
		this.chatlogRepository.save(chatlog);
	}

	public List<ChatLog> getallchatlogs(ChatLog chatlog) {
		return this.chatlogRepository.findByGroupnameOrderBySenttimeAsc(chatlog.getGroupname());
	}
	
	
}

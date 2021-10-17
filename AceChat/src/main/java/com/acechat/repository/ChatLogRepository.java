package com.acechat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acechat.model.ChatLog;

@Repository
public interface ChatLogRepository extends JpaRepository<ChatLog, Integer>{
	public <S extends ChatLog> S save(S chatlog);

	public List<ChatLog> findByGroupnameOrderBySenttimeAsc(String groupname);

	
}

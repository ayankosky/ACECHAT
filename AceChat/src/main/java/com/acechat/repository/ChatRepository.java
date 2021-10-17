package com.acechat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.acechat.model.Chat;
import com.acechat.model.User;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer>{
	public <S extends Chat> S save(S chat);
	List<Chat> findAll();
	public void deleteByChatid(int chatid);
	public List<Chat> findByUserid(User user);
	public List<Chat> findByGroupnameContainingAndUserid(String groupname, User user);
	
	@Modifying
	@Query("update Chat c set c.status = ?1 where c.chatid =?2")
	void setChatInfoById(String status, int chatid);
	public Chat findByChatid(int chatid);
}

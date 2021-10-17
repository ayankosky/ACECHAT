package com.acechat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.acechat.model.Friend;
import com.acechat.model.User;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Integer>{
	public <S extends Friend> S save(S friend);
	public void deleteByFriendtableid(int friendtableid);
	
	@Modifying
	@Query("update Friend f set f.status = ?1 where f.friendtableid =?2")
	void setFriendInfoById(String status, int friendtableid);
	public List<Friend> findByRequesteridOrRequesteeid(User requesterid, User requesteeid);
}

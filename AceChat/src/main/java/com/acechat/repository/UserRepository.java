package com.acechat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.acechat.model.Chat;
import com.acechat.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	public <S extends User> S save(S user);
	public User findByUsernameAndPassword(String username, String password);
	List<User> findByNameContaining(String name);
	List<User> findAll();
	@Modifying
	@Query("update User u set u.name = ?1, u.password = ?2,u.profilepic=?3 where u.id = ?4")
	void setUserInfoById(String name, String password, String profilepic, Integer userId);
	public User findByUserid(int userid);
	
	
	
}

package com.acechat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acechat.model.Block;
import com.acechat.model.User;

@Repository
public interface BlockRepository extends JpaRepository<Block,Integer>{
	public <S extends Block> S save(S block);

	public void deleteByBlockusertableid(int blockusertableid);

	public List<Block> findByBlockerid(User user);
}

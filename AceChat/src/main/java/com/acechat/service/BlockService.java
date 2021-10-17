package com.acechat.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acechat.model.Block;
import com.acechat.repository.BlockRepository;

@Transactional
@Service("BlockService")
public class BlockService {
	private BlockRepository blockRepository;

	@Autowired
	public BlockService(BlockRepository blockRepository) {
		this.blockRepository = blockRepository;
	}

	public void block(Block block) {
		this.blockRepository.save(block);
		
	}

	public void unblock(Block block) {
		this.blockRepository.deleteByBlockusertableid(block.getBlockusertableid());
		
	}

	public List<Block> getblocks(Block block) {
		return this.blockRepository.findByBlockerid(block.getBlockerid());
		
	}

	
}

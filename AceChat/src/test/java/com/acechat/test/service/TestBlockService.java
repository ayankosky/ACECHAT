package com.acechat.test.service;

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
import static org.mockito.Mockito.times;

import com.acechat.model.Block;
import com.acechat.model.User;
import com.acechat.repository.BlockRepository;
import com.acechat.service.BlockService;

@TestInstance(Lifecycle.PER_CLASS)
@ContextConfiguration(locations = "classpath:testApplicationContexts.xml")
@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
public class TestBlockService {

	private MockMvc mockMvc;
	@InjectMocks
	private BlockService blockService;
	@Mock
	private BlockRepository blockRepository;

	@BeforeAll
	public void setup() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(blockService).build();
	}

	@Test
	public void testBlock() {
		User u1 = new User("user", "pass", "name", "pic", 1);
		User u2 = new User("user2", "pass2", "name", "pic", 2);
		Block block = new Block(1, u1, u2);

		blockService.block(block);

		verify(blockRepository, times(1)).save(block);
	}

	@Test
	public void testUnblock() {
		User u1 = new User("user", "pass", "name", "pic", 1);
		User u2 = new User("user2", "pass2", "name", "pic", 2);
		Block block = new Block(1, u1, u2);

		blockService.unblock(block);

		verify(blockRepository, times(1)).deleteByBlockusertableid(block.getBlockusertableid());
	}

	@Test
	public void testGetBlocks() {
		User u1 = new User("user", "pass", "name", "pic", 1);
		User u2 = new User("user2", "pass2", "name", "pic", 2);
		User u3 = new User("user3", "pass3", "name", "pic3", 3);
		Block block = new Block(1, u1, u2);
		Block block2 = new Block(2, u3, u2);
		Block block3 = new Block(3, u1, u3);

		List<Block> blockList = new ArrayList<Block>();
		blockList.add(block3);
		blockList.add(block2);
		blockList.add(block);

		Mockito.when(blockRepository.findByBlockerid(block.getBlockerid())).thenReturn(blockList);

		Assertions.assertEquals(3, blockList.size());
	}

}

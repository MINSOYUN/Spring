package com.momo.reply;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.momo.service.ReplyService;
import com.momo.vo.ReplyVO;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ReplyServiceTest {
	
	@Autowired
	ReplyService service;
	
	@Test
	public void test() {
		assertNotNull(service);
		List<ReplyVO> list = service.getList(83);
		log.info("list : " + list);
	}
	
	@Test
	public void insert() {
		ReplyVO replyvo = new ReplyVO();
		replyvo.setBno(83);
		replyvo.setReply("댓글");
		replyvo.setReplyer("작성자");
		int res = service.insert(replyvo);
		log.info("res : " + res);
	}
	
	@Test
	public void getOne() {
		ReplyVO vo = service.getOne(13);
		log.info("vo : " + vo);
	}
	
	@Test
	public void update() {
		ReplyVO replyvo = service.getOne(13);
		replyvo.setReply("댓글");
		replyvo.setReplyer("작성자");
	}
}

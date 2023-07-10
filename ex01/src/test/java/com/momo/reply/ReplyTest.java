package com.momo.reply;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.momo.book.BookTest;
import com.momo.mapper.ReplyMapper;
import com.momo.vo.ReplyVO;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ReplyTest {
	
	@Autowired
	ReplyMapper mapper;
	
	@Test
	public void test() {
		assertNotNull(mapper);
		List<ReplyVO> list = mapper.getList(83);
		log.info("list : " + list);
	}
	
	@Test
	public void insert() {
		ReplyVO replyvo = new ReplyVO();
		replyvo.setBno(83);
		replyvo.setReply("댓글");
		replyvo.setReplyer("작성자");
		int res = mapper.insert(replyvo);
		assertEquals(res, 1);
	}
	
	@Test
	public void delete() {
		int res = mapper.delete(12);
		assertEquals(res, 1);
	}
	
	@Test
	public void getOne() {
		ReplyVO vo =mapper.getOne(13);
		log.info("한 건 조회 : "  + vo);
	}
	
	@Test
	public void update() {
		ReplyVO replyvo = mapper.getOne(13);
		replyvo.setReply("수정테스트");
		replyvo.setReplyer("수정테스트");
		int res = mapper.update(replyvo);
	
		log.info("res : " + res);
		log.info("replyvo : " + replyvo);
	}
}

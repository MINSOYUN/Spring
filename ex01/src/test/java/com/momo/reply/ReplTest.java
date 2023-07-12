package com.momo.reply;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.momo.mapper.ReplMapper;
import com.momo.vo.ReplVO;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ReplTest {
	
	@Autowired
	ReplMapper repl;
	
	@Test
	public void getList() {
		List<ReplVO> list = repl.getList(83);
		log.info(list);
	}
	
	@Test
	public void insert() {
		ReplVO vo = new ReplVO();
		vo.setBno(83);
		vo.setReply("댓댓");
		vo.setReplyer("작성자");
		int res = repl.insert(vo);
		log.info("res: " + res);
	}
	
	@Test
	public void delete() {
		int res = repl.delete(31);
		assertEquals(res, 1);
	}

}

package com.momo.board;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.momo.service.BoardService;
import com.momo.vo.BoardVO;

import jdk.internal.org.jline.utils.Log;
import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardServiceTest {
	@Autowired
	BoardService boardService;
	
	@Test
	public void getListXml() {
		List<BoardVO> list = boardService.getListXml();
		
		list.forEach(board ->{
			log.info(board);
		});
	}
	
	@Test
	public void getOne() {
		BoardVO board = boardService.getOne(7);
		log.info("boardVO=======");
		log.info(board.getBno());
		log.info(board.getTitle());
		log.info(board.getContent());
	}
	
	
	@Test
	public void delete() {
		int res = boardService.delete(47);
		log.info("boardVO=======");
		log.info("res : " + res);
		assertEquals(res, 1);
	}
	
	
	@Test
	public void insert() {
		BoardVO board = new BoardVO();
		board.setTitle("뉴제목");
		board.setContent("뉴컨텐츠");
		board.setWriter("뉴작가");
		int res = boardService.insert(board);
		
		log.info("boardVO=======");
		log.info("board: "+ board);
		log.info("res: "+ res);
		assertEquals(res, 1);
	}
	
	
	@Test
	public void update() {
		BoardVO board = boardService.getOne(46);
		board.setTitle("update제목!");
		board.setContent("update내용");
		board.setWriter("update작성자");
		int res = boardService.update(board);
		
		log.info("==========");
		log.info("board: "+ board);
		log.info("res: "+ res);
		assertEquals(res, 1);
	}
	
	
	@Test
	public void getTotalCnt() {
		int res = boardService.getTotalCnt();
		log.info("=================");
		log.info("res: "+ res);
	}
}

package com.momo.board;

import static org.junit.Assert.assertEquals;
import static org.junit.Assume.assumeNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.momo.mapper.BoardMapper;
import com.momo.vo.BoardVO;

import lombok.extern.log4j.Log4j;

@Log4j   // log.info 사용 가능
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BoardTest {
	@Autowired
	BoardMapper boardMapper;
	
	@Test
	public void getList() {
		assumeNotNull(boardMapper);
		List<BoardVO> list = boardMapper.getList();
		
		list.forEach(board -> {
			log.info("boardVO=======");
			log.info(board.getBno());
			log.info(board.getTitle());
			log.info(board.getContent());
		});
		
	}
	
	
	@Test
	public void getListXml() {
		List<BoardVO> list = boardMapper.getListXml();
		list.forEach(board -> {
			log.info("boardVO=======");
			log.info(board.getBno());
			log.info(board.getTitle());
			log.info(board.getContent());
		});
	}
	
	
	@Test
	public void insert() {
		BoardVO board = new BoardVO();
	    board.setTitle("테스트 제목");
	    board.setContent("테스트 내용");
	    board.setWriter("user01");
	    
	    int res = boardMapper.insert(board);
	    assertEquals(res, 1);
	}
	
	
	
	@Test
	public void insertSelectkey() {
		BoardVO board = new BoardVO();
	    board.setTitle("테스트 제목 Selectkey");
	    board.setContent("테스트 내용 Selectkey");
	    board.setWriter("user01 Selectkey");
		
	    int res = boardMapper.insertSelectkey(board);
	    log.info("bno : " + board.getBno());
	    assertEquals(res, 1);
	}
	
	
	@Test
	public void getOne() {
		BoardVO board = boardMapper.getOne(6);
		log.info("boardVO=======");
		log.info(board.getBno());
		log.info(board.getTitle());
		log.info(board.getContent());
	}
	
	
	@Test
	public void delete() {
		int res = boardMapper.delete(6);
		assertEquals(res, 1);
	}
	
	
	@Test
	public void update() {
		BoardVO board = boardMapper.getOne(7);
		board.setTitle("수정/제목");
		board.setContent("수정/내역");
		board.setWriter("수정/작성자");
		int res = boardMapper.update(board);
		log.info("board: " + board);
	}
	
	
	@Test
	public void getTotalCnt() {
		int res = boardMapper.getTotalCnt();
		log.info("totalCnt: " + res);
	}
}

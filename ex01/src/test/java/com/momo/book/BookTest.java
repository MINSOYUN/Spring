package com.momo.book;

import static org.junit.Assume.assumeNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.Model;

import com.momo.mapper.BookMapper;
import com.momo.vo.BookVO;
import com.momo.vo.Criteria;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class BookTest {
	
	@Autowired
	BookMapper bookmapper;
	
	@Test
	public void getList() {
		assumeNotNull(bookmapper);
		Criteria cri = new Criteria();
		
		// 테스트 -> 설정
		cri.setSearchField("title");
		cri.setSearchWord("word");
		
		List<BookVO> list = bookmapper.getList(cri);
		
		list.forEach(book->{
			log.info("==================");
			log.info(book.getNo());
			log.info(book.getTitle());
			log.info(book.getAuthor());
		});
	}
	
	
	@Test
	public void getTotalCnt() {
		
		Criteria cri = new Criteria();
		log.info("cri:" + cri);
		// 테스트 -> 설정
		cri.setSearchField("title");
		cri.setSearchWord("word");
		int res = bookmapper.getTotalCnt(cri);
		log.info("===========");
		log.info("res : " + res);
	}
	
	
	@Test
	public void getOne() {
		BookVO book = bookmapper.getOne(49);
		
		log.info("=========");
		log.info("book : " + book);
	}
	
	
	@Test
	public void insert() {
		BookVO book = new BookVO();
		book.setTitle("타이틀");
		book.setAuthor("작가");
		book.setPublisher("출판사");
		book.setInfo("설명");
		int res = bookmapper.insert(book);
		log.info("res: " +res);
		log.info("book: " + book);
	}
}

package com.momo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.momo.mapper.BookMapper;
import com.momo.vo.BookVO;
import com.momo.vo.Criteria;
import com.momo.vo.PageDto;

import lombok.extern.log4j.Log4j;

@Log4j
@Service
public class BookServiceImpl implements BookService{
	
	@Autowired
	BookMapper bookmapper;
	
	@Override
	public List<BookVO> getList(Criteria cri, Model model){
		/*
		 * 1. 리스트 조회
		 * 2. 총 건수 조회
		 * 3. PageDto 생성 -> 페이징 블럭 생성
		 */
		List<BookVO> list = bookmapper.getList(cri);
		int totalCnt = bookmapper.getTotalCnt(cri);
		// 게시물의 초 건수로 페이지 블럭을 생성
		PageDto pageDto = new PageDto(totalCnt, cri);
		
		model.addAttribute("list", list);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("pageDto", pageDto);
		model.addAttribute("cri", cri);
		
		log.info("=========");
		log.info("list : " + list);
		log.info("totalCnt : " + totalCnt);
		log.info("pageDto : " + pageDto);
		
		return null;
	}
	
	
	
	@Override
	public BookVO getOne(int no, Model model) {
		BookVO book = bookmapper.getOne(no);
		model.addAttribute("book", book);
		return null;
	}
	
	
	@Override
	public int insert(BookVO book) {
		int res = bookmapper.insert(book);
		return res;
	}
	
}

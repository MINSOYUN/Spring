package com.momo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.momo.vo.BookVO;
import com.momo.vo.Criteria;

@Service
public interface BookService {
	
	// 도서 조회
	public List<BookVO> getList(Criteria cri, Model model);
	
	// 도서 상세 조회
	public BookVO getOne(int no, Model model);
	
	// 도서 등록
	public int insert(BookVO book);
	
	// 도서 삭제
	public int delete(int no);
	
	// 도서 업데이트
	public int update(BookVO book);

}

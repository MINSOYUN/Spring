package com.momo.mapper;

import java.util.List;

import com.momo.vo.BookVO;
import com.momo.vo.Criteria;

public interface BookMapper {
	
	// 도서 리스트 조회
	public List<BookVO> getList(Criteria cri);

	// 도서 총 건수 조회
	public int getTotalCnt(Criteria cri);

	// 도서 상세보기
	public BookVO getOne(int no);
	
	// 도서 등록
	public int insert(BookVO book);
	
	// 도서 삭제
	
	
	// 도서 업데이트

}

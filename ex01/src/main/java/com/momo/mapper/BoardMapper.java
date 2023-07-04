package com.momo.mapper;

// util import 받기
import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.momo.vo.BoardVO;

public interface BoardMapper {
	
	@Select("select * from tbl_board")
	public List<BoardVO> getList();
	
	// 조회
	public List<BoardVO> getListXml();
	
	// 작성
	public int insert(BoardVO board);
	
	// 시퀀스 포함한 작성
	public int insertSelectkey(BoardVO board);
	
	// 한 건 조회
	public BoardVO getOne(int bno);
	
	// 삭제
	public int delete(int bno);
	
	// 업데이트
	public int update(BoardVO board);
	
	// 총 건수
	public int getTotalCnt();
}

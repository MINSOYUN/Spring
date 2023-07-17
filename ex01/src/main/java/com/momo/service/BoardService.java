package com.momo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.momo.vo.BoardVO;
import com.momo.vo.Criteria;
	
	@Service
	public interface BoardService {
		public List<BoardVO> getListXml(Criteria cri, Model model);
		
		// 삽입
		public int insert(BoardVO board);
		
		// 시퀀스 포함한 삽입
		public int insertSelectkey(BoardVO board);
		
		// 하나 조회
		public BoardVO getOne(int bno);
		
		// 삭제
		public int delete(String bno);
		
		// 업데이트
		public int update(BoardVO board);
		
		// 총 건수
		public int getTotalCnt(Criteria cri);
		
		// 게시글 당 댓글  수
		public int updateReplyCnt(@Param("bno")int bno, @Param("amount")int amount);
		
}

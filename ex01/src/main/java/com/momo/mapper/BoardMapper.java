package com.momo.mapper;

// util import 받기
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.momo.vo.BoardVO;
import com.momo.vo.Criteria;

public interface BoardMapper {
	
	@Select("select * from tbl_board")
	public List<BoardVO> getList();
	
	// 조회
	public List<BoardVO> getListXml(Criteria cri);
	
	// 작성
	public int insert(BoardVO board);
	
	// 시퀀스 포함한 작성
	public int insertSelectkey(BoardVO board);
	
	// 한 건 조회
	public BoardVO getOne(int bno);
	
	// 삭제
	public int delete(String bno);
	
	// 업데이트
	public int update(BoardVO board);
	
	// 총 건수
	public int getTotalCnt(Criteria cri);
	
	/**
	 * 파라미터가 2개 이상인 경우 Param 어노테이션을 꼭 달아 주어야 합니다
	 * @param bno
	 * @param amount
	 * @return
	 */
	// 게시글에 해당하는 댓글 수
	public int updateReplyCnt(@Param("bno")int bno, @Param("amount")int amount);
	
}

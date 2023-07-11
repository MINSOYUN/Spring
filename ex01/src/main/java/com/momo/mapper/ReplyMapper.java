package com.momo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.momo.vo.Criteria;
import com.momo.vo.ReplyVO;

public interface ReplyMapper {
	
	/**
	 * 매개변수가 두 개 이상 전달 되는 경우
	 * Param 어노테이션 사용
	 * @param bno
	 * @param cri
	 * @return
	 */
	// 댓글 조회
	public List<ReplyVO> getList(@Param(value="bno") int bno, @Param(value="cri") Criteria cri);
	
	// 댓글 추가
	public int insert(ReplyVO replyvo);
	
	// 댓글 삭제
	public int delete(int rno);
	
	// 댓글 한 건
	public ReplyVO getOne(int rno);
	
	// 댓글 수정
	public int update(ReplyVO replyvo);
	
	// 댓글 건수
	public int totalCnt(int bno);
	
}

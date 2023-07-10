package com.momo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.momo.vo.ReplyVO;

@Service
public interface ReplyService {
	
	// 댓글 조회
	public List<ReplyVO> getList(int bno);
	
	// 댓글 추가
	public int insert(ReplyVO replyvo);
	
	// 댓글 삭제
	public int delete(int rno);
	
	// 댓글 한 건
	public ReplyVO getOne(int rno);
	
	// 댓글 수정
	public int update(ReplyVO replyvo);
}

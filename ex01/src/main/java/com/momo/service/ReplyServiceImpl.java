package com.momo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.momo.mapper.BoardMapper;
import com.momo.mapper.ReplyMapper;
import com.momo.vo.Criteria;
import com.momo.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService{
	@Autowired
	ReplyMapper mapper; 
	
	@Autowired
	BoardMapper boardmapper;
	
	@Override
	public List<ReplyVO> getList(int bno, Criteria cri) {
		return mapper.getList(bno, cri);
	}
	
	
	@Override
	public int totalCnt(int bno) {
		return mapper.totalCnt(bno);
	}
	
	
	/**
	 * Transactional
	 * 서비스 로직에 대한 트랜잭션 처리를 지원
	 * -> 오류 발생 시 롤백
	 */
	@Transactional
	@Override
	public int insert(ReplyVO replyvo) {
		// 댓글 추가되면 게시물 테이블의 댓글 수 1 증가
		// 트랜잭션 처리하여 둘 다 성공하여 커밋, 하나라도 실패하면 롤백
		boardmapper.updateReplyCnt(replyvo.getBno(), 1);  
		int res = mapper.insert(replyvo);
		return res;
	}
	
	
	@Transactional
	@Override
	public int delete(int rno) {
		// delete 에서는 bno값을 못 가져오기 때문에
		// rno의 bno의 값을 가져와서 댓글 수를 update 한다
		ReplyVO replyvo = mapper.getOne(rno);
		boardmapper.updateReplyCnt(replyvo.getBno(), -1);
		return mapper.delete(rno);
	}
	
	
	@Override
	public ReplyVO getOne(int rno) {
		ReplyVO replyvo =mapper.getOne(rno);
		return replyvo;
	}
	
	
	@Override
	public int update(ReplyVO replyvo) {
		int res = mapper.update(replyvo);
		return res;
	}
}

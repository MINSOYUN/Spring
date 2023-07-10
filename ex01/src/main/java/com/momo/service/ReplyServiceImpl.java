package com.momo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momo.mapper.ReplyMapper;
import com.momo.vo.ReplyVO;

@Service
public class ReplyServiceImpl implements ReplyService{
	@Autowired
	ReplyMapper mapper; 
	
	
	@Override
	public List<ReplyVO> getList(int bno) {
		return mapper.getList(bno);
	}
	
	
	@Override
	public int insert(ReplyVO replyvo) {
		int res = mapper.insert(replyvo);
		return res;
	}
	
	
	@Override
	public int delete(int rno) {
		int res = mapper.delete(rno);
		return res;
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

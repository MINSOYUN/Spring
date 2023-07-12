package com.momo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.momo.vo.ReplVO;
import com.momo.vo.ReplyVO;

@Service
public interface ReplService {
	
	public List<ReplVO> getList(int bno);
	
	public int insert(ReplVO vo);
	
	public int delete(int rno);
	
	public int update(ReplVO vo);
}

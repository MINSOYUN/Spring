package com.momo.mapper;

import java.util.List;

import com.momo.vo.ReplVO;
import com.momo.vo.ReplyVO;

public interface ReplMapper {
	// 추상메서드로 생성
	public List<ReplVO> getList(int bno);
	
	public int insert(ReplVO vo);
	
	public int delete(int rno);
	
	public int update(ReplVO vo);
}

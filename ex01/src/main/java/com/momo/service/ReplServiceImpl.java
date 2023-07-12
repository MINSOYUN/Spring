package com.momo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momo.mapper.ReplMapper;
import com.momo.vo.ReplVO;

@Service
public class ReplServiceImpl implements ReplService{
	@Autowired
	ReplMapper mapper;
	
	@Override
	public List<ReplVO> getList(int bno) {
		List<ReplVO> list = mapper.getList(bno);
		return list;
	}
	
	@Override
	public int insert(ReplVO vo) {
		int res = mapper.insert(vo);
		return res;
	}
	
	@Override
	public int delete(int rno) {
		return mapper.delete(rno);
	}
	
	@Override
	public int update(ReplVO vo) {
		return mapper.update(vo);
	}
}

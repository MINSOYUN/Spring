package com.momo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momo.mapper.FileMapper;
import com.momo.vo.FileVO;

@Service
public class FileServiceImpl implements FileService {
	
	@Autowired
	FileMapper mapper;
	
	@Override
	public List<FileVO> getList(int bno) {
		return mapper.getList(bno);
	}
	
	@Override
	public int insert(FileVO vo) {
		return mapper.insert(vo);
	}

}

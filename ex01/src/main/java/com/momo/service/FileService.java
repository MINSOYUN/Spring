package com.momo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.momo.vo.FileVO;

@Service
public interface FileService {
	
	public List<FileVO> getList(int bno);
	
	public int insert(FileVO vo);
	
	public int delete(@Param("uuid") String uuid, @Param("bno") int bno);

}

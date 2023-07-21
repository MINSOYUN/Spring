package com.momo.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.momo.vo.FileVO;

@Service
public interface FileService {
	
	public List<FileVO> getList(int bno);
	
	public int insert(FileVO vo);
	
	public int delete(@Param("uuid") String uuid, @Param("bno") int bno);
	
	public int fileupload(List<MultipartFile> files, int bno) throws Exception;
	
	public FileVO getOne(@Param("uuid") String uuid, @Param("bno") int bno);

}

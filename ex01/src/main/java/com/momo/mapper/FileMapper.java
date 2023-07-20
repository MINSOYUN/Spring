package com.momo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.momo.vo.FileVO;

public interface FileMapper {
	
	/**
	 * 하나의 게시물에 대해 업로드된 파일 목록을 조회
	 * @param bno
	 * @return
	 */
	public List<FileVO> getList(int bno);
	
	public int insert(FileVO vo);
	
	public int delete(@Param("uuid") String uuid, @Param("bno") int bno);


}

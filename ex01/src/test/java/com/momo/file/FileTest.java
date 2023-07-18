package com.momo.file;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.momo.mapper.FileMapper;
import com.momo.vo.FileVO;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class FileTest {
	
	@Autowired
	FileMapper mapper;
	
	@Test
	public void getList() {
		List<FileVO> list = mapper.getList(83);
		log.info("========================");
		log.info("list : " + list); 
	}
	
	@Test
	public void insert() {
		FileVO vo = new FileVO();
		vo.setUuid("8djndfddddddddd");
		vo.setUploadpath("경로");
		vo.setFilename("파일이름");
		vo.setFiletype("I");
		vo.setBno(83);
		int res = mapper.insert(vo);
		
		log.info("========================");
		log.info("res" + res); 
		log.info("vo" + vo); 
	}
}

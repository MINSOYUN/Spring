package com.momo.file;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.momo.service.FileService;
import com.momo.vo.FileVO;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class FileServiceTest {
	
	@Autowired
	FileService service;
	
	@Test
	public void getList() {
		List<FileVO> list = service.getList(83);
		log.info("========================");
		log.info("list : " + list); 
	}
	
	@Test
	public void insert() {
		FileVO vo = new FileVO();
		vo.setUuid("8djndf4344444");
		vo.setUploadpath("경로");
		vo.setFilename("파일이름");
		vo.setFiletype("I");
		vo.setBno(83);
		int res = service.insert(vo);
		
		log.info("========================");
		log.info("res" + res); 
		log.info("vo" + vo); 
	}
	
	@Test
	public void delete() {
		int res = service.delete("3ad6d1e9-8362-41db-8aff-a74fc683d043", 83);
		log.info("===========");
		log.info("res : " + res);
	}

}

package com.momo.log;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.momo.service.LogService;
import com.momo.vo.LogVO;

import lombok.extern.log4j.Log4j;

@Log4j   // log.info 사용 가능
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class LogServiceTest {
	
	@Autowired
	LogService service;
	
	@Test
	public void insert() {
		LogVO vo = new LogVO();
		vo.setClassName("클래스");
		vo.setMethodName("메서드");
		vo.setParams("파라미터");
		vo.setErrmsg("에러");
		int res = service.insert(vo);
		
		log.info("========");
		log.info(vo);
		log.info("res : " + res);
	}

}

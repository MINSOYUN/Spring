package com.momo.reply;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.momo.service.ReplService;
import com.momo.vo.ReplVO;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class ReplServiceTest {
	
	@Autowired
	ReplService service;
	
	@Test
	public void getList() {
		List<ReplVO> list = service.getList(83);
		log.info(list);
	}

}

package com.momo.hotel;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.momo.mapper.HotelMapper;
import com.momo.vo.HotelVO;

import lombok.extern.log4j.Log4j;

@Log4j   // log.info 사용 가능
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class HotelTest {
	
	@Autowired
	HotelMapper mapper;
	
	@Test
	public void getList() {
		List<HotelVO> list = mapper.getList();
		log.info("===========");
		log.info("list " + list);
	}
	
}

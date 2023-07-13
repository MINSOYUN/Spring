package com.momo.member;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.momo.mapper.MemberMapper;
import com.momo.service.MemberService;
import com.momo.vo.Member;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MemberServiceTest {
	
	@Autowired
	MemberService service;
	
	@Test
	public void login() {
		Member member = new Member();
		member.setId("sso");
		member.setPw("1234");
		service.login(member);
		
		log.info("=========");
		log.info("member : " + member);
	}
	
	@Test
	public void insert() {
		Member member = new Member();
		member.setId("메가");
		member.setPw("1234");
		member.setName("커피");
		member.setAdminyn("N");
		member.setGrade("C");
		int res = service.insert(member);
		log.info("==========");
		log.info("member : " + member);
		log.info("res : " + res);
	}
	

}

package com.momo.member;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.momo.mapper.MemberMapper;
import com.momo.vo.Member;

import lombok.extern.log4j.Log4j;

@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
public class MemberTest {
	
	@Autowired
	MemberMapper mapper;
	
	@Test
	public void login() {
		Member member = new Member();
		member.setId("sso");
		member.setPw("1234");
		mapper.login(member);
		
		log.info("=========");
		log.info("member : " + member);
	}
	
	@Test
	public void insert() {
		Member member = new Member();
		member.setId("sso");
		member.setPw("1234");
		member.setName("소윤");
			
		int res = mapper.insert(member);
		log.info("==========");
		log.info("member : " + member);
		log.info("res : " + res);
	}
	
	@Test
	public void idCheck() {
		Member member = new Member();
		member.setId("ADMIN");
		int res = mapper.idCheck(member);
		log.info("==========");
		log.info("res : " + res);
	}
	
	@Test
	public void delete() {
		int res = mapper.delete("나예리");
		log.info("==========");
		log.info("res : " + res);
	}
	
	@Test
	public void memberCnt() {
		int res = mapper.memberCnt();
		log.info("==========");
		log.info("res : " + res);
	}
	
	@Test
	public void testMemberRole() {
		List<String> list = mapper.getMebmerRole("admin");
		System.out.println("list : " + list);
		System.out.println("관리자 권한 : " + list.contains("ADMIN_ROLE"));
	}

}

package com.momo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.momo.dao.MemberDao;
import com.momo.vo.Member;

// service 라고 어노테이션
@Service
public class MemberService {

	// new 직접 생성하지 않는다 -> 주입 @Autowired
	@Autowired
	MemberDao dao;
	
	// model : Controller에서 생성한 데이터를 담아서 View로 전달할 때 사용하는 객체
	public Member login(Member paramMember, Model model) {
		Member member = dao.login(paramMember);
	
		if(member == null) {
			model.addAttribute("message", "아이디/ 비밀번호를 확인해주세요");
		} else {
			model.addAttribute("message", member.getName()+"님 환영합니다");
		}
		return member;
	}
}

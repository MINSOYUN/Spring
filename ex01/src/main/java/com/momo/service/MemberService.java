package com.momo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.momo.dao.MemberDao;
import com.momo.mapper.MemberMapper;
import com.momo.vo.Member;

// service 라고 어노테이션
@Service
public interface MemberService {
	
	// 멤버 로그인
	public Member login(Member member);
	
	// 멤버 추가
	public int insert(Member member);

	
	// model : Controller에서 생성한 데이터를 담아서 View로 전달할 때 사용하는 객체
//	public Member login(Member paramMember, Model model) {
//		Member member = memberMapper.login(paramMember);
//	
//		if(member == null) {
//			model.addAttribute("message", "아이디/ 비밀번호를 확인해주세요");
//			
//		} else {
//			model.addAttribute("message", member.getName()+"님 환영합니다");
//		}
//		return member;
//	}
}

package com.momo.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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
	
	// idCheck
	public int idCheck(Member member);
	
	// 멤버 삭제
	public int delete(String name);
	
	// 멤버 수 조회
	public int memberCnt();

	public void naverLogin(HttpServletRequest request, Model model);

	
}

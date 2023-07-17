package com.momo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.momo.mapper.MemberMapper;
import com.momo.vo.Member;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	MemberMapper memberMapper;
	
	@Autowired
	BCryptPasswordEncoder encoder;
	
	@Override
	public Member login(Member paramMember) {
		Member member = memberMapper.login(paramMember);
		if(member != null) {
			boolean res = encoder.matches(paramMember.getPw(), member.getPw());  
			
			if(res) {
				member.setRole(memberMapper.getMebmerRole(member.getId()));
				return member;
			}
		}
		return null;
	}
	
	
	
	@Override
	public int insert(Member member) {
		String pw = member.getPw();   // pw 가져오고
		member.setPw(encoder.encode(pw));  // pw 인코딩(암호화) 후 세팅
		System.out.println("pw: " + member.getPw());
		return memberMapper.insert(member);
	}
	
	
	@Override
	public int idCheck(Member member) {
		return memberMapper.idCheck(member);
	}
	
	
	@Override
	public int delete(String name) {
		return memberMapper.delete(name);
	}
	
	
	@Override
	public int memberCnt() {
		return memberMapper.memberCnt();
	}

}

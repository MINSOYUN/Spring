package com.momo.mapper;

import java.util.List;

import com.momo.vo.Member;

public interface MemberMapper {
	
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
	
	// 권한 조회
	public List<String> getMebmerRole(String id);

}

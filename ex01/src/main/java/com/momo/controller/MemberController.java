package com.momo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.momo.service.MemberService;
import com.momo.vo.Member;


// controller 어노테이션 작성하여 컨트롤러임을 명시
@RequestMapping("/member/*")
@Controller
public class MemberController {
	
	// 주입
	@Autowired
	MemberService service;
	
	
	/**
	 * 로그인 페이지로 이동
	 * @return
	 */
	// 어떤 식으로 매핑 줄 것인지 어노테이션 작성 -> login 페이지로 이동
	// 컨트롤러 장점 : 파라미터 자동 수집
	@GetMapping("login")
	public String login(Member member) {    // member 파라미터 자동 수집
		return "login";   // return 파일이름
	}
	
	
	@PostMapping("/loginAction")
	public String loginAction(Member member, Model model) {
		System.out.println("id:" + member.getId());
		System.out.println("pw: " + member.getPw());
	
		service.login(member, model);
		// req.setAttribute랑 같음
		// model.addAttribute("message", member.getId() + "님 환영합니다");
		
		return "redirect:/board/list";
		
	}
}

package com.momo.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.momo.service.MemberService;
import com.momo.vo.Member;


// controller 어노테이션 작성하여 컨트롤러임을 명시
@Controller
public class MemberController extends CommonRestController{  // map(res,msg) extends
	
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
	// fetch는 JSON 형식이기 때문에 JSON 형태로 받기 위해 @RequestBody 작성
	public @ResponseBody Map<String, Object> loginAction(@RequestBody Member member, Model model, HttpSession session) {
		System.out.println("id:" + member.getId());
		System.out.println("pw: " + member.getPw());
	
		member = service.login(member);
		if(member != null) {
			// member 가 null 이 아니라 성공 -> 1반환
			session.setAttribute("member", member);   // session에 저장
			session.setAttribute("userId", member.getId());
			return responseMap(1, "로그인");   // map 반환 (성공, msg)
		} else {
			return responseMap(0, "로그인");
		}
		// req.setAttribute랑 같음
		// model.addAttribute("message", member.getId() + "님 환영합니다");
	}
	
}

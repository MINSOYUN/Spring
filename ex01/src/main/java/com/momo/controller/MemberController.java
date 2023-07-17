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

import lombok.extern.log4j.Log4j;


// controller 어노테이션 작성하여 컨트롤러임을 명시
@Controller
@Log4j
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
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();  // 세션무효화
		return "login";
	}
	
	
	/**
	 * JSON 형식의 데이터를 주고받으면 페이지를 갱신하지 않고
	 * 원하는 데이터만 요청 가능하다
	 * @return
	 */
	@PostMapping("/loginAction")
	// fetch는 JSON 형식이기 때문에 JSON 형태로 받기 위해 @RequestBody 작성
	public @ResponseBody Map<String, Object> loginAction(@RequestBody Member member, Model model, HttpSession session) {
		System.out.println("id:" + member.getId());
		System.out.println("pw: " + member.getPw());
	
		member = service.login(member);
		System.out.println("member: " + member);
		
		if(member != null) {
			// member 가 null 이 아니라 성공 -> 1반환
			session.setAttribute("member", member);   // session에 저장
			session.setAttribute("userId", member.getId());
			
			// =========================== 관리자면 관리자 페이지로 이동 ======================
			Map<String, Object> map = responseMap(REST_SUCCESS, "로그인 되었습니다");
			// 권한 확인
			if(member.getRole() != null && member.getRole().contains("ADMIN_ROLE")) {
				map.put("url", "/admin");  // 관리자면 admin 페이지로
				log.info("URL 주소: " + map.get("url"));
			} else {
				map.put("url", "/board/list");  // 아니면 list로
			}
			
			return map;
		} else {
			return responseMap(REST_FAIL, "아이디와 비밀번호를 확인해주세요.");
		}
		// req.setAttribute랑 같음
		// model.addAttribute("message", member.getId() + "님 환영합니다");
	}
	
	@PostMapping("/idCheck")  // -> url 호출 / Controller 임!
	// 보낼때도 JSON @RequestBody, 받아올 때도 JSON @ResponseBody
	public @ResponseBody Map<String, Object> idCheck(@RequestBody Member member){
		int res = service.idCheck(member);
		// count가 1이상이면 id 중복 -> 실패
		
		if(res == 0) {
			return responseMap(REST_SUCCESS, "사용 가능한 아이디 입니다");
		} else {
			return responseMap(REST_FAIL, "이미 사용 중인 아이디 입니다");
		}
	}
	
	@PostMapping("/register")
	public @ResponseBody Map<String, Object> register(@RequestBody Member member){
		try {
			int res = service.insert(member);
			return responseWriteMap(res);
		} catch (Exception e) {
			e.printStackTrace();
			return responseMap(REST_FAIL, "회원 가입 중 문제가 발생하였습니다");
		}
	}
}

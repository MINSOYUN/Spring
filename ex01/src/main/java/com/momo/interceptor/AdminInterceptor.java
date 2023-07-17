package com.momo.interceptor;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.momo.vo.Member;

@Component
public class AdminInterceptor implements HandlerInterceptor{
	
	// 컨트롤 실행 전 미리 체크
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();   // userId와 member 객체 저장해놓음
		
		if(session.getAttribute("member") != null) {
			// member 객체 꺼내옴
			Member member = (Member)session.getAttribute("member");  // Object 타입이라 형변환
			List<String> role = member.getRole();
			if(role.contains("ADMIN_ROLE")) {
				return true;
			}
		}
		// 로그인 페이지로 이동
		String msg = URLEncoder.encode("로그인 후 사용 가능한 메뉴입니다", "UTF-8");
		response.sendRedirect("/login?msg="+msg);
		return false;
	}

}

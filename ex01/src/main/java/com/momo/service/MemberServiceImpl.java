package com.momo.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.fasterxml.jackson.databind.ObjectMapper;
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
	
	@Autowired
	ApiExamMemberProfile apiExam;
	
	@Override
	public void naverLogin(HttpServletRequest request, Model model) {
		try {
			// callback 처리 -> access_token
			Map<String, String> callbackRes = callback(request);

			String access_token = callbackRes.get("access_token");
			// access_token -> 사용자 정보 조회
			Map<String, Object> responseBody = apiExam.getMemberProfile(access_token);
			
			Map<String, String> response = (Map<String, String>) responseBody.get("response");
			System.out.println("=======naverLogin=========");
			System.out.println(response.get("name")); 
			System.out.println(response.get("id")); 
			System.out.println(response.get("gender"));
			System.out.println("=======naverLogin=========");
			model.addAttribute("id", response.get("id"));
			model.addAttribute("name", response.get("name"));
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public Map<String, String> callback(HttpServletRequest request) throws Exception{
		String clientId = "hdC1Y6KkRXGTwQLARKGf";//애플리케이션 클라이언트 아이디값";
	    String clientSecret = "RLD38JjeLc";//애플리케이션 클라이언트 시크릿값";
	    String code = request.getParameter("code");
	    String state = request.getParameter("state");
	    try {
		    String redirectURI = URLEncoder.encode("http://localhost:8090/login/naver_callback", "UTF-8");
		    String apiURL;
		    // URL 경로
		    apiURL = "https://nid.naver.com/oauth2.0/token?grant_type=authorization_code&";
		    apiURL += "client_id=" + clientId;
		    apiURL += "&client_secret=" + clientSecret;
		    apiURL += "&redirect_uri=" + redirectURI;
		    apiURL += "&code=" + code;
		    apiURL += "&state=" + state;
		    String access_token = "";
		    String refresh_token = "";
		    System.out.println("apiURL="+apiURL);
		      URL url = new URL(apiURL);
		      HttpURLConnection con = (HttpURLConnection)url.openConnection();
		      con.setRequestMethod("GET");
		      int responseCode = con.getResponseCode();
		      BufferedReader br;
		      System.out.print("responseCode="+responseCode);
		      if(responseCode==200) { // 정상 호출
		        br = new BufferedReader(new InputStreamReader(con.getInputStream()));
		      } else {  // 에러 발생
		        br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
		      }
		      String inputLine;
		      StringBuffer res = new StringBuffer();
		      while ((inputLine = br.readLine()) != null) {
		        res.append(inputLine);
		      }
		      br.close();
		      if(responseCode==200) {
		    	  System.out.println("toekn 요청"+res.toString());
		    	  
		    	  Map<String, String> map = new HashMap<String, String>();
		          // jackson 라이브러리 사용
		          ObjectMapper objectmapper = new ObjectMapper();
		          map = objectmapper.readValue(res.toString(), Map.class);
		          return map;
		      } else {
		    	  throw new Exception("callback 반환 코드 : " + responseCode);
		      }
	    } catch (Exception e) {
	      System.out.println(e);
	      throw new Exception("callback 처리 중 예외사항이 발생하였습니다");
	    }
	}

}

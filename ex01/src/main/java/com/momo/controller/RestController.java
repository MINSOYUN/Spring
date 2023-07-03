package com.momo.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.momo.vo.Member;

// json 타입 반환
@Controller
public class RestController {
	
	// @ResponseBody JSON 형식
	@GetMapping("rest")
	public @ResponseBody Member rest(Member member) {
		
		return member;
	}
	
	
	/**
	 * responseEntity
	 * 헤더 정보를 가공하기 위한 용도로 사용
	 * Request, Response 객체를 직접 다루지 않고
	 * 스프링 MVC에서 제공해주는 어노테이션 또는 객체를 이용한다
	 * @return
	 */
	@GetMapping("restResponseEntity")
	public ResponseEntity<String> rest1(){
		HttpHeaders header = new HttpHeaders();
		header.add("content-type","application/json;charset=utf-8"); 
		String msg = "{\"name\":\"모모\"}";   
		ResponseEntity<String> rs = new ResponseEntity<String>(msg, header, HttpStatus.OK);
		return rs;
	}
}

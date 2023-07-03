package com.momo.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.momo.vo.Member;

@Controller
public class PrecController {
	
	@GetMapping("body")
	public @ResponseBody Member rest(Member member) {
		return member;
	}
	
	@GetMapping("entity")
	public ResponseEntity<String> rest2(){
		HttpHeaders header = new HttpHeaders();
		header.add("content-type","application/json;charset=utf-8"); 
		String msg = "{\"name:\" \"모모\"}";
		ResponseEntity<String> rs = new ResponseEntity<String>(msg, header, HttpStatus.OK);
		return rs;
	}

	
}

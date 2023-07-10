package com.momo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.momo.service.ReplyService;
import com.momo.vo.ReplyVO;

import lombok.extern.log4j.Log4j;

/**
 * RestController
 * 	Controller가 Rest 방식을 처리하기 위한 것임을 명시
 * @author user
 *
 */
@RestController
@Log4j
public class ReplyController {
	
	@Autowired
	ReplyService service;
	
	@GetMapping("/test")
	public String test() {
		return "test";
	}
	
	/**
	 * PathVariable
	 * 	URL 경로에 있는 값을 파라미터로 추출하려고 할 때 사용
	 *  경로의 일부분을 파라미터로 사용
	 * @param bno
	 * @return
	 */
	@GetMapping("/reply/list/{bno}")  // url을 호출할 때 bno 값을 받아온다
	public List<ReplyVO> getList(@PathVariable("bno") int bno){
		log.info("bno : " + bno);
		return service.getList(bno);
	}
	
	
	
	/**
	 * 댓글 삭제
	 * rno을 경로로 받기 위해 @PathVariable 사용
	 * 
	 * @return map
	 */
	@GetMapping("/reply/delete/{rno}")
	public Map<String, Object> delete(@PathVariable("rno") int rno){
		Map<String, Object> map = new HashMap<String, Object>();
		int res = service.delete(rno);
		
		if(res>0) {
			map.put("result", "success");
		} else {
			map.put("result", "fail");
			map.put("message", "댓글 삭제 중 예외사항이 발생하였습니다");
		}
		return map;
	}
	
	
	
	/**
	 * RequestBody
	 * JSON 데이터를 원하는 타입으로 바인딩 처리
	 * 요청을 JSON 타입으로 받!아!옴 -> 나중에 객체로 바꿔줌
	 * @param vo
	 * @return
	 */
	@PostMapping("/reply/insert")
	public Map<String, Object> insert(@RequestBody ReplyVO vo){
		log.info("=============");
		log.info("ReplyVO : " + vo);
		
		int res = service.insert(vo);
	
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(res>0) {
			map.put("result", "success");
		} else {
			map.put("result", "fail"); 
			map.put("message", "댓글 등록 중 예외사항이 발생하였습니다");
		}
		return map;
	}
	
	
	@GetMapping("/reply/delete/{rno}")
	public ReplyVO update(@PathVariable("rno") int rno) {
		return service.getOne(rno);
	}
	
}

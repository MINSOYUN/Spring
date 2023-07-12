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

import com.momo.service.ReplService;
import com.momo.vo.ReplVO;

import lombok.extern.log4j.Log4j;

@RestController
@Log4j
public class ReplController {
	
	@Autowired
	ReplService service;
	
	@GetMapping("/repl/list/{bno}")
	public List<ReplVO> getList(@PathVariable("bno") int bno){
		return service.getList(bno);
	}
	
	@PostMapping("/repl/insert")
	public Map<String, Object> insert(@RequestBody ReplVO vo){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		int res = service.insert(vo);
		
		if(res>0) {
			map.put("result", "success");
		} else {
			map.put("result", "fail"); 
			map.put("message", "댓글 등록 중 예외사항이 발생하였습니다");
		}
		return map;
	}
	
	@GetMapping("repl/delete/{rno}")
	public Map<String, Object> delete(@PathVariable("rno") int rno){
		Map<String, Object> map = new HashMap<String, Object>();
		int res = service.delete(rno);
		if(res>0) {
			map.put("result", "success");
		} else {
			map.put("result", "fail"); 
			map.put("message", "댓글 등록 중 예외사항이 발생하였습니다");
		}
		return map;
	}

}

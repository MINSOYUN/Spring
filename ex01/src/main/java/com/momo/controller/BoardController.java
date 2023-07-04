package com.momo.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.momo.service.BoardService;
import com.momo.vo.BoardVO;

import lombok.extern.log4j.Log4j;

@Log4j    // log.info 사용 가능!
@Controller  // s 붙었는지 확인!
@RequestMapping("/board/*")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@GetMapping("list")
	public void getList(Model model) {
		List<BoardVO> list = boardService.getListXml();
		log.info("========");
		log.info("list : " + list);
		// model에 조회값 list를 담는다
		model.addAttribute("list", list);
	}
	
	
	@GetMapping("view")
	public void getOne(int bno, Model model) {
		BoardVO board =boardService.getOne(bno);
		log.info("========");
		log.info("board : " + board);
		model.addAttribute("board", board);
	}
	
	
	@GetMapping("write")
	public void write(Model model) {
		
	}
	
	
	@PostMapping("write")
	public String writeAction(BoardVO board, Model model) {
		log.info(board);
		String msg = "등록되었습니다";
		model.addAttribute("msg", msg);
		return "redirect:/board/list";
	}
	
	@GetMapping("update")
	public String update(BoardVO board, Model model) {
		BoardVO boardvo = boardService.getOne(board.getBno());
		boardvo.setTitle("제목!!");
		int res = boardService.update(boardvo);
		
		if(res>0) {
			return  "redirect:/board/list"
					+ "";
		} else{
			 return "redirect:/board/list";
		}
	}
	
	
	@GetMapping("totalCnt")
	public void getTotalCnt(Model model) {
		int res = boardService.getTotalCnt();
		model.addAttribute("totalCnt", res);
	}
}

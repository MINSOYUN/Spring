package com.momo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.momo.service.BookService;
import com.momo.vo.BookVO;
import com.momo.vo.Criteria;

import lombok.extern.log4j.Log4j;

@Log4j
@Controller
@RequestMapping("/book/*")
public class BookController {
	
	@Autowired
	BookService bookservice;
	
	// 도서 리스트 조회
	@GetMapping("list")
	public void list(Criteria cri, Model model) {
		// pageNo type -> int 타입이라 '' 공백 들어가면 오류!
		log.info("cri : " + cri);
		
		// 리스트 조회
		List<BookVO> list = bookservice.getList(cri, model);
		
		// return "/book/list";
	}
	
	
	// 도서 상세 조회
	@GetMapping("view")
	public void view(BookVO bookOne, Model model) {
		BookVO book = bookservice.getOne(bookOne.getNo(), model);
		log.info("===== view =====");
		log.info("book: " + book);
	}
	
	
	// 도서 등록
	@GetMapping("write")
	public void write() {
		
	}
	
	@PostMapping("write")
	public String writeAction(BookVO book, Model model, RedirectAttributes rttr) {
		int res = bookservice.insert(book);
		log.info("res : "  + res);
		log.info("book : " + book);
		
		String msg = "";
		if(res>0) {
			msg = "'" + book.getTitle() + "'" + "도서가 등록되었습니다";
			rttr.addFlashAttribute("msg", msg);
			return "redirect:/board/write";
		} else {
			msg = "도서 등록에 실패하였습니다";
			model.addAttribute("msg", msg);
			return "board/list";
		}
	}
	
}

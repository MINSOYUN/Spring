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
	}
	
	
	// 도서 등록
	@GetMapping("write")
	public void write() {

	}
	
	
	// 도서 등록
	@PostMapping("write")
	public String writeAction(BookVO book, Model model, RedirectAttributes rttr) {
		int res = bookservice.insert(book);
		log.info("res : "  + res);
		log.info("book : " + book);
		
		String msg = "";
		if(res>0) {
			msg = "'" + book.getTitle() + "'" + "도서가 등록되었습니다";
			rttr.addFlashAttribute("msg", msg);
			return "redirect:/book/list";
		} else {
			msg = "도서 등록에 실패하였습니다";
			model.addAttribute("msg", msg);
			return "/book/message";
		}
	}
	
	
	// 도서 삭제
	@GetMapping("delete")
	public String delete(BookVO book, RedirectAttributes rttr, Model model) {
	    int res = bookservice.delete(book.getNo());
	    log.info("no : " + book.getNo());
	    log.info("res : " + res);
	    
	    String msg = "";
	    if (res > 0) {
	        msg = "'" + book.getTitle() + "' 도서가 삭제되었습니다";
	        rttr.addFlashAttribute("msg", msg);
	        log.info("msg: " + msg);
	        return "redirect:/book/list";
	    } else {
	        msg = "도서 삭제에 실패하였습니다";
	        model.addAttribute("msg", msg);
	        return "/book/message";
	    }
	}
	
	// 도서 업데이트
	@GetMapping("edit")
	public String edit(BookVO bookvo, Model model) {
		BookVO book = bookservice.getOne(bookvo.getNo(), model);
		model.addAttribute("book", book);
		log.info("book : "  + book);
		return "/book/edit";
	}
	
	
	// 도서 업데이트
	@PostMapping("edit")
	public String editAction(BookVO book, Model model, RedirectAttributes rttr) {
		int res = bookservice.update(book);
		
		String msg = "";
		log.info("res : " + res);
		log.info("book : "+ book);
		
		if(res > 0) {
			msg = book.getNo() + "번 도서가 수정되었습니다";
			rttr.addFlashAttribute("msg", msg);  
			return "redirect:/book/view?no=" + book.getNo();
		} else {
			msg = "도서 정보수정 중 예외가 발생하였습니다";
			model.addAttribute("msg", msg);
			return "/book/message";
		}
	}
	


	
}

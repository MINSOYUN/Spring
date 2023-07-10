package com.momo.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.momo.service.BoardService;
import com.momo.vo.BoardVO;
import com.momo.vo.Criteria;
import com.momo.vo.PageDto;

import lombok.extern.log4j.Log4j;

@Log4j    // log.info 사용 가능!
@Controller  // s 붙었는지 확인!
@RequestMapping("/board/*")
public class BoardController {
	
	@GetMapping("/reply/test")
	public String test() {
		return "/reply/test";
	}

	@GetMapping("message")
	public void message(Model model) {
		
	}
	
	@GetMapping("list_bs")
	public void list_bs(Model model) {
		
	}
	
	/**
	 *  /board/msg
	 *  WEB-INF/views/board/msg.jsp
	 */
	@GetMapping("msg")
	public void msg() {
		
	}
	
	
	@Autowired
	BoardService boardService;
	
	
	/**
	 * 파라미터의 자동 수집
	 * 기본 생성자를 이용해서 객체를 생성
	 * -> setter 메서드를 이용해서 세팅
	 * @param cri
	 * @param model
	 */
	// 리스트 조회
	@GetMapping("list")
	public void getList(Criteria cri, Model model) {
		boardService.getListXml(cri, model);
	}
	
	
	// 상세보기
	@GetMapping("view")
	public void getOne(BoardVO paramVO, Model model) {
		BoardVO board =boardService.getOne(paramVO.getBno());
		log.info("========");
		log.info("board : " + board);
		model.addAttribute("board", board);
	}
	
	
	/**
	 * requestMapping에 /board/ 가 설정 되어 있으므로 /board/write
	 * @param model
	 */
	@GetMapping("write")
	public void write(Model model) {
		
	}
	
	
	/**
	 * RedirectAttributes
	 * 리다이렉트 URL 의 화면까지 데이터를 전달
	 * Model과 같이 매개변수로  받아 사용한다
	 * addAttribute : 주소표시줄에 msg 같이 넘어간다
	 * addFlashAttribute : 세션에 저장 후 페이지 이동
	 */
	@PostMapping("write")
	public String writeAction(BoardVO board, Model model, RedirectAttributes rttr) {
		log.info(board);
		
		String msg = "";
		
		// 시퀀스 조회 후 시퀀스 번호를 bno에 저장
		int res = boardService.insertSelectkey(board); 
		log.info("==========================");
		log.info("res : " + res);
		
		if(res > 0) {
			msg = board.getBno() + "번 게시글이 등록되었습니다";
			//rttr.addAttribute("msg", msg);   // url?msg=등록 -> 쿼리스트링으로 전달 ${param.msg}
			rttr.addFlashAttribute("msg", msg);  // 세션영역에 저장 -> 새로 고침시 유지되지 않음  ${msg}
			return "redirect:/board/list";  // request 영역 공유 x -> 데이터 유지x
		} else {
			msg = "등록 중 예외가 발생하였습니다";
			model.addAttribute("msg", msg);
			return "/board/message"; // redirect: 안쓰면 리스트 조회x
		}
	}
	
	
	// 게시글 업데이트
	@GetMapping("edit")
	public String edit(BoardVO board, Model model) {
		BoardVO boardvo = boardService.getOne(board.getBno());
		model.addAttribute("board", boardvo);
		return "/board/edit";
	}
	
	
	// 게시글 업데이트
	@PostMapping("editAction")
	public String editAction(BoardVO board, Model model, RedirectAttributes rttr) {
		int res = boardService.update(board);
		
		String msg = "";
		log.info("==========================");
		log.info("board : "+ board);
		
		if(res > 0) {
			// redirect 시 request 영역이 공유 되지 않으므로 RedirectAttributes 를 이용합니다
			
			msg = board.getBno() + "번 게시글이 수정되었습니다";
			rttr.addFlashAttribute("msg", msg);  
			return "redirect:/board/view?bno=" + board.getBno();
		} else {
			msg = "게시믈 수정 중 예외가 발생하였습니다";
			model.addAttribute("msg", msg);
			return "/board/message";
		}
	}
	
	
	// 게시글 삭제
	@GetMapping("delete")
	public String delete(Model model, RedirectAttributes rttr, String bno) {
		System.out.println("bno:" + bno);
		int res = boardService.delete(bno);
		System.out.println("삭제res:" + res);
		
		String msg = "";
		if(res>0) {
			msg = bno + "번 게시글이 삭제되었습니다";
			rttr.addFlashAttribute("msg", msg);  
			return "redirect:/board/list";
		} else {
			msg = "게시글 삭제 중 예외가 발생하였습니다";
			model.addAttribute("msg", msg);
			return "/board/message";
		}
	}
	
	
	@GetMapping("totalCnt")
	public void getTotalCnt(Criteria cri, Model model) {
		int res = boardService.getTotalCnt(cri);
		model.addAttribute("totalCnt", res);
	}
	
	
	// 게시글 삭제
}

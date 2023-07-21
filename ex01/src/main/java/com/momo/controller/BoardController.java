package com.momo.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
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
	
	@GetMapping("/reply/prec")
	public String test2() {
		return "/reply/prec";
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
		StopWatch stopwatch = new StopWatch();
		stopwatch.start();
		boardService.getListXml(cri, model);
		stopwatch.stop();
		log.info("======= list ========");
		log.info("수행시간 : " + stopwatch.getTotalTimeMillis()+"(ms)초");
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
	public String writeAction(BoardVO board, List<MultipartFile> files, Model model, RedirectAttributes rttr) {
		log.info(board);
		
		
		int res;
		
		try {
			// 시퀀스 조회 후 시퀀스 번호를 bno에 저장
			// 게시물 등록 + 파일 첨부
			String msg = "";
			res = boardService.insertSelectkey(board, files);
			
			if(res > 0) {
				msg = "Post" + board.getBno() + " has been registered";
				//rttr.addAttribute("msg", msg);   // url?msg=등록 -> 쿼리스트링으로 전달 ${param.msg}
				rttr.addFlashAttribute("msg", msg);  // 세션영역에 저장 -> 새로 고침시 유지되지 않음  ${msg}
				return "redirect:/board/list";  // request 영역 공유 x -> 데이터 유지x
			} else {
				msg = "An exception occurred during registration";
				model.addAttribute("msg", msg);
				return "/board/message"; // redirect: 안쓰면 리스트 조회x
			}
			
		} catch (Exception e) {
			log.info(e.getMessage());
			
			// 첨부파일 글씨가 있으면 오류 -> msg
			if(e.getMessage().indexOf("첨부파일")>-1) {
				model.addAttribute("msg", e.getMessage());
			} else {
				model.addAttribute("msg", "등록중 예외사항이 발생 하였습니다");
			}
			return "board/message";
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
	public String editAction(BoardVO board, Criteria cri, List<MultipartFile> files, Model model, RedirectAttributes rttr) {
		// ?pageNo=1   -> request.getParam("pageNo");  받기
		// request.setAttr("") request 내장 객체에 저장
		// request.setAttr("") ${param.pageNo}
		// request.getAttr("") / session.setAttr("") -> ${pageNo} 내장객체에 저장하면 ${}꺼내옴
		int res;
		
		try {
			
			res = boardService.update(board, files);
			String msg = "";
			log.info("==========================");
			log.info("board : "+ board);
			
			if(res > 0) {
				// redirect 시 request 영역이 공유 되지 않으므로 RedirectAttributes 를 이용
				msg = "Post" + board.getBno() + "has been modified";
				rttr.addFlashAttribute("msg", msg); 
				rttr.addAttribute("pageNo",cri.getPageNo());   // attribute 는 parameter로 넘겨준다
				rttr.addAttribute("pageNo",cri.getSearchField());   
				rttr.addAttribute("pageNo",cri.getSearchWord());   
				
				return "redirect:/board/view?bno=" + board.getBno();
			} else {
				msg = "Exception while modifying post";
				model.addAttribute("msg", "수정 중 예외사항이 발생하였습니다");
				return "/board/message";
			}
			
		} catch (Exception e) {
			if(e.getMessage().indexOf("첨부파일")>-1) {
				model.addAttribute("msg", "수정 중 예외사항이 발생하였습니다");
				e.printStackTrace();
			} else {
				
			}
		}
		return null;
		
	}
	
	
	// 게시글 삭제
	@GetMapping("delete")
	public String delete(Model model, RedirectAttributes rttr, String bno) {
		System.out.println("bno:" + bno);
		int res = boardService.delete(bno);
		System.out.println("삭제res:" + res);
		
		String msg = "";
		if(res>0) {
			msg = "Post" + bno + "has been deleted";
			rttr.addFlashAttribute("msg", msg);  
			return "redirect:/board/list";
		} else {
			msg = "Exception while deleting post";
			model.addAttribute("msg", msg);
			return "/board/message";
		}
	}
	
	
	@GetMapping("totalCnt")
	public void getTotalCnt(Criteria cri, Model model) {
		int res = boardService.getTotalCnt(cri);
		model.addAttribute("totalCnt", res);
	}
	
}

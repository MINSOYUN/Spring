package com.momo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.log4j.Log4j;

/**
 * root-context에 component-scan을 패키지에 적용해야 빈으로 등록!
 * 
 * ControllerAdvice 컨트롤러에 대한 예외를 처리하는 객체임을 명시
 * 컨트롤러가 실행 중 발생되는 오류이므로 500번 오류가 발생
 * 하는 경우 실행
 * 
 * ExceptionHandler Controller, RestController가 적용된 Bean 내에서 발생하는 예외를 하나의 메서드에서
 * 처리해주는 기능을 한다
 * 
 * @param ex
 * @param model
 * @return
 */
@RestControllerAdvice
@Log4j
public class CommonRestExceptionAdvice2 {
	
	// 500에 대한 에러
	// 어떤 예외인지 작성!
	@ExceptionHandler(Exception.class)
	public Map<String, Object> exception(Exception ex, Model model) {
		System.out.println("Exception...." + ex.getMessage());
		ex.printStackTrace();
		// 상단에 @Log4j 작성하여 사용 가능!
		log.info("RestException....");
		log.debug("로그테스트 + debug");
		log.error("로그테스트 + error");
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("result", "fail");
		map.put("message", ex.getMessage());
		
		return map;
	}
	
	
	// 400에 대한 에러
//	@ExceptionHandler(NoHandlerFoundException.class)
//	@ResponseStatus(HttpStatus.NOT_FOUND)
//	public String handle404(NoHandlerFoundException ex) {
//		return "/error/error400";
//	}
}

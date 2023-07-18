package com.momo.aop;


import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.momo.service.LogService;
import com.momo.vo.LogVO;

import lombok.extern.log4j.Log4j;



/**
 * AOP
 * 관점지향프로그래밍
 * 핵심 비즈니스 로직과 부가적인 관심사를 분리하여 개발하는 방법론
 * 코드의 중복을 줄이고 유지 보수성을 향상 시킬 수 있다
 * 
 * 부가적인 관심사
 * 로깅, 보안, 트랜잭션 등 애플리케이션에서 공통적으로 처리해야 하는 기능
 * 오류 발생 시 데이터베이스에 저장
 * @author user
 *
 */
@Aspect
@Log4j
@Component  // 자바 빈으로 등록해야 하기 때문
public class LogAdvice {
	
	/**
	 * pointcut : 언제 어디에 적용할 것인지 기술
	 * Before: 타겟 객체의 메소드가 실행되기 전에 호출되는 어드바이스
	 * JoinPoint를 통해 파라미터 정보 참고 가능
	 */
	@Before("execution(* com.momo.service.Board*.*(..))")  
	// 로직 실행 전 들어간다 (포인트컷지정(패키지명포함한 경로 작성))  / .. : 모든 메서드 포함
	public void logBefore() {
		log.info("=============");
		
	}
	
	
	
	/**
	 * JoinPoint
	 * 타겟에 대한 정보와 상태를 담고 있는 객체로 매개변수로 받아서 사용
	 * @param joinPoint
	 */
	// http://localhost:8090/board/view?bno=83 -> view에 Reply 있으니까!
	@Before("execution(* com.momo.service.Reply*.*(..))")
	public void logBeforeParams(JoinPoint joinPoint) {
		log.info("====== AOP =======");
		log.info("Param :" + Arrays.toString(joinPoint.getArgs()));  // Object 배열 반환
		log.info("Target : " +joinPoint.getTarget());
		log.info("Method : " + joinPoint.getSignature().getName());
	}
	
	
	
	/**
	 * Around
	 * 타겟의 메서드가  호출되기 이전 시점과 이후 시점에 모두처리해야 할 필요가 있는 부가 기능 정의
	 * 주업무로직을 실행 하기 위해 JoinPoint의 하위 클랙스인 ProceedingJoinPoint 타입의 파라미터를 필수적으로 선언해야 한다
	 * 타겟메서드를 실행하고 결과를 반한하기 위해서!
	 * @return pjp
	 */
	// service의 Board! 
	@Around("execution(* com.momo.service.Board*.*(..))")
	public Object logTime(ProceedingJoinPoint pjp) {
		
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		
		Object res = "";
		
		// 주 업무로직 실행(타겟 메서드의 실행 시점을 정할 수 있다)
		try {
			res = pjp.proceed();  // 실행 결과를 알 수 있다
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		stopWatch.stop();
		
		log.info("=============");
		log.info(
				pjp.getTarget() + "." + pjp.getSignature().getName() +
				" 수행시간 : " + stopWatch.getTotalTimeMillis() + "(ms) 초"); 
		
		return res;
	}
	
	
	
	/**
	 * AfterThrowing
	 * 타겟 메서드 실행 중 예외가 발생한 뒤에 실행할 부가 기능
	 * 오류가 발생내역을 테이블에 등록해준다
	 */
	@Autowired  // insert 사용해야 하기 때문에 @Autowired
	LogService logService;
	
	@AfterThrowing(pointcut="execution(* com.momo.service.Board*.*(..))", throwing="exception")
	public void logException(JoinPoint joinPoint, Exception exception) {
		
		// 예외가 발생 시 예외 내용을 테이블에 저장
		try {
			LogVO vo = new LogVO();
		
			vo.setClassName(joinPoint.getClass().getName());
			vo.setMethodName(joinPoint.getSignature().getName());
			vo.setParams(Arrays.toString(joinPoint.getArgs()));
			vo.setErrmsg(exception.getMessage());

			logService.insert(vo);
			
		} catch (Exception e) {
			log.info("로그테이블 저장 중 예외 발생");
			log.info(e.getMessage());
			e.printStackTrace();
		}
	}
}

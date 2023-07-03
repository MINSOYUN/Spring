package com.momo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.momo.vo.Member;
import com.momo.vo.MemberList;

/**
 * 스프링MVC에서 제공하고 있는 어노테이션을 이용하여 controller를 작성
 * 
 * 톰캣 서버를 실행하면 web.xml 파일의 설정을 읽어 서버를 시작합니다
 * web.xml 파일에 기술되어 있는 servlet-context.xml 파일의 component-scan에 등록된 패키지를 탐색하며
 * 클래스를 조사하고 객체 설정에 사용되는 어노테이션들을 가진 클래스를 객체로 생성하고 관리한다
 * 
 * MVC에서 사용되는 어노테이션 학습!
 * @author user
 * 
 * 스프링 controller 장점
 * 1. 파라미터 자동 수집
 * 2. URL 매핑을 메서드 단위로 처리 ex)getmapping
 * 3. 화면에 전달할 데이터는 Model 에 담아주기만 하면 된다 (model: 데이터를 화면까지 전달)
 * 4. 간단한 페이지 전환(forward, redirect)
 * 5. 상속/인터페이스 방식 대신에 어노테이션만으로도 필요한 설정 가능
 */

@Controller
@RequestMapping("/mapping/*")
// 해당 클래스의 인스턴스를 스프링의 빈으로 등록하고 컨트롤러로 사용
public class MappingController {
	
	/**
	 * RequestMapping
	 * 클래스의 상단에 적용 시 현재 클래스의 모든 메서드들의 기본 URL 경로를 지정
	 * 메서드의 상단에 적용시 메서드의 URL 경로를 지정
	 * get 방 식과 post 방식 모두 처리 하고 싶은 경우 배열로 받을 수 있다
	 *  /mapping/requestMapiing URI을 GET 메서드로 호출하면 해당 메서드가 실행된다
	 * @return
	 */
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	//@GetMapping("/")
	public String requestMapping() {
		return"mapping";
	}
	
	
	// get, post 방식으로 호출되도 실행 가능
	// /mapping/requestMapping URI 을 GET 메서드로 호출하면 해당 메서드가 실행된다
	@RequestMapping(value="/requestMapping", method = {RequestMethod.GET, RequestMethod.POST})
	public String requestMapping2() {
		System.out.println("/requestMapping 호출");
		return"mapping";
	}
	
	
	/**
	 * 스프링 4.3 이후에는 GetMapping, PostMapping 등으로 간단히 표현 가능
	 * 어노테이션 사용이 불가능 할 경우 스프링의 버전을 확인한다
	 * GetMapping
	 * Get 방식의 요청을 처리한다
	 * 
	 * 파라미터의 자동 수집
	 * RequestParam 어노테이션을 이용하면 기본 타입의 데이터를 지정한 타입으로 받을 수 있습니다
	 * 단 , 타입이 불일치 하는경우 400 오류가 발생 할 수 있다
	 * 
	 * VO 객체를 지정 할 경우 객체를 생성 후 파라미터를 name 속성과 일치하는 필드에 세팅 해준다
	 * 단 , 타입이 불일치 하는경우 400 오류가 발생 할 수 있다
	 * @return
	 */
	// request 로부터 넘어온 param의 name
	@GetMapping("/getMapping")
	public String getMapping(@RequestParam("name") String name, @RequestParam("age") int age, Model model) {
		model.addAttribute("name", name);
		model.addAttribute("age", age);
		
		System.out.println("/getMapping 호출");
		System.out.println("name: "+ name);
		System.out.println("age: "+ age);
		return"mapping";
	}
	
	
	/**
	 * 파라미터를 VO, DTO에 수집한 경우(자바 빈으로 수집), 별도의 저장 없이 화면까지 전달 된다
	 * 
	 * 화면에 값을 전달 하고 싶은 경우
	 * Model 객체를 매개변수로 받아 addAttribute() 메서드를 이용
	 * model.addAttribute("이름", 값)
	 * @return
	 */
	@GetMapping("getMappingVO")
	public String getMappingVO(Member member, Model model) {
		System.out.println("member: "+ member);
		model.addAttribute("message", "파라미터 자동 수집!");
		return"mapping";
	}
	
	
	
	@GetMapping("getMappingArr")
	public String getMappingArr(@RequestParam("ids") String[] ids) {
		for(String id : ids) {
			System.out.println("ids : " + id);
		}
		return"mapping";
	}
	
	
	@GetMapping("getMappingList")
	public String getMappingList(@RequestParam("ids") List<String> ids) {
		ids.forEach(id -> {
			System.out.println("ids : " + id);
		});
		return"mapping";
	}
	
	
	
	@GetMapping("getMappingMemberList")
	public String getMappingMemberList(MemberList list) {
		System.out.println(list);
		return"mapping";
	}
	
}

package com.momo.vo;


import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


/**
 * Lombok 라이브러리
 * getter / setter, equals, toString 등의 메서드를 자동 생성 해준다
 * 
 * Date 어노테이션
 * IDE(이클립스, STS)에 설치 후 롬복 라이브러리를 추가 후 사용 가능
 * IDE에 설치가 되어 있지 않으면 어노테이션을 추가 해도 메서드가 생성되지 않을 수 있다
 * Outline View를 통해 메서드가 생성되었는지 확인!
 * @author user
 *
 */
	// 롬복의 역할 : setter, getter 및 생성자 및 toString 등 자동 생성
	// 롬복 제공 @Data -> outline 확인
	// porm.xml 에 롬복 라이브러리 추가하여 메이븐 디펜던시에서도 롬복 라이브러리가 자동적으로 추가되었다
	@Data
	public class Member {
		private String id;
		private String pw;
		private String name;
		private int age;
		private String adminyn;
		
		@DateTimeFormat(pattern="yyyy/MM/dd")
		private Date dueDate;
		
	}

package com.momo.vo;

import lombok.Data;

	// 롬복의 역할 : setter, getter 및 생성자 및 toString 등 자동 생성
	// 롬복 제공 @Data -> outline 확인
	// porm.xml 에 롬복 라이브러리 추가하여 메이븐 디펜던시에서도 롬복 라이브러리가 자동적으로 추가되었다
	@Data
	public class Member {
		String id;
		String pw;
		String name;
	}

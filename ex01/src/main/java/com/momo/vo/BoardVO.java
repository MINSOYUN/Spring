package com.momo.vo;

import lombok.Data;

// setter getter 자동 생성
@Data
public class BoardVO {
	
	private int bno;
	private String title;
	private String content;
	private String writer;
	private String regDate;
	private String updateDate;
	
	
}

package com.momo.vo;

import lombok.Data;

@Data
public class BookVO {
	private int no;		// 도서 일련번호
	private String title;	// 도서명
	private String author;	// 작가
	private String publisher;
	private String rentyn;	// 도서 대여여부
	private String rentynStr;   // 사용자한테 화면에 보여 줄 시 사용

	private String ofile;	// 원본 파일명
	private String sfile;	// 저장된 파일명
	
	private String id;  // 대여자 id	
	private String rentno;	
	private String startDate;  // 대여시작일
	private String endDate;  // 반납가능일
	private String returnDate;	// 반납일
	
	private String visitcount;
	private String postdate;
	private String info;
}


package com.momo.vo;

import lombok.Data;

@Data
public class PageDto {

	// 페이지를 구성하는데 필요한 요소
	int startNo;           // 페이지 블럭 시작번호
	int endNo;             // 페이지 블럭 끝번호
    int realEnd;		   // 게시물 끝페이지 번호 (<< 끝 페이지로 이동 >>)
	boolean prev, next;    // 이전, 다음 버튼 (boolean true 이면 보여진다)
	
	
	// 페이지 블럭을 생성하기 위해 필요한 요소
	int total;             // 총 개시물 수
	Criteria criteria;     // 페이지 번호, 페이지당 게시물수 (pageNo, amount)
	
	
	// 페이지 블럭 초기화
	public PageDto(int total, Criteria criteria) {
		super();
		this.total = total;
		this.criteria = criteria;
		
		// 페이지 블럭의 끝번호
		endNo = (int)Math.ceil(criteria.pageNo / 5.0) * 5;
		System.out.println("pageNo : " + criteria.pageNo);
		// 페이지 블럭의 시작 번호
		startNo = endNo - (5-1);
		
		// 총 게시물의 수를 페이지당 보여지는 게시물의 수로 나눠서 실제 끝 페이지 번호를 구함
		realEnd = (int)Math.ceil((total * 1.0) / criteria.amount);
		
		endNo = endNo> realEnd ? realEnd : endNo;

		prev = startNo > 1 ? true : false;
		next = endNo == realEnd ? false : true;
	}
}

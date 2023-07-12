package com.momo.vo;

import lombok.Data;

@Data
public class ReplVO {
	private int rno; // 댓글번호
	private int bno; // 게시글번호
	private String reply; // 댓글
	private String replyer; // 댓글작성자
	private String replydate; // 댓글작성일 sysdate
	private String updatedate;  // 댓글수정일
}

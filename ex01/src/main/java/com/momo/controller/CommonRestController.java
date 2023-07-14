package com.momo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.momo.vo.PageDto;
import com.momo.vo.ReplyVO;

public class CommonRestController {
	// ========= Map res, msg return ========== //
	
	private final String REST_WRITE = "회원가입";
	private final String REST_EDIT = "수정";
	private final String REST_DELETE = "삭제";
	private final String REST_SELECT = "조회";
	// memberController 에서 사용하기 위함 -> protected
	protected final String REST_SUCCESS = "success";
	protected final String REST_FAIL = "fail";
	
	
	/**
	 * 입력, 수정, 삭제의 경우 int 값을 반환
	 * 결과를 받아서 Map을 생성 후 반환 -> 타입 Map
	 * @return
	 */
	public Map<String, Object> responseMap(int res, String msg){
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(res > 0) {
			map.put("result", "success");
			map.put("msg", msg + "되었습니다. ๑'ٮ'๑");
		} else {
			map.put("result", "fail");
			map.put("msg", msg + "중 예외가 발생하였습니다.");
		}
		
		return map;
	}
	
	
	// 원하는 msg 출력
	public Map<String, Object> responseMap(String res, String msg){
		Map<String, Object> map = new HashMap<String, Object>();
		
			map.put("result", res);
			map.put("msg", msg);
		
		return map;
	}
	
	
	// List<?> -> 어떤 타입이든 상관 x
	public Map<String, Object> responseListMap(List<?> list, PageDto pageDto){
		// null 이 아니면  1 -> res > 0 성공
		int res = list != null? 1: 0;
		Map<String, Object> map = responseMap(res, REST_SELECT);
		// map에 list 와 pageDto put
		map.put("list", list);
		map.put("pageDto", pageDto);
		return map;
	}
	
	
	public Map<String, Object> responseWriteMap(int res){
		// 등록 + response 되었습니다
		return responseMap(res, REST_WRITE);   // return map -> responseMap 한번 더 호출
	}
	
	public Map<String, Object> responseEditMap(int res){
		return responseMap(res, REST_EDIT);
	}
	
	public Map<String, Object> responseDeleteMap(int res){
		return responseMap(res, REST_DELETE);
	}
	

}

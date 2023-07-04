package com.momo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.momo.mapper.BoardMapper;
import com.momo.vo.BoardVO;

	/**
	 * 각 계층간의 연결은 인터페이스를 활용하여 느슨한 결합을 한다
	 * 느슨한 결합 : 하나의 컴포넌트 변경이 다른 컴퍼넌트들의 변경을 요구하는 위험을 줄이는 것을 목적으로 하는 시스템에서 컴퍼넌트 간의 내부 의존성을 줄이는 것을 추구하는 디자인 목표
	 * service: 게층 구조상 비즈니스 영역을 담당하는 객체임을 표시
	 * 
	 * root-context.xml : component-scan 속성에 패키지를 등록
	 *
	 *서비스를 Interface로 생성하는 이유
	 *1. 내부 로직의 분리 
	 *인테페이스를 사용함으로써 내부 로직의 변경, 수정 시 유연하게 대처 할 수 있다
	 *2. 구현체의 전환이 용이
	 *구현체의 변경, 교체가 용이하다
	 *
	 *3. 테스트 용이성
	 *단위 테스트 시 테스트용 구현체를 이용함으로써 테스트를 수행
	 */
@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public List<BoardVO> getListXml() {
		return boardMapper.getListXml();
	}

	@Override
	public int insert(BoardVO board) {
		int res = boardMapper.insert(board);
		return res;
	}

	@Override
	public int insertSelectkey(BoardVO board) {
		boardMapper.insertSelectkey(board);
		return 0;
	}

	@Override
	public BoardVO getOne(int bno) {
		BoardVO board=boardMapper.getOne(bno);
		return board;
	}

	@Override
	public int delete(int bno) {
		int res = boardMapper.delete(bno);
		return res;
	}

	@Override
	public int update(BoardVO board) {
		int res = boardMapper.update(board);
		return res;
	}

	@Override
	public int getTotalCnt() {
		int totalCnt = boardMapper.getTotalCnt();
		return totalCnt;
	}

}

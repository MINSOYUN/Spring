package com.momo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.momo.mapper.BoardMapper;
import com.momo.vo.BoardVO;
import com.momo.vo.Criteria;
import com.momo.vo.PageDto;

import lombok.extern.log4j.Log4j;

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
@Log4j
@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	private BoardMapper boardMapper;
	
	@Autowired
	private FileService fileservice;
	
	
	@Override
	public List<BoardVO> getListXml(Criteria cri, Model model) {
		List<BoardVO> list = boardMapper.getListXml(cri);
		int totalCnt = boardMapper.getTotalCnt(cri);
		PageDto pageDto = new PageDto(totalCnt, cri);
		
		log.info("============");
		log.info("list : " + list);
		log.info("cri : " + cri);
		
		model.addAttribute("list", list);
		model.addAttribute("totalCnt", totalCnt);
		model.addAttribute("pageDto", pageDto);
		//return boardMapper.getListXml(cri);
		return null;
	}

	@Override
	public int insert(BoardVO board) {
		int res = boardMapper.insert(board);
		return res;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public int insertSelectkey(BoardVO board, List<MultipartFile> files) throws Exception {
		// 게시물 등록
		int res = boardMapper.insertSelectkey(board);
		
		// 파일 첨부
		fileservice.fileupload(files, board.getBno());
		
		return res;
	}

	@Override
	public BoardVO getOne(int bno) {
		BoardVO board=boardMapper.getOne(bno);
		return board;
	}

	@Override
	public int delete(String bno) {
		// 게시물  삭제 시 첨부된 파일이 있는 경우 오류가 발생
		// 첨부 파일 리스트 조회 - fileuploadService
		// 리스트를 돌면서 삭제 처리하며 모든 파일 삭제 - fileService
		int res = boardMapper.delete(bno);
		return res;
	}

	@Override
	public int update(BoardVO board, List<MultipartFile> files) throws Exception {
		// 게시물 등록
		int res = boardMapper.update(board);
		
		// 파일 처리
		fileservice.fileupload(files, board.getBno());
		return res;
	}

	@Override
	public int getTotalCnt(Criteria cri) {
		int totalCnt = boardMapper.getTotalCnt(cri);
		return totalCnt;
	}
	
	@Override
		public int updateReplyCnt(int bno, int amount) {
		return boardMapper.updateReplyCnt(bno, amount);
		}

}

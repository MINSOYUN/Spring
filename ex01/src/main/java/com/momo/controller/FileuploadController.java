package com.momo.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.momo.mapper.FileMapper;
import com.momo.service.FileService;
import com.momo.vo.FileVO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnails;

@Controller
@Log4j
public class FileuploadController extends CommonRestController{
	
	// 저장할 경로
	public static final String ATTACHES_DIR ="c:\\upload\\"; // 파일 경로 역슬래시 사용
	
	// service 사용하기 위해 주입
	@Autowired
	FileService service;
	
	
	@GetMapping("/file/fileupload")
	public void fileupload() {
		
	}
	
	
	/**
	 * <오류 나는 경우>
	 * 전달된 파일이 없는 경우
	 * enctype="multipart/form-data" 오타
	 * 설정이 안되어있을 경우 -> 라이브러리 추가, bean 객체 생성
	 * @param files
	 * @return
	 * @throws Exception 
	 */
	@PostMapping("/file/fileuploadAction")
	// root-context 에 multipartResolver 등록해서  MultipartFile 사용 가능
	public String fileAction(List<MultipartFile> files, int bno, RedirectAttributes rttr) throws Exception {
		// 파일 저장 및 등록 메서드 호출
		int insertRes = service.fileupload(files, bno);
		
		String msg = insertRes + "건 저장되었습니다";
		rttr.addAttribute("msg", msg); // 리다이렉트 시 URL에 파라미터로 저장
		return "redirect:/file/fileupload?msg="+msg;
	}
	 
	
	
	// fetch 용
	@PostMapping("/file/fileuploadActionFetch")
	public @ResponseBody Map<String, Object> fileAction(List<MultipartFile> files, int bno) throws Exception {
		log.info("fileuploadActionFetch");
		int insertRes = service.fileupload(files, bno);
		log.info("업로드 건수 : " + insertRes);
		
		return responseMap("success", insertRes+"건 저장되었습니다");
	}
	
	
	// 파일 조회
	@GetMapping("/file/list/{bno}")   
	public @ResponseBody Map<String, Object> fileList(@PathVariable("bno") int bno) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", service.getList(bno));
		return map;
	}
	
	
	
	public static void main(String[] args) {
		LocalDate currentDate = LocalDate.now();
		System.out.println("currentDate : " + currentDate);   // 2023-07-18
		
		String uploadPath = currentDate.toString().replace("-", File.separator) + File.separator;
		System.out.println("경로 : " + uploadPath);  // 2023\07\18
	}
	
	
	
	
	@GetMapping("/file/delete/{uuid}/{bno}")
	public @ResponseBody Map<String, Object> delete(@PathVariable("uuid") String uuid, @PathVariable("bno") int bno){
		int res = service.delete(uuid, bno);
		log.info("res : " + res);
		return responseMap(REST_SUCCESS, res +"건 삭제되었습니다");
	}
	
	
	/*
		파일 다운로드
		컨텐츠 타입을 다운로드 받을 수 있는 형식으로 지정하여
		브라우저에서 파일을 다운로드 할 수 있게 처리
	*/
	@GetMapping("/file/download")
	// byte 배열  ??
	public ResponseEntity<byte[]> download(String fileName){
		log.info("download file : " + fileName);   // 파일 이름
		
		// org.springframework.http.HttpHeaders
		//  HTTP 응답 헤더를 설정하기 위해 생성자 생성
		HttpHeaders headers = new HttpHeaders();
		
		// fileName : 파라미터로 넘어온 파일 정보
		File file = new File(ATTACHES_DIR + fileName);  // 요청하는 경로에서 해당하는 파일이 있는지 확인
		log.info("file : " + file);
		// 파일이 있으면
		if(file.exists()) {
			// 컨텐츠타입을 지정 : APPLICATION_OCTET_STREAM -> 이진 파일의 콘텐츠 유형 / 바이트배열로 다운 받겠다
			headers.add("contentType", MediaType.APPLICATION_OCTET_STREAM.toString());
			
			// 컨텐츠에 대한 추가 설명 및 파일 이름 한글처리
			try {
				// fileName.getBytes("UTF-8") : 인코딩 -> 다운로드 받을 파일 이름 설정
				// 헤더의 이름 설정 -> getFirst(), get()으로 값을 가져올 수 있다 
				headers.add("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("UTF-8"), "ISO-8859-1")+"\"");
				try {
					// 파일을 byte 배열로 받아와 ResponseEntity로 반환
					// ========================= 다운 받는 코드 =========================
					return new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), headers, HttpStatus.OK); // HttpStatus.OK : Http 통신의 결과 코드
				} catch (IOException e) {
					e.printStackTrace();
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);  // 에러 500
				}  
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // 에러 404
		}
	}
	
	
}

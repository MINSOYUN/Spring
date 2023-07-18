package com.momo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
public class FileuploadController {
	
	
	// service 사용하기 위해 주입
	@Autowired
	FileService service;
	
	
	@GetMapping("/file/fileupload")
	public void fileupload() {
		
	}
	
	
	// 저장할 경로
	private static final String ATTACHES_DIR ="c:\\upload\\"; // 파일 경로 역슬래시 사용
	
	
	/**
	 * <오류 나는 경우>
	 * 전달된 파일이 없는 경우
	 * enctype="multipart/form-data" 오타
	 * 설정이 안되어있을 경우 -> 라이브러리 추가, bean 객체 생성
	 * @param files
	 * @return
	 */
	@PostMapping("/file/fileuploadAction")
	// root-context 에 multipartResolver 등록해서  MultipartFile 사용 가능
	public String fileAction(List<MultipartFile> files, int bno, RedirectAttributes rttr) {
		int insertRes = 0;
		
		for(MultipartFile file : files) {
			// 선택된 파일이 비어 있는 경우 다음 파일로 이동
			if(file.isEmpty()) {
				continue;
			}
			
			log.info("oFileName : " + file.getOriginalFilename()); // 업로드한 파일 이름
			log.info("name : " + file.getName());  // files
			log.info("size : " + file.getSize());  // 사이즈
			
			try {
				/**
				 * 소프트웨어 구축에 쓰이는 식별자(중복되지 않는 값) 표준
				 * 파일이름이 중복되어 파일이 소실되지 않도록 uuid를 붙여서 저장
				 */
				// UUID
				UUID uuid = UUID.randomUUID();
				String saveFileName = uuid + "_" + file.getOriginalFilename();
				
				String uploadPath = getFolder();
				// 파일경로+파일제목 c:/upload/2023/7/18
				// getFolder : uploadPath
				File sFile = new File(ATTACHES_DIR + uploadPath + saveFileName);
				
				// file : 원본파일 / sFile : 저장할 대상 파일
				file.transferTo(sFile);
				
				FileVO vo = new FileVO();
				vo.setBno(bno);
				vo.setFilename(file.getOriginalFilename());
				vo.setFiletype("I");
				vo.setUploadpath(uploadPath);
				vo.setUuid(uuid.toString());
				log.info("vo:" + vo);
				
				
				// 주어진 파일의 Mime 유형 확인
				// Files.probeContentType -> MIME 타입(Content Type)을 검사
				String contentType = Files.probeContentType(sFile.toPath());  // toPath : 경로
				log.info("toPath 경로 :" + sFile.toPath());
				log.info("contentType :" + Files.probeContentType(sFile.toPath()));
				
				
				// Mime 타입을 확인하여 이미지인 경우  썸네일을 생성
				// contentType 문자열이 "image"로 시작하는지를 확인
				if(contentType.startsWith("image")) {
					vo.setFiletype("I");
				
					// 썸네일 경로 생성
					String thumbnail = ATTACHES_DIR + uploadPath +"s_"+ saveFileName;   // s_ 만 추가
					
					// 원본파일, 크기, 저장될 경로
					Thumbnails.of(sFile).size(100, 100).toFile(thumbnail);  // 썸네일의 경로
					
					log.info("thumbnail 경로:" + thumbnail);
					
				} else {
					vo.setFiletype("F");
				}
				
				int res = service.insert(vo);
				log.info("res : " +res);
				log.info("sFile : " +sFile);
				
				// 추가 성공
				if(res>0) {
					insertRes++;
				}
			
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		String msg = insertRes + "건 저장되었습니다";
		rttr.addAttribute("msg", msg);
		return "redirect:/file/fileupload?msg="+msg;
	}
	
	
	
	@GetMapping("/file/list/{bno}")   
	public @ResponseBody Map<String, Object> fileList(@PathVariable("bno") int bno) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", service.getList(bno));
		return map;
	}
	
	
	// 중복 방지용 업로드 날짜를 폴더 이름으로 사용
	// 형식: 2023/07/18
	public String getFolder() {
		LocalDate currentDate = LocalDate.now();
		log.info("currentDate : " + currentDate);
		
		// c:\\upload\\2023\07\18곰돌이.jpg 면 파일명이 되지 않으므로 파일 앞에 구분자 하나 더 넣어준다 -> 2023\07\18\
		String uploadPath = currentDate.toString().replace("-", File.separator) + File.separator;
		log.info("경로 : " + uploadPath);  // -를 파일의 기본자(\)로 변경
		
		// 폴더 없으면 생성
		File saveDir = new File(ATTACHES_DIR + uploadPath);   // c:\\upload\\2023\07\18\
		
		if(!saveDir.exists()) {
			if(saveDir.mkdirs()) {
				// 디렉토리 여러개 생성
				log.info("폴더 생성!!");
			} else {
				log.info("폴더 생성 실패!!");
			}
				
		}
		return uploadPath; // 2023\07\18\
	}
	
	
	
	public static void main(String[] args) {
		LocalDate currentDate = LocalDate.now();
		System.out.println("currentDate : " + currentDate);   // 2023-07-18
		
		String uploadPath = currentDate.toString().replace("-", File.separator) + File.separator;
		System.out.println("경로 : " + uploadPath);  // 2023\07\18
	}
}

package com.momo.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.momo.controller.FileuploadController;
import com.momo.mapper.FileMapper;
import com.momo.vo.FileVO;

import lombok.extern.log4j.Log4j;
import net.coobird.thumbnailator.Thumbnails;

@Log4j
@Service
public class FileServiceImpl implements FileService {
	
	@Autowired
	FileMapper mapper;
	
	@Override
	public List<FileVO> getList(int bno) {
		return mapper.getList(bno);
	}
	
	@Override
	public int insert(FileVO vo) {
		return mapper.insert(vo);
	}
	
	@Override
	public int delete(String uuid, int bno) {
		// 파일 삭제
		// 삭제 할 파일 조회
		// 삭제
		FileVO vo = mapper.getOne(uuid, bno);
		String savePath = vo.getSavePath();
		String s_savePath = vo.getS_savePath();
		
		System.out.println("savePath : " + savePath);
		System.out.println("s_savePath : " + s_savePath);
		
		// 원본 파일 삭제
		if(savePath != null && !savePath.equals("")) {
			File file = new File(FileuploadController.ATTACHES_DIR + savePath);
			
			// 기본 경로 + 경로 파일이 존재하면 삭제
			if(file.exists()) {
				if(!file.delete()) {
					System.out.println("path : " + savePath);
					System.out.println("파일 삭제에 실패하였습니다");
				}
			}
		}
		
		// 새 파일(+식별자) 삭제
		if(s_savePath != null && !s_savePath.equals("")) {
			File file = new File(FileuploadController.ATTACHES_DIR + s_savePath);
			
			if(file.exists()) {
				if(!file.delete()) {
					System.out.println("path : " + savePath);
					System.out.println("파일 삭제에 실패하였습니다");
				}
			}
		}
		
		// 데이터 베이스에서 삭제
		return mapper.delete(uuid, bno);
	}
	
	
	@Override
	public FileVO getOne(String uuid, int bno) {
		return mapper.getOne(uuid, bno);
	}
	
	
	/**
	 * 첨부파일 저장 및 데이터 베이스에 등록
	 * @param files
	 * @param bno
	 * @return
	 * @throws Exception 
	 */
	public int fileupload(List<MultipartFile> files, int bno) throws Exception {
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
				File sFile = new File(FileuploadController.ATTACHES_DIR + uploadPath + saveFileName);
				
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
					String thumbnail = FileuploadController.ATTACHES_DIR + uploadPath +"s_"+ saveFileName;   // s_ 만 추가
					
					// 원본파일, 크기, 저장될 경로
					Thumbnails.of(sFile).size(100, 100).toFile(thumbnail);  // 썸네일의 경로
					
					log.info("thumbnail 경로:" + thumbnail);
					
				} else {
					vo.setFiletype("F");
				}
				
				int res = insert(vo);
				log.info("res : " +res);
				log.info("sFile : " +sFile);
				
				// 추가 성공
				if(res>0) {
					insertRes++;
				}
			
			} catch (IllegalStateException e) {
				e.printStackTrace();
				throw new Exception("첨부파일 등록 중 예외사항이 발생하였습니다.(IllegalStateException)"); // 메서드 던짐
			} catch (IOException e) {
				e.printStackTrace();
				throw new Exception("첨부파일 등록 중 예외사항이 발생하였습니다.(IOException)");
			} catch(Exception e) {  // DB 처리 중 오류
				e.printStackTrace();
				throw new Exception("첨부파일 등록 중 예외사항이 발생하였습니다.(Exception)");
			}
		}
		return insertRes;
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
		File saveDir = new File(FileuploadController.ATTACHES_DIR + uploadPath);   // c:\\upload\\2023\07\18\
		
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
}

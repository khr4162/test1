package com.ezen.myproject.handler;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.tika.Tika;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ezen.myproject.domain.FileVO;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;

@Slf4j
@Component
@AllArgsConstructor
public class FileHandler {
	
	private final String UP_DIR = "D:\\_myweb\\_java\\fileupload2";

	public List<FileVO> uploadFiles(MultipartFile[] files) {
		LocalDate date = LocalDate.now();
//		log.info("나오나요?");
		String today = date.toString();
		today = today.replace("-", File.separator);
		
		File folders = new File(UP_DIR, today);
		
		if(!folders.exists()) {
			folders.mkdirs();
		}
		
		List<FileVO> flist = new ArrayList<FileVO>();
//		log.info("이건 나오나요?");
		for(MultipartFile file : files) {
			log.info("나옴?"+files);
			FileVO fvo = new FileVO();
			fvo.setSave_dir(today);
			fvo.setFile_size(file.getSize());
			
			String originalFileName = file.getOriginalFilename();
			String onlyFileName = originalFileName.substring(
					originalFileName.lastIndexOf(File.separator)+1);
			log.info(">>> onlyFileName : "+onlyFileName);
			fvo.setFile_name(onlyFileName);
			
			UUID uuid = UUID.randomUUID();
			fvo.setUuid(uuid.toString());
			
			String fullfileName = uuid.toString()+"_"+onlyFileName;
			File storeFile = new File(folders, fullfileName);
			log.info("storeFile : "+storeFile);
			
			try {
				file.transferTo(storeFile);
				if(isImagerFile(storeFile)) {
					fvo.setFile_type(1);
					File thumbNail =  new File(folders, uuid.toString()+"_th_"+onlyFileName);
					Thumbnails.of(storeFile).size(100, 100).toFile(thumbNail);
				}
			} catch (Exception e) {
				log.info("오류. 파일 생성 오류");
				e.printStackTrace();
			}
			flist.add(fvo);
			log.info(">>> fvo : "+fvo);
		}
		
		return flist;
	}

	private boolean isImagerFile(File storeFile) throws IOException {
	      String mimeType = new Tika().detect(storeFile);
	      return mimeType.startsWith("image")? true : false;
	   }

}

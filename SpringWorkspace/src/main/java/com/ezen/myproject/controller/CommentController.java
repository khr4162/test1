package com.ezen.myproject.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.myproject.domain.CommentVO;
import com.ezen.myproject.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/comment/*")
@Slf4j
public class CommentController {
	
	@Inject
	private CommentService csv;
	

	@PostMapping(value = "/post", consumes = "application/json",
			produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> post(@RequestBody CommentVO cvo){
		log.info(">>> cvo : "+cvo);
		
		int isOk = 1;
		return isOk > 0? new ResponseEntity<String>("1", HttpStatus.OK)
		: new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@PutMapping(value = "/{cno}", consumes = "application/json"
			, produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> edit(@PathVariable("cno")int cno,
			@RequestBody CommentVO cvo){
		log.info(">>> edit cno : "+cno);
		log.info(">>> edit cvo : "+cvo);
		
		int isOk = csv.edit(cvo);
		
		return isOk>0?
				new ResponseEntity<String>("1",HttpStatus.OK)
				:new ResponseEntity<String>("0",HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping(value = "/{cno}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String>remove(@PathVariable("cno")int cno){
		log.info(">>> remove cno : "+cno);
		int isOk = csv.remove(cno);
		return isOk > 0? new ResponseEntity<String>("1", HttpStatus.OK)
		: new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

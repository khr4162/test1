package com.ezen.myproject.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ezen.myproject.domain.BoardDTO;
import com.ezen.myproject.domain.BoardVO;
import com.ezen.myproject.domain.FileVO;
import com.ezen.myproject.domain.PagingVO;
import com.ezen.myproject.domain.UserVO;
import com.ezen.myproject.handler.FileHandler;
import com.ezen.myproject.handler.PagingHandler;
import com.ezen.myproject.repository.UserDAO;
import com.ezen.myproject.service.BoardService;
import com.ezen.myproject.service.CommentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/board/*")
public class BoardController {
	
	@Inject
	private BoardService bsv;
	@Inject
	private UserDAO udao;
	@Inject
	private CommentService csv;
	@Inject
	private FileHandler fhd;
	
	@GetMapping("/register")
	public String registerGet() {
		return "/board/register";
	}
	
	@PostMapping("/register")
	public String registerPost(BoardVO bvo, RedirectAttributes rArr,
			@RequestParam(name="files", required = false)MultipartFile[] files) {
		log.info(">>>bvo :"+bvo);
		log.info(">>> files : "+files);
		
		List<FileVO> flist = null;
		
		if(files[0].getSize()>0) {
			flist = fhd.uploadFiles(files);
		}else {
			log.info("파일이 없습니다");
		}
		BoardDTO bdto = new BoardDTO(bvo,flist);
		int isOk = bsv.register(bdto);
		
//		int isOk = bsv.register1(bvo);
		log.info(">>>board register >>"+(isOk>0? "성공":"실패"));
		rArr.addFlashAttribute("isOk", isOk);
		
		return "redirect:/board/list";
	}
	
	@GetMapping("/list")
	public String list(Model m, PagingVO pvo) {
		log.info(">>> pageVO : "+pvo);
		List<BoardVO> list = bsv.getList(pvo);
		m.addAttribute("list", list);
		int totalCount = bsv.getTotalCount(pvo);
		PagingHandler ph = new PagingHandler(pvo, totalCount);
		m.addAttribute("ph", ph);
		return "/board/list";
	}
	
	@GetMapping({"/detail","modify"})
	public void detail(Model m, @RequestParam("bno")int bno, HttpServletRequest r) {
		log.info(">>> bno : "+bno);
		String mapping = r.getRequestURI();
		BoardVO bvo = bsv.getDetail(bno);
		BoardDTO bdto = bsv.getDetailFile(bno);
		String path = mapping.substring(mapping.lastIndexOf("/")+1);
		log.info(">>> path : "+path);
		if(path.equals("datail")) {
			int isOk = bsv.readCount(bno);
		}
		m.addAttribute("boardDTO", bdto);
		
		log.info(""+bdto);
	}
	
	@PostMapping("/modify")
	public String update(
			@RequestParam(name = "files", required=false)
			MultipartFile[] files,
			RedirectAttributes rAttr, BoardVO bvo, HttpServletRequest request) {
		log.info(">>> bvo"+bvo.toString());
		HttpSession ses = request.getSession();
		UserVO sesUser = (UserVO)ses.getAttribute("ses");
		UserVO user = udao.getUser(sesUser.getId());
//		UserVO user = udao.getUser(bvo.getWriter());
		
		log.info("files : "+files);
		
		List<FileVO> flist = null;
		if(files[0].getSize() > 0) {
			flist = fhd.uploadFiles(files);
		}else {
			log.info("file null");
		}
		BoardDTO bdto = new BoardDTO(bvo, flist);
//		int isOk = bsv.update(bvo, user);
		int isOk = bsv.modifyFile(bdto, user);
		log.info(">>> isOk "+(isOk>0 ? "ok":"fail"));
		rAttr.addAttribute("msg_modify", isOk>0 ? "1":"0");
		return "redirect:/board/list";
	}
	@GetMapping("/delete")
	public String delete(RedirectAttributes rAttr, @RequestParam("bno")int bno, HttpServletRequest request) {
		//DB상 update하기 isDel = "Y" => 삭제 처리
		HttpSession ses = request.getSession();
		UserVO sesUser = (UserVO)ses.getAttribute("ses"); //세션 객체
		log.info("sesUser"+sesUser.toString());
		UserVO user = udao.getUser(sesUser.getId());
		int isOk = bsv.delete(bno, user);
		log.info(">>> isOk "+(isOk>0 ? "ok":"fail"));
		rAttr.addAttribute("isOk", isOk);
		return "redirect:/board/list";
	}
	
	@DeleteMapping(value = "/file/{uuid}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> removeFile(@PathVariable("uuid")String uuid){
		log.info(">>> uuid : "+uuid);
		return bsv.removeFile(uuid) > 0?
				new ResponseEntity<String>("1", HttpStatus.OK)
				:new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	
}

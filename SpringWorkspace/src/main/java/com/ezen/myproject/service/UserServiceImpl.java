package com.ezen.myproject.service;

import javax.inject.Inject;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ezen.myproject.domain.UserVO;
import com.ezen.myproject.repository.UserDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
	
	@Inject
	private UserDAO udao;
	@Inject
	BCryptPasswordEncoder passwordencoder;

	@Override
	public int join(UserVO uvo) {
		log.info("회원가입 서비스 진입");
		log.info("uvo.getId()"+uvo.getId());
		UserVO tempUser = udao.getUser(uvo.getId());
		if(tempUser != null) {
			return 0;			
		}
		if(uvo.getId() == null || uvo.getId().length() == 0) {
			return 0;
		}
		if(uvo.getPw() == null || uvo.getPw().length() == 0) {
			return 0;
		}
		log.info("uvo.getPw() 나옴?" +uvo.getPw());
		String pw = uvo.getPw();
		String encodePw = passwordencoder.encode(pw);
		uvo.setPw(encodePw);
		int isOk = udao.join(uvo);
		log.info("isOk : "+isOk);
		return isOk;
	}

	@Override
	public UserVO login(String id, String pw) {
		log.info(">> 로그인 서비스 진입");
		UserVO user = udao.getUser(id);
		if(user == null) { return null; }

		if(passwordencoder.matches(pw, user.getPw())) {
			return user;
		}else {
			return null;			
		}
	}

}

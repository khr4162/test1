package com.ezen.myproject.service;

import com.ezen.myproject.domain.UserVO;

public interface UserService {

	int join(UserVO uvo);

	UserVO login(String id, String pw);

}

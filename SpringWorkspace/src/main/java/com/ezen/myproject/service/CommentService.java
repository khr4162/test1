package com.ezen.myproject.service;

import com.ezen.myproject.domain.CommentVO;

public interface CommentService {

	int edit(CommentVO cvo);

	int remove(int cno);

}

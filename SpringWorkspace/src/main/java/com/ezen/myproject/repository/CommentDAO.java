package com.ezen.myproject.repository;

import com.ezen.myproject.domain.CommentVO;

public interface CommentDAO {

	int update(CommentVO cvo);

	int remove(int cno);

}

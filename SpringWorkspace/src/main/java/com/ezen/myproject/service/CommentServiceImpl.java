package com.ezen.myproject.service;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.ezen.myproject.domain.CommentVO;
import com.ezen.myproject.repository.CommentDAO;

import lombok.extern.slf4j.Slf4j;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Inject
	private CommentDAO cdao;

	@Override
	public int edit(CommentVO cvo) {
		return cdao.update(cvo);
	}

	@Override
	public int remove(int cno) {
		return cdao.remove(cno);
	}
}

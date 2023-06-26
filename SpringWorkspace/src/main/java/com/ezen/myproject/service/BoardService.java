package com.ezen.myproject.service;

import java.util.List;

import com.ezen.myproject.domain.BoardDTO;
import com.ezen.myproject.domain.BoardVO;
import com.ezen.myproject.domain.PagingVO;
import com.ezen.myproject.domain.UserVO;

public interface BoardService {

	int register(BoardDTO bdto);

	List<BoardVO> getList(PagingVO pvo);

	int getTotalCount(PagingVO pvo);

	BoardDTO getDetailFile(int bno);

	int readCount(int bno);

	BoardVO getDetail(int bno);

	int update(BoardVO bvo, UserVO user);

	int modifyFile(BoardDTO bdto, UserVO user);

	int register1(BoardVO bvo);

	int delete(int bno, UserVO user);

	int removeFile(String uuid);

}

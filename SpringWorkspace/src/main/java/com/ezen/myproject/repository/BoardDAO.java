package com.ezen.myproject.repository;

import java.util.List;

import com.ezen.myproject.domain.BoardVO;
import com.ezen.myproject.domain.PagingVO;

public interface BoardDAO {

	int insert(BoardVO bvo);

	int selectBno();

	List<BoardVO> selectBoardListPaging(PagingVO pvo);

	int getTotalCount(PagingVO pvo);

	BoardVO getDetail(int bno);

	int readCount(int bno);

	int updateBoard(BoardVO bvo);

	int insert1(BoardVO bvo);

	int delete(int bno);

}

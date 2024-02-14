package com.ncs.spring02.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ncs.spring02.domain.BoardDTO;

import pageTest.Criteria;

public interface BoardService {
	
	//Board_Paging
	public List<BoardDTO> bPageList(Criteria cri);
	public int totalRowsCount(Criteria cri);

	// List
	public List<BoardDTO> selectList() ;

	// Detail
	public BoardDTO selectOne(int seq) ;

	// Insert
	public int insert(BoardDTO dto) ;
	
	//replyInsert
	public int rinsert(BoardDTO dto) ;

	// Update
	public int update(BoardDTO dto) ;

	// Delete
	public int delete(BoardDTO dto) ;
}

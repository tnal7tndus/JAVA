package com.ncs.spring02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncs.spring02.domain.BoardDTO;
import com.ncs.spring02.model.BoardDAO;

import mapperInterface.BoardMapper;
import pageTest.Criteria;
import pageTest.SearchCriteria;

@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
//	BoardDAO dao;
	//=> Mybatis 적용
	BoardMapper mapper;
	
	//Board Check_List
	@Override
	public List<BoardDTO> bCheckList(SearchCriteria cri){
		return mapper.bCheckList(cri);
	}
	@Override
	public int bCheckRowsCount(SearchCriteria cri) {
		return mapper.bCheckRowsCount(cri);
	}
	
	//Board Saerch_List
	@Override
	public List<BoardDTO> bSearchList(SearchCriteria cri){
		return mapper.bSearchList(cri);
	}
	@Override
	public int bSearchRowsCount(SearchCriteria cri) {
		return mapper.bSearchRowsCount(cri);
	}

	//** Board_Paging
	@Override
	public List<BoardDTO> bPageList(SearchCriteria cri) {
//		return mapper.bPageList(cri); //ver01
		return mapper.bSearchList(cri); //ver02
	}
	@Override
	public int totalRowsCount(SearchCriteria cri) {
//		return mapper.totalRowsCount(cri); //ver01
		return mapper.bSearchRowsCount(cri); //ver02
	}
		
	@Override
	public List<BoardDTO> selectList() {
		return mapper.selectList();
	}

	@Override
	public BoardDTO selectOne(int seq) {
		return mapper.selectOne(seq);
	}

	@Override
	public int insert(BoardDTO dto) {
		return mapper.insert(dto);
	}
	
	//** 답글등록
	//=> rinsert, stepUpdate
	@Override
	public int rinsert(BoardDTO dto) {
		if( mapper.rinsert(dto) > 0 ) {
			//stepUpdate
			System.out.println("** stepUpdate Count => "+mapper.stepUpdate(dto));
			return 1;
		}else return 0;
	}
	
	@Override
	public int update(BoardDTO dto) {
		return mapper.update(dto);
	}

	@Override
	public int delete(BoardDTO dto) {
		return mapper.delete(dto);
	}

}

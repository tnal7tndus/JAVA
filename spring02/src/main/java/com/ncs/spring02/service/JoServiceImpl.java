package com.ncs.spring02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncs.spring02.domain.JoDTO;
import com.ncs.spring02.domain.MemberDTO;
import com.ncs.spring02.model.JoDAO;

import mapperInterface.JoMapper;
import mapperInterface.MemberMapper;

@Service
public class JoServiceImpl implements JoService {
	
	//** 전역변수 정의
	@Autowired
//	JoDAO dao;
	JoMapper mapper;

	//**selectJoList
	public List<JoDTO> selectJoList(){
		return mapper.selectJoList();
	}
	
	//**selectJoDetail
	public JoDTO selectJoDetail(String jno) {
		return mapper.selectJoDetail(jno);
	}
	
	//**insert
	public int insert(JoDTO dto) {
		return mapper.insert(dto);
	}
	
	//**update
	public int update(JoDTO dto) {
		return mapper.update(dto);
	}
	
//	**delete
	public int delete(String jno) {
		return mapper.delete(jno);
	}
}

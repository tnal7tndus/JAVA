package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.JoDTO;


public interface JoService {
	
	//**selectJoList
	public List<JoDTO> selectJoList();
	
	//**selectJoDetail
	public JoDTO selectJoDetail(String jno);
	
	//**insert
	public int insert(JoDTO dto);
	
	//**update
	public int update(JoDTO dto);
	
//	**delete
	public int delete(String jno);
}

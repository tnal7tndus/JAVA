package com.ncs.spring02.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ncs.spring02.domain.JoDTO;
import com.ncs.spring02.model.JoDAO;

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

package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Jo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import lombok.RequiredArgsConstructor;

public interface JoService {
	
	//**selectJoList
	public List<Jo> selectJoList();
	
	//**selectJoDetail
	public Jo selectJoDetail(int jno);
	
	//**insert, update
	public Jo save(Jo entity);
	
	
	//**delete
	public void delete(int jno);
}

package com.example.demo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Item_ask;
import com.example.demo.module.PageRequest;
import com.example.demo.module.SearchRequest;

@Repository
public interface Item_askRepository {

	List<Item_ask> selectItemAskList(PageRequest pageRequest , SearchRequest searchRequest);
	

//	List<Item_ask> selectItemAskListStringWhereType(PageRequest pageRequest , SearchRequest searchRequest);
//	List<Item_ask> selectItemAskListIntegerWhereType(PageRequest pageRequest , SearchRequest searchRequest);
//	
}

package com.example.demo.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.Item_reviewDTO;
import com.example.demo.entity.Item_review;
import com.example.demo.module.PageRequest;
import com.example.demo.module.SearchRequest;

@Repository
public interface Item_reviewRepository {

	List<Item_review> selectItemRevieListStringWhereType(PageRequest pageRequest , SearchRequest searchRequest);
	List<Item_review> selectItemRevieListIntegerWhereType(PageRequest pageRequest , SearchRequest searchRequest);
	
	
	
	//** 상품리뷰 등록
	int insertItemReview(Item_reviewDTO dto);
	
}

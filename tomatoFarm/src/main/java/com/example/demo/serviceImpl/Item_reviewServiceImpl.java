package com.example.demo.serviceImpl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.domain.Item_reviewDTO;
import com.example.demo.entity.Item_review;
import com.example.demo.module.PageRequest;
import com.example.demo.module.SearchRequest;
import com.example.demo.repository.Item_reviewRepository;
import com.example.demo.service.Item_reviewService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
@Service
public class Item_reviewServiceImpl implements Item_reviewService{
	
	private final Item_reviewRepository item_reviewRepository;
	
	@Override
	//** 상품리뷰 조회
	public List<Item_review> selectItemRevieListStringWhereType(PageRequest pageRequest, SearchRequest searchRequest) {
		return item_reviewRepository.selectItemRevieListStringWhereType(pageRequest, searchRequest);
	}
	@Override
	//** 상품리뷰 조회
	public List<Item_review> selectItemRevieListIntegerWhereType(PageRequest pageRequest, SearchRequest searchRequest) {
		return item_reviewRepository.selectItemRevieListIntegerWhereType(pageRequest, searchRequest);
	}

	@Override
	//** 상품리뷰 등록
	public int insertItemReview(Item_reviewDTO dto) {
		
		return item_reviewRepository.insertItemReview(dto);
	}
	
	
	
	
	
}
	

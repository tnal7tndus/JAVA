package com.example.demo.repostoryImpl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.entity.Item_ask;
import com.example.demo.module.PageRequest;
import com.example.demo.module.SearchRequest;
import com.example.demo.repository.Item_askRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.example.demo.entity.QItem_ask.item_ask;

import lombok.AllArgsConstructor;


@Repository
@AllArgsConstructor
public class Item_askRepositoryImpl implements Item_askRepository{
	
	private final JPAQueryFactory jPAQueryFactory;

	@Override
	// ** 상품 리뷰 조회
	public List<Item_ask> selectItemAskList(PageRequest pageRequest, SearchRequest searchRequest) {
		return jPAQueryFactory.selectFrom(item_ask)
				.where(item_ask.item_code.eq(Integer.parseInt(searchRequest.getKeyword())))
				.orderBy(item_ask.regdate.desc())
				.offset(pageRequest.getStartNum()).limit(pageRequest.getEndNum())
				.fetch();
	}
	
//	@Override
//	// ** 상품 리뷰 조회
//	public List<Item_ask> selectItemAskListStringWhereType(PageRequest pageRequest, SearchRequest searchRequest) {
//		return jPAQueryFactory
//				.selectFrom(item_ask)
//				.where(Expressions.stringPath(searchRequest.getColumn()).contains(searchRequest.getKeyword()))
////				.orderBy(item_ask.regdate.desc())
//				.limit(pageRequest.getEndNum()).offset(pageRequest.getStartNum())
//				.fetch();
//	}
//	
//	@Override
//	public List<Item_ask> selectItemAskListIntegerWhereType(PageRequest pageRequest, SearchRequest searchRequest) {
//		return jPAQueryFactory
//				.selectFrom(item_ask)
//				.where(Expressions.numberPath(Integer.class,searchRequest.getColumn()).stringValue().eq(searchRequest.getKeyword()))
////				.orderBy(item_ask.regdate.desc())
//				.limit(pageRequest.getEndNum()).offset(pageRequest.getStartNum())
//				.fetch();
//	}
//	
	
}

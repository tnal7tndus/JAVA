package com.example.demo.repostoryImpl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.demo.domain.ItemDTO;
import com.example.demo.domain.SortDTO;
import com.example.demo.entity.Item;
import com.example.demo.entity.QItem;
import com.example.demo.module.PageRequest;
import com.example.demo.module.SearchRequest;
import com.example.demo.repository.ItemRepository;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.core.types.dsl.PathBuilderFactory;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.jpa.impl.JPAQueryFactory;

import static com.example.demo.entity.QItem.item;
import static com.example.demo.entity.Qitem_event.item_event;

import lombok.AllArgsConstructor;

@Repository
@AllArgsConstructor
public class ItemRepositoryImpl implements ItemRepository {

	private final JPAQueryFactory jPAQueryFactory;
	private final EntityManager entityManager;

	// queryDSL 동적 정렬을 위해 OrderSpecifier객체를 이용한 동적 정렬
	public OrderSpecifier<?> getSortType(SearchRequest searchRequest) {
		if (searchRequest.getSortType() != null) {
			switch (searchRequest.getSortType()) {
			case "priceD":
				return new OrderSpecifier<>(Order.DESC, QItem.item.price);
			case "priceA":
				return new OrderSpecifier<>(Order.ASC, QItem.item.price);
			case "salesA":
				return new OrderSpecifier<>(Order.ASC, QItem.item.sales);
			case "codeD":
				return new OrderSpecifier<>(Order.DESC, QItem.item.code);
			}
		}
		return new OrderSpecifier<>(Order.DESC, QItem.item.sales);
	}

	@Override
	public List<ItemDTO> selectItemListStringWhereType(PageRequest pageRequest, SearchRequest searchRequest) {
		return jPAQueryFactory
				.select(Projections.bean(ItemDTO.class, item.code, item.brand, item.name, item.delivery, item.price, item.storage,
						item.weight, item.packing, item.sales, item.stock, item.views, item.likes, item.event_code,
						item_event.discount, item_event.name.as("event_name")))
				.from(item).join(item_event).on(item.event_code.eq(item_event.code))
				.where(Expressions.stringPath(searchRequest.getColumn()).contains(searchRequest.getKeyword()))
				.limit(pageRequest.getEndNum()).offset(pageRequest.getStartNum()).orderBy(getSortType(searchRequest))
				.fetch();
	}

	@Override
	public List<ItemDTO> selectItemListIntegerWhereType(PageRequest pageRequest, SearchRequest searchRequest) {
		return jPAQueryFactory
				.select(Projections.bean(ItemDTO.class, item.code, item.brand, item.name, item.delivery, item.price, item.storage,
						item.weight, item.packing, item.sales, item.stock, item.views, item.likes, item.event_code,
						item_event.discount, item_event.name.as("event_name")))
				.from(item).leftJoin(item_event).on(item.event_code.eq(item_event.code))
				.where(Expressions.numberPath(Integer.class, searchRequest.getColumn()).stringValue()
						.eq(searchRequest.getKeyword()))
				.limit(pageRequest.getEndNum()).offset(pageRequest.getStartNum()).orderBy(getSortType(searchRequest))
				.fetch();
	}

	@Override
	public List<ItemDTO> selectItemListStringWhereTypeNotNull(PageRequest pageRequest, SearchRequest searchRequest) {
		return jPAQueryFactory
				.select(Projections.bean(ItemDTO.class, item.code, item.brand, item.name, item.delivery, item.price, item.storage,
						item.weight, item.packing, item.sales, item.stock, item.views, item.likes, item.event_code,
						item_event.discount, item_event.name.as("event_name")))
				.from(item).join(item_event).on(item.event_code.eq(item_event.code))
				.where(Expressions.stringPath(searchRequest.getColumn()).isNotNull()).limit(pageRequest.getEndNum())
				.offset(pageRequest.getStartNum()).orderBy(getSortType(searchRequest)).fetch();
	}

	@Override
	public ItemDTO selectItemIntegerWhereType(SearchRequest searchRequest) {
		return jPAQueryFactory
				.select(Projections.bean(ItemDTO.class, item.code, item.brand, item.name, item.delivery, item.price, item.storage,
						item.weight, item.packing, item.sales, item.stock, item.views, item.likes, item.event_code,
						item_event.discount, item_event.name.as("event_name")))
				.from(item).leftJoin(item_event).on(item.event_code.eq(item_event.code))
				.where(Expressions.numberPath(Integer.class, searchRequest.getColumn()).stringValue()
						.eq(searchRequest.getKeyword()))
				.fetchOne();
	}

	@Override
	// ** 브랜드 상품 조회
	public List<ItemDTO> selectItemWherebrand(PageRequest pageRequest, SearchRequest searchRequest) {
		return jPAQueryFactory
				.select(Projections.bean(ItemDTO.class, item.code, item.brand, item.name, item.delivery, item.price, item.storage,
						item.sales, item.stock, item.views, item.likes, item.event_code, item_event.discount,
						item_event.name.as("event_name")))
				.from(item).leftJoin(item_event).on(item.event_code.eq(item_event.code))
				.where(item.brand.eq(searchRequest.getKeyword())).orderBy(item.sales.desc())
				.offset(pageRequest.getStartNum()).limit(pageRequest.getEndNum()).fetch();
	}

	@Override
	// ** 키워드 상품 페이징 조회
	public List<ItemDTO> selectItemWhereSearchType(PageRequest pageRequest, SearchRequest searchRequest) {
		return jPAQueryFactory
				.select(Projections.bean(ItemDTO.class, item.code, item.brand, item.name, item.delivery, item.price,
						item.sales, item.stock, item.views, item.likes, item.event_code, item_event.discount,
						item_event.name.as("event_name")))
				.from(item).leftJoin(item_event).on(item.event_code.eq(item_event.code))
				.where(item.sort2.contains(searchRequest.getKeyword())
						.or(item.sort3.contains(searchRequest.getKeyword()))
						.or(item.brand.contains(searchRequest.getKeyword()))
						.or(item.name.contains(searchRequest.getKeyword())))
				.orderBy(getSortType(searchRequest))
				.limit(pageRequest.getEndNum()).offset(pageRequest.getStartNum())
				.fetch();
	}

	@Override
	public List<SortDTO> selectSortWhereKeyword(SearchRequest searchRequest) {
		List<SortDTO> result = jPAQueryFactory
				.select(Projections.bean(SortDTO.class, item.sort1, item.sort2,item.sort2.count().as("count")))
				.from(item)
				.where(item.sort2.contains(searchRequest.getKeyword())
						.or(item.sort3.contains(searchRequest.getKeyword()))
						.or(item.brand.contains(searchRequest.getKeyword()))
						.or(item.name.contains(searchRequest.getKeyword()))
						.and(item.sort1.ne("밀키트")))
				.groupBy(item.sort1, item.sort2)
				.fetch();

		result.addAll(jPAQueryFactory
				.select(Projections.bean(SortDTO.class, item.sort1, item.brand.as("sort2"), item.sort2.count().as("count")))
		        .from(item)
		        .where(item.sort1.eq("밀키트")
		                .and(item.sort2.contains(searchRequest.getKeyword())
		                        .or(item.sort3.contains(searchRequest.getKeyword()))
		                        .or(item.brand.contains(searchRequest.getKeyword()))
		                        .or(item.name.contains(searchRequest.getKeyword()))))
		        .groupBy(item.brand)
		        .fetch());

		return result;
	}

	@Override
	// ** 키워드 상품 분류 조회
	public List<SortDTO> selectSortList() {
		List<SortDTO> result = jPAQueryFactory
				.select(Projections.bean(SortDTO.class, item.sort1, item.brand.as("sort2")))
					.where(item.sort1.eq("밀키트"))
					.from(item)
					.groupBy(item.sort1, item.brand)
					.orderBy(item.brand.asc())
					.fetch();
		result.addAll(jPAQueryFactory
				.select(Projections.bean(SortDTO.class, item.sort1, item.sort2))
					.from(item)
					.where(item.sort1.ne("밀키트"))
					.groupBy(item.sort1, item.sort2)
					.orderBy(item.sort2.asc())
					.fetch());
		return result;
	}

	/* 🎃🎃🎃🎃🎃🎃 검수 전 🎃🎃🎃🎃🎃🎃 */

	public int batchInsert(List<Item> entity) {
		// QueryDSL을 사용하여 batch insert 쿼리 작성 및 실행
		return (int) jPAQueryFactory.insert(item).values(entity).execute();
	}

	@Override
	public List<ItemDTO> selectAll() {
		return jPAQueryFactory
				.select(Projections.bean(ItemDTO.class, item.code, item.sort1, item.sort2, item.sort3, item.brand,
						item.name, item.weight, item.storage, item.packing, item.delivery, item.price, item.vat,
						item.origin, item.stock, item.admin))
				.from(item).offset(0).limit(500).orderBy(item.sales.desc()).fetch();
	}

	@Override
	@Transactional
	public void insertItem(Item entity) {
		entityManager.persist(entity);
	}

}

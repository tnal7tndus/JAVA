package com.example.demo.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "item")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {

	@Id
	private Integer code; // 제품코드
	private String sort1; // 대분류 (밀키트,식재료)
	private String sort2; // 중분류 (브랜드, 식품분류 ex) 야채, 육류...)
	private String sort3;	// 소분류 (상세 분류 ex) 양상추, 토마토...)
	private String brand; // 브랜드
	private String name; // 제품 명
	private String weight; // g, k g 량
	private String storage; // 저장방식 e x) 냉동,냉장
	private String packing; // 포장방식
	private Integer delivery; // 배송비
	private Integer price; // 가격
	private Integer vat; // 과세여부
	private String origin; // 원산지
	private Integer sales; // 판매 수량
	private Integer stock; // 재고 수량
	private Integer views; // 조회수
	private Integer likes; // 좋아요수
	private Integer event_code; // 이벤트명
	private String admin; // 등록자

}

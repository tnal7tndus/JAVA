package com.example.demo.module;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//** JPA Paging & Sort
		// => https://bnzn2426.tistory.com/135

		// ** PageList 요청 처리 DTO
		// => 재사용 가능 구조: 다양한 Table에 적용가능
		// => JPA 에서 사용하는 Pageable Type 객체 생성을 목표로함.

@Builder
@AllArgsConstructor
@Getter
@Setter
@ToString
public class PageRequest {
	
	private int page; // 출력페이지
	private int size; // 1page 당 출력 row 갯수
	private int startNum; // 페이지의 나타날 데이터 중 시작데이터 번호
	private int endNum; // 페이지의 나타날 데이터 중 끝데이터 번호
	private int currPage;
	
	public void setStartEndNum(int currPage) {
		if(this.startNum < 1) {
			this.startNum = 1;
		}
		this.startNum = ((this.currPage-1) * (this.size));
		this.endNum = (startNum+1) * this.size;
	}
	
	public PageRequest(int page, int size){
		this.page = page;
		this.size = size;
		
		this.startNum = (page-1)*size;
		this.endNum = page*size;
	}
	
	public PageRequest() {
		this.page = 1;
		this.size = 500;
		this.startNum = (page-1)*size;
		this.endNum = page*size;
	}
}

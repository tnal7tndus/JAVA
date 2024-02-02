package com.ncs.spring02.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor // 생성자 자동 생성
@NoArgsConstructor // 기본 생성자 자동 생성
@Data
public class JoDTO{
	//** private으로 멤버변수 정의
	private int jno;
	private String jname;
	private String captain;
	private String project;
	private String slogan;
//	private String uploadfile;


	//** 1) 생성자
	//=> default 생성자, 모든 값을 초기화하는 생성자
	
	//** 2) setter/getter
	//** 3) toString
	

		

}

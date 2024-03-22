package com.example.demo.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "item_ask")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item_ask {

	@Id
	private int seq; //순번
	private int item_code; //제품코드
	private String writer;	//글쓴이
	private String title;	//제목
	private String contents;	//내용
	private String password; 	//비밀번호
	private String reply;	//답변
	private Date regdate;	//날짜
	private int privacy; 	//비공개설정

}

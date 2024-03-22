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
@Table(name = "item_review")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item_review {

	@Id
	private int seq; //순번
	private int item_code; //제품코드
	private String writer;	//글쓴이
	private String title;	//제목
	private String contents;	//내용
	private String score; 	//조회수
	private Date regdate;	//날짜
	private int likes;	//공감수
	private String image1; 
	private String image2; 
	private String image3; 

}

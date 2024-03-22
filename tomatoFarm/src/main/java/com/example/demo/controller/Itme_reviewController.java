package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.Item_reviewDTO;
import com.example.demo.entity.Item_review;
import com.example.demo.module.PageRequest;
import com.example.demo.module.SearchRequest;
import com.example.demo.service.Item_reviewService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@CrossOrigin(origins = "http://localhost:3000")
@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping(value = "/itemreview")
public class Itme_reviewController {
	private final Item_reviewService item_reviewService;

	@GetMapping("/select")
	public ResponseEntity<?> selectItem_reviewList(PageRequest pageRequest, SearchRequest searchRequest) {
		ResponseEntity<?> result = null;

		List<Item_review> list = item_reviewService.selectItemRevieListIntegerWhereType(pageRequest, searchRequest);
		result = ResponseEntity.status(HttpStatus.OK).body(list);
		return result;
	}

	@PostMapping("/iteminsert")
	public ResponseEntity<?> iteminsert(Item_reviewDTO dto) {
		ResponseEntity<?> result = null;

		String writer = dto.getWriter();

		if (item_reviewService.insertItemReview(dto) > 0) {
			result = ResponseEntity.status(HttpStatus.OK).body("review_insert_success");
		} else {
			result = ResponseEntity.status(HttpStatus.OK).body("review_insert_failed");
		}
		return result;
	}

}

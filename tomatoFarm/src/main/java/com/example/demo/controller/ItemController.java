package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.domain.ItemDTO;
import com.example.demo.domain.SortDTO;
import com.example.demo.module.PageRequest;
import com.example.demo.module.SearchRequest;
import com.example.demo.service.ItemService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@CrossOrigin(origins = "http://localhost:3000")
@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping(value = "/item")
public class ItemController {
	private final ItemService itemService;

	@GetMapping("/selectnotnull")
	public ResponseEntity<?> selectItemWhereEvent(SearchRequest searchRequest) {
		ResponseEntity<?> result = null;
		PageRequest pageRequest = new PageRequest(1, 11);
		List<ItemDTO> list = itemService.selectItemListStringWhereTypeNotNull(pageRequest, searchRequest);
		result = ResponseEntity.status(HttpStatus.OK).body(list);
		return result;
	}

	@GetMapping("/detailn")
	public ResponseEntity<?> selectItemWhereType(SearchRequest searchRequest) {
		ResponseEntity<?> result = null;
		ItemDTO dto = itemService.selectItemIntegerWhereType(searchRequest);
		result = ResponseEntity.status(HttpStatus.OK).body(dto);
		return result;
	}

	@GetMapping("/branditem/{keyword}")
	public ResponseEntity<?> selectItemWherebrand(@PathVariable("keyword") String keyword) {
		ResponseEntity<?> result = null;
		PageRequest pageRequest = new PageRequest(1, 6);
		SearchRequest searchRequest = new SearchRequest(keyword);

		List<ItemDTO> list = itemService.selectItemWherebrand(pageRequest, searchRequest);
		result = ResponseEntity.status(HttpStatus.OK).body(list);
		return result;
	}

//페이징 + 정렬 기능 되는 search
	@GetMapping("/search")
	public ResponseEntity<?> selectItemWhereSearchType(PageRequest pageRequest, SearchRequest searchRequest) {
		ResponseEntity<?> result = null;
		System.out.println(pageRequest);
		List<ItemDTO> list = itemService.selectItemWhereSearchType(pageRequest, searchRequest);
		result = ResponseEntity.status(HttpStatus.OK).body(list);
		return result;
	}

	@GetMapping("/searchsort")
	public ResponseEntity<?> selectSortWhereKeyword(SearchRequest searchRequest) {
		ResponseEntity<?> result = null;
		List<SortDTO> list = itemService.selectSortWhereKeyword(searchRequest);
		System.out.println(searchRequest);
		result = ResponseEntity.status(HttpStatus.OK).body(list);
		System.out.println(list);
		return result;
	}

	@GetMapping("/sort")
	public ResponseEntity<?> selectSortList() {
		ResponseEntity<?> result = null;
		List<SortDTO> list = itemService.selectSortList();
		System.out.println(list);
		if (list != null && list.size() > 0) {
			result = ResponseEntity.status(HttpStatus.OK).body(list);
			log.info("sort check");
		} else {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("출력자료 없음");
			log.info("sort check");
		}
		return result;
	}

	/* 🎃🎃🎃🎃🎃🎃 검수 전 🎃🎃🎃🎃🎃🎃 */

	@GetMapping("/allitem")
	public ResponseEntity<?> selectAll() {
		ResponseEntity<?> result = null;
		List<ItemDTO> itemList = itemService.selectAll();

		if (itemList != null && itemList.size() > 0) {
			result = ResponseEntity.status(HttpStatus.OK).body(itemList);
			log.info("출력한다");
		} else {
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body("출력자료 없음");
			log.info("데이터 못찾겠다");
		}

		return result;
	}

	@PostMapping(value = "/insert")
//    @GetMapping("/insert")
	public ResponseEntity<?> insertItem(ItemDTO entity) {
		System.out.println("getCode => " + entity.getCode());
		System.out.println("getAdmin => " + entity.getAdmin());
		System.out.println("getSort1 => " + entity.getSort1());
		System.out.println("getLikes => " + entity.getLikes());
		ResponseEntity<?> result = null;
//        itemService.insertItem(entity);
		result = ResponseEntity.status(HttpStatus.OK).body("insert성공");
		return result;
	}

	// 테스트 메서드야 지워도 돼
	@PostMapping("/test")
	public void test(ItemDTO entity) {
		System.out.println("*************************************");
		System.out.println("getCode =>" + entity.getCode());
		System.out.println("getSort1 =>" + entity.getSort1());
		System.out.println("getSort1 =>" + entity.getSort1());
		System.out.println("getSort3 =>" + entity.getSort3());
		System.out.println("getName =>" + entity.getName());
		System.out.println("*************************************");
	}

}

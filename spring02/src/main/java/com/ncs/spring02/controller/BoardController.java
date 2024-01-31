package com.ncs.spring02.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ncs.spring02.domain.BoardDTO;
import com.ncs.spring02.service.BoardServiceImpl;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	//**Reply Insert
	@GetMapping("/replyInsert")
	public void replyInsert(BoardDTO dto) {
		//=> 답글 처리를 위해 부모글의 root, step, indent를 인자로 전달 받으면,
		//	 이 인자에 담겨진 값은 requestScope와 동일
		//=> 그러므로 response 전송 전까지는 서버(Jsp)에서 사용가능
		//	 단, 객체명의 첫문자를 소문자로 접근 가능(${boardDTO. ~~} )
	}
	
	//=> 메서드명과 요청명이 위의 메서드와 동일하지만,
	//	 Post 요청이고 인자가 다르기 때문에 허용됨.
	@PostMapping("/replyInsert")
	public String replyInsert(Model model, BoardDTO dto, RedirectAttributes rttr) {
		//** 답글등록
		//=> 성공시: boardList에서 입력완료 확인
		//=> 실패시: replyInsert 재입력유도
		String uri = "redirect:boardList";
		
		//=> dto 값 확인
		// -> id, title, content : 사용가능
		// -> 부모글의 root : 사용가능
		// -> 부모글의 step, indent : 1씩 증가
		//=> Sql 처리
		//	-> replyInsert, step의 Update
		dto.setStep(dto.getStep()+1);
		dto.setIndent(dto.getIndent()+1);
		if(service.rinsert(dto)>0) {
			rttr.addFlashAttribute("message"," ~~ 답글 등록 성공 ~~ ");
		}else {
			uri="board/replyInsert";
			model.addAttribute("message"," !!! 답글 등록 실패 !!!");
		}
		return uri;
	}//replyInsert

	@Autowired
	private BoardServiceImpl service;

	@GetMapping("/boardList")
	public void selectListForm(Model model) {
		model.addAttribute("apple", service.selectList());
	}//replyInsert

	// ** Board Detail
	// => 글요청 처리중, 글을 읽기 전
	// => 조회수 증가
	// ->loginId와 board의 id가 다른 경우
//	@GetMapping("/boardDetail")
//	public void selectDetailForm(Model model, @RequestParam("jCode")int seq) {
//		model.addAttribute("apple", service.selectOne(seq));
//	}
	@GetMapping("/boardDetail")
	public String selectDetailForm(HttpSession session, Model model, @RequestParam("jCode") String jCode,
			@RequestParam("seq") int seq) {
		String uri = "board/boardDetail";

		// => 조회수 증가
		// -> selectOne의 결과를 보관
		// -> update 요청이 아니고, 로그인 id가 다른경우
		BoardDTO dto = service.selectOne(seq);

		if ("U".equals(jCode)) {
			uri = "board/boardUpdate";
			
		} else if (!dto.getId().equals((String) session.getAttribute("loginID"))) {
			// => 조회수 증가 조건 만족
			dto.setCnt(dto.getCnt() + 1);
			service.update(dto);
		}
		model.addAttribute("apple", dto);
		return uri;
	}//boardDetail

	@GetMapping("/boardInsert")
	public void selectInsertForm(Model model, BoardDTO dto) {
		model.addAttribute("apple", service.insert(dto));
	}//boardDetail

	@GetMapping("/insert")
	public String insert(Model model, BoardDTO dto) {
		String uri = "board/boardList";
		if (service.insert(dto) > 0) {
			model.addAttribute("apple", service.selectList());
			model.addAttribute("message", "새로운 게시글 등록 완료!");

		} else {
			uri = "board/boardInsert";
			model.addAttribute("message", "게시글 등록 실패! 다시 입력하세요");
		}
		return uri;
	}//boardInsert

	@GetMapping("/boardUpdate")
	public void selectUpdateForm(Model model, BoardDTO dto) {
		model.addAttribute("apple", service.selectOne(dto.getSeq()));
	}

	@PostMapping("/update")
	public String update(Model model, BoardDTO dto) {
		String uri = "board/boardDetail";
		if (service.update(dto) > 0) {
			model.addAttribute("message", "게시글 수정 완료");
		} else {
			uri = "board/boardList";
			model.addAttribute("message", "게시글 수정 실패! 다시 입력하세요!");
		}
		return uri;
	}//boardUpdate

	@GetMapping("/delete")
	public String delete(BoardDTO dto, Model model, RedirectAttributes rttr, @RequestParam("jCode") int seq) {
		String uri = "redirect:/";
		if (service.delete(dto) > 0) {
			rttr.addFlashAttribute("message", "삭제 완료");
		} else {
			uri = "board/boardDetail";
			model.addAttribute("message", "삭제 실패! 다시 누르세요!");
		}
		return uri;
	}//delete
}//clss

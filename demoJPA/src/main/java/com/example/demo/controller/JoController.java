package com.example.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Jo;
import com.example.demo.service.JoService;
import com.example.demo.service.MemberService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Controller
@RequestMapping(value = "/jo")
@AllArgsConstructor
public class JoController {
	JoService service;  // =new JoService();
	MemberService mservice;
	
	@GetMapping("/joList")
	public void joList(Model model, Jo entity) {
		model.addAttribute("apple", service.selectJoList());
	}
	
	@GetMapping("/joDetail")
	public String datail(HttpServletRequest rqeust,
			Model model, Jo entity, @RequestParam("jCode") String jCode) {
		String uri = "jo/joDetail";
		model.addAttribute("apple",service.selectJoDetail(entity.getJno()));
	//** 수정요청시에 수정폼으로
			if("U".equals(jCode))
				uri = "jo/joUpdate";
			
			//** 조원목록 출력하기 (detail 출력시에만)
			//=> MemberService 실행
			//	-> findByJno(int Jno) 메서드 추가
			//	실행결과는 apple로
			if("D".equals(jCode))
				model.addAttribute("banana",mservice.findByJno(entity.getJno()));
				return uri;
	}
	
	@GetMapping("/joInsert")
	public void joinForm() {
	}//loginForm
	
	//**insert
	@GetMapping("/insert")
	public String insert(Model model, Jo entity) {
		
		String uri = "redirect:joList";
		
		try {
			log.info("** Jo Insert 성공 => \n"+service.save(entity));
			model.addAttribute("message", "조 등록 성공!!");
		} catch (Exception e) {
			log.info("** Jo Insert Exception => \n"+e.toString());
			uri="jo/joinsert";
			model.addAttribute("message", "조 등록 실패!! 다시 등록하세요!");
		}
		return uri;
	}
	
	@GetMapping("/joUpdate" )
	public void joUpdate(Model model, @RequestParam("jno") int jno) {
		model.addAttribute("apple",service.selectJoDetail(jno));
	}//loginForm
	
	//**update
	@GetMapping("/Update")
	public String update(Model model, Jo entity) {
		String uri = "jo/joDetail";
		model.addAttribute("apple", entity);
		
		try {
			log.info("** Jo Update 성공 => \n"+service.save(entity));
			model.addAttribute("message", "조 정보 수정 성공했습니다");
		} catch (Exception e) {
			log.info("** Jo Update Exception => \n"+e.toString());
			uri="jo/joUpdate";
			model.addAttribute("message", "정보 수정 실패! 다시 입력하세요");
		}
		return uri;
	}
	
	//**delete
	@GetMapping("/joDelete")
	public String delete(int jno,RedirectAttributes rttr) {
		String uri = "redirect:joList";
		try {
			service.delete(jno);
			log.info("** Jo Delete 성공 => \n"+jno);
			rttr.addFlashAttribute("message", "삭제 완료");
		} catch (Exception e) {
			log.info("** Jo Delete Exception => \n"+e.toString());
			rttr.addAttribute("message","삭제 실패! 다시 누르세요");
		}
		return uri;
	}//delete
}//JoController

package com.ncs.spring02.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ncs.spring02.domain.JoDTO;
import com.ncs.spring02.service.JoService;
import com.ncs.spring02.service.MemberService;


@Controller
@RequestMapping(value = "/jo")
//@AllArgsConstructor //모든 멤버변수를 초기화하는 생성자 자동 추가 & 사용
					//그러므로 아래의 @Autowired는 생략 가능
public class JoController {
	@Autowired(required = false)
	JoService service;  // =new JoService();
	@Autowired
	MemberService mservice;
	String uri;
	
	@RequestMapping(value = "/joInsert" ,method=RequestMethod.GET)
	public void joinForm(Model model) {
	}//loginForm
	
	@RequestMapping(value = "/joUpdate" ,method=RequestMethod.GET)
	public void joUpdate(Model model, @RequestParam("jno") String jno) {
		model.addAttribute("apple",service.selectJoDetail(jno));
	}//loginForm
	
	
	@RequestMapping(value = "/joList", method = RequestMethod.GET)
	public void joList(Model model) {
		model.addAttribute("apple", service.selectJoList());
	}
	
	
	@RequestMapping(value = "/joDetail", method = RequestMethod.GET)
	public void joDetail( Model model, @RequestParam("jno")String jno) {
		model.addAttribute("apple", service.selectJoDetail(jno));
		model.addAttribute("banana", mservice.selectJoList(jno));
//	String uri = "jo/joDetail";
//	model.addAllAttributes("apple", service.selectOne(dto));
//	if("U".equals(jCode))
//		uri="jo/joUpdate";
//	if(("D".equals(jCode)))
//		model.addAllAttributes("banana", mservice.selectJoList(dto.getJno));
//		return uri;
//	
	}
	
	
	
	
	//**insert
	@RequestMapping(value = "/Insert", method = RequestMethod.GET)
	public String insert(Model model, JoDTO dto) {
		
		uri = "jo/joList";
				
		if(service.insert(dto) > 0) {
			model.addAttribute("apple", service.selectJoList());
			model.addAttribute("message", "조 등록 성공!!");
		}else {
			uri="jo/joInsert";
			model.addAttribute("message", "조 등록 실패!! 다시 등록하세요!");
		}
		return uri;
	}
	
	//**update
	@RequestMapping(value = "/Update", method = RequestMethod.GET)
	public String update(Model model, JoDTO dto) {
		uri = "jo/joDetail";
		model.addAttribute("apple", dto);
		
		if(service.update(dto)>0) {
			model.addAttribute("message", "조 정보 수정 성공했습니다");
		}else {
			uri="jo/joUpdate";
			model.addAttribute("message", "정보 수정 실패! 다시 입력하세요");
		}
		return uri;
	}
	
	//**delete
	@RequestMapping(value = "/joDelete", method = RequestMethod.GET)
	public String delete(Model model, RedirectAttributes rttr, @RequestParam("jno") String jno ) {
		uri = "redirect:/";
		if(service.delete(jno)>0) {
			rttr.addFlashAttribute("message", "삭제 완료");
		}else {
			uri="jo/joDetail";
			model.addAttribute("message","삭제 실패! 다시 누르세요");
			model.addAttribute("apple",service.selectJoDetail(jno));
		}
		return uri;
	}//delete
}//JoController

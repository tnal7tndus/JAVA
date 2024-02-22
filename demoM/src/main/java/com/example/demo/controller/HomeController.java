package com.example.demo.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/home")
	//@GetMapping(value={"/", "/home"})
    // => void : 요청명.jsp를 viewName으로 처리함 (home.jsp)
    //           그러므로 "/" 요청은 .jsp를 viewName으로 찾게됨(제외) 
    // => Boot의 매핑메서드에서 "/" 요청은 적용안됨(무시됨) 
    //    WebMvcConfig의 addViewControllers 메서드로 해결
	public void home(Locale locale, Model model) {
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		
	}//home
	
	@GetMapping("/axtestform")
	public String axTestForm() {
		return "axTest/axTestForm";
	}
	
		
		
		
		
}//class

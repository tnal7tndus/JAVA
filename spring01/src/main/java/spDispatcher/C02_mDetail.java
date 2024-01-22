package spDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import service.MemberService;

public class C02_mDetail implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		//Member Detail
		MemberService service = new MemberService();
		ModelAndView mv = new ModelAndView();
		mv.addObject("apple", service.selectOne("sumiii"));
		mv.setViewName("member/memberDetail");
		return mv;
	}
}

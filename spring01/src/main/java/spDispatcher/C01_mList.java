package spDispatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import myDispatcher.MyController;
import service.MemberService;

public class C01_mList implements Controller {

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) {
		//Member List
		MemberService service = new MemberService();
		ModelAndView mv = new ModelAndView();
		mv.addObject("banana", service.selectList());
		mv.setViewName("member/memberList");
		return mv;
	}
}

package myDispatcher;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ViewResolver;

//*** Spring MVC2_ver01
//=> MyDispatcherServlet (FrontController 역할)
// 	 HandlerMapping, ViewResolver를 활용해서
// 	 요청분석, Service, View를 처리

//=> Url Mapping은 web.xml에서 처리
public class MyDispatcher extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	// ** 전역변수 정의
	private MyHandlerMapping hmappings;
	private MyViewResolver vresolver;
	
	// ** 멤버변수 초기화: 생성자에서
    public MyDispatcher() {
        super();
        hmappings = MyHandlerMapping.getInstance();
        vresolver = new MyViewResolver();
        vresolver.setPrefix("/WEB-INF/views/");
        vresolver.setSuffix(".jsp");
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1) 요청 분석
		// => url 분석 후 요청명 확인
		// => 한글처리
		request.setCharacterEncoding("UTF-8");
		String uri = request.getRequestURI();
		uri=uri.substring(uri.lastIndexOf("/"));
	
		// 2) Service 실행
		// => MyHandlerMapping에 요청, 해당 서비스컨트롤러의 인스턴스를 제공 받음
		// => 해당 서비스 실행
		MyController controller = hmappings.getController(uri);
		System.out.println("** uri = >"+uri);
		if(controller!=null) {
			
			uri=controller.handleRequest(request,response);
		}else {
			uri="home";
			request.setAttribute("message", "~~ 없는 요청 입니다");
		}
		// 3) View 처리
		uri=vresolver.getViewName(uri); //최종 viewName 완성
		request.getRequestDispatcher(uri).forward(request, response);
	}//doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}//doPost

}//class

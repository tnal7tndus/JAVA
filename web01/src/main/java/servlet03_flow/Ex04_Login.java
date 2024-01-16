package servlet03_flow;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import mvcTest.StudentDTO;
import mvcTest.StudentService;

@WebServlet("/login")
public class Ex04_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Ex04_Login() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청분석
		// => 한글, request의 Parameter 처리
		request.setCharacterEncoding("UTF-8");
		String sno=request.getParameter("sno");
		String name=request.getParameter("name");
		System.out.println(Integer.parseInt(sno));
		String uri = "home.jsp";
		
		//선생님답
//		int sno=0;
//		if(request.getParameter("sno")!=null && request.getParameter("sno".length()>0)) {
//			sno= Integer.parseInt(request.getParameter("sno"));
//		}
//		String name=request.getParameter("name");
//		String uri = "home.jsp";
	
		
		// 2. Service 처리
		// => ~Service, ~DTO의 인스턴스
		// => Service의 selectOne : sno 확인
		//    확인 결과 성공이면 name 확인
		// => 성공 : index.html
		// => 실패 : ~LoginForm.jsp (재로그인 유도)
		
		StudentService sc = new StudentService();
		StudentDTO dto = sc.selectOne(Integer.parseInt(sno));
		if(name.equals(dto.getName())) {
			//성공했을때
			uri = "home.jsp";
			HttpSession session = request.getSession();
			session.setAttribute("loginName", dto.getName());
//			session.setAttribute("StudentDTO", dto);
			System.out.println("** 로그인성공 **");
			System.out.println("** 로그인 Student =>" + dto);
		}else {
			//실패했을때
			request.setAttribute("message", "로그인 실패 ! 다시 로그인 하세요 ~~ ");
			uri = "servletTestForm/flowEx04_LoginForm.jsp";
		}
		
		
		// 3. View (Response) : Forward
		request.getRequestDispatcher(uri).forward(request, response);
		
		//선생님 답안
//		StudentService sc = new StudentService();
//		StudentDTO dto = service.selecOne(sno);
//		if(dto!=null && dto.getName().equals(name)) {
//			request.getSession().setAttribute("loginName", name);
//			request.getSession().setAttribute("loginID", sno);
//			System.out.println("** 로그인성공 **");
//			System.out.println("** 로그인 Student =>" + dto);
//			response.sendRedirect(uri);
//		}else {
//			System.out.println("** 로그인 실패 **");
//			request.setAttribute("message", "로그인 실패 ! 다시 로그인 하세요 ~~ ");
//			uri="servletTestForm/flowEx04_Login.jsp";
//			request.getRequestDispatcher(uri).forward(request, response);
//		}
//		//3. View(Response) : Forward or Redirect
//		request.getRequestDispatcher(uri).forward(request, response);
	
	
	}//doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}//doPost

}//class

package controllerM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import domain.MemberDTO;
import service.MemberService;

@WebServlet("/login")
public class C02_Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public C02_Login() {
        super();
    }

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청분석
	    // => request 의 Parameter 처리
	    // => id, password 처리
		String id=request.getParameter("id");
		String password=request.getParameter("password");
		
	    // 2. 서비스 처리
	    // => Service, DTO 객체 생성
	    // => id 확인 : Service의 selectOne
		// => id 확인되면 password 일치 확인
		// => 성공: id, name을 session에 보관, home으로 이동
		//	  실패: loginForm으로 message출력, 재로그인 유도
		MemberService ms = new MemberService();
		MemberDTO dto = ms.selectOne(id);
		if (dto != null && password.equals(dto.getPassword())) {
			request.getSession().setAttribute("loginID", dto.getId());
			request.getSession().setAttribute("mName", dto.getName());
			request.getSession().setAttribute("mPassword", dto.getPassword());
			// forward 방식
//				request.getRequestDispatcher("home.jsp").forward(request, response);
			// redirect 방식
			response.sendRedirect("home.jsp");
		}else {
			request.setAttribute("message", "로그인 실패! 다시 로그인해주세요"); //forward로 처리해야 나오는 문구
			response.sendRedirect("/web02/member/loginForm.jsp");
		}
		
			
	    // 3. View (Response) : Forward
			
	
	}//doPost

}//class

package controllerM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Service;

import com.mysql.cj.Session;

import domain.MemberDTO;
import service.MemberService;

@WebServlet("/mdetail")
public class C03_mDetail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public C03_mDetail() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. 요청분석
	    // => id 처리, session에서 getAttribute
		String id = (String)request.getSession().getAttribute("loginID");
		String uri="member/memberDetail.jsp"; //성공시
		// => Update 요청 시에는 updateForm.jsp로 이동
		if ("U".equals(request.getParameter("jCode"))){
			uri="member/updateForm.jsp";
		}
		
	    // 2. 서비스 처리
	    // => Service, DTO 객체 생성
		// => 결과를 View가 출력 가능 하도록 Attribute에 저장
		MemberService service = new MemberService();
		request.setAttribute("apple", service.selectOne(id));
		
	    // 3. View (Response) : Forward
		request.getRequestDispatcher(uri).forward(request, response);
	}//doGet

}//class

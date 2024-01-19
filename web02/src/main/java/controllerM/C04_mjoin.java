package controllerM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.MemberDTO;
import service.MemberService;

@WebServlet("/mjoin")
public class C04_mjoin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public C04_mjoin() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 요청분석
		//=> request의 한글(post요청 시 필수) & Parameter 처리
		//=> 성공: 로그인 유도(loginForm.jsp)
		//=> 실패: 재가입 (joinForm.jsp)
		String uri="memeber/loginForm.jsp"; //성공시
		request.setCharacterEncoding("utf-8");
		MemberDTO dto = new MemberDTO();
		dto.setId(request.getParameter("id"));
		dto.setPassword(request.getParameter("password"));
		dto.setName(request.getParameter("name"));
		dto.setAge((Integer.parseInt(request.getParameter("age"))));
		dto.setJno((Integer.parseInt(request.getParameter("jno"))));
		dto.setInfo(request.getParameter("info"));
		dto.setPoint((Double.parseDouble(request.getParameter("point"))));
		dto.setBirthday(request.getParameter("birthday"));
		dto.setRid(request.getParameter("rid"));
		
		//2. 서비스 처리
		//=> Service 객체 생성 & 실행
		MemberService sc = new MemberService();
		if(sc.insert(dto) > 0) {
			//=> 성공
			request.setAttribute("message", " ~~ 회원가입 성공 ~~ 로그인 후 이용하세요 ~~");
			uri="member/loginForm.jsp";
			
		}else {
			//=> 실패
			request.setAttribute("message", " 회원가입 실패 !!! 다시 가입하세요");
			uri="member/joinForm.jsp";
			
		}
				
		
		//3. View(Response) : Forward
		request.getRequestDispatcher(uri).forward(request, response);
		
		
	}//doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}//doPost

}//class

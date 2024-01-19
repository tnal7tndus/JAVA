package controllerM;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.MemberDTO;
import service.MemberService;

@WebServlet("/mupdate")
public class C05_mUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public C05_mUpdate() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1. 요청분석
		//=> request의 한글(post요청 시 필수) & Parameter 처리
		//=> 성공: 내정보로 이동(memberDetail.jsp)
		//=> 실패: 재수정 유도(updateForm.jsp)
		//=> 출력객체(apple) 필요함
		//		-> redirect 또는 전달된 값들을 detail에 저장
		
		String uri="member/memberDetail.jsp"; //성공시
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
		
		//=> 결과 출력을 위해 전달된 값들을 detail에 보관
		request.setAttribute("detail", dto);
		
		//2. 서비스 처리
		//=> Service 객체 생성 & 실행
		MemberService sc = new MemberService();
		if(sc.update(dto) > 0) {
			//=> 성공
			request.getSession().setAttribute("mName", dto.getName());
			request.setAttribute("message", " ~~ 회원 정보 수정이 완료되었습니다 ~~");
			
		}else {
			//=> 실패
			request.setAttribute("message", " 회원 정보 수정 실패! 다시 입력하세요");
			uri="member/updateForm.jsp";
			
		}
				
		//3. View(Response) : Forward
		request.getRequestDispatcher(uri).forward(request, response);
		
		
	}//doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}//doPost

}//class

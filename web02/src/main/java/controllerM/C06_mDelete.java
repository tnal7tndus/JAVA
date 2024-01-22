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

@WebServlet("/mdelete")
public class C06_mDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public C06_mDelete() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		
//		MemberService sc = new MemberService();
//		
//		if(sc.delete((String)request.getSession().getAttribute("mId"))>0) {
//			request.getSession().invalidate();
//			request.setAttribute("message", "회원탈퇴 완료");
//			request.getRequestDispatcher("home.jsp").forward(request, response);
////			request.getSession().setAttribute("message", "~~~~");
//		}
	
		if (request.getSession().getAttribute("mPassword").equals(request.getParameter("password"))) {
			MemberService sc = new MemberService();
			sc.delete((String)request.getSession().getAttribute("mId"));
			request.getSession().invalidate();
			request.setAttribute("dMessage", "회원탈퇴 완료");
			request.getRequestDispatcher("home.jsp").forward(request, response);
		} else {
			request.setAttribute("dMessage", "탈퇴 실패");
			request.getRequestDispatcher("member/mdelete.jsp").forward(request, response);
		}
	}

	// 3. View(Response) : Forward

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}//doPost

}//class


//민지언니 코드
//protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    MemberService sc = new MemberService();
//    MemberDTO dto = sc.selectOne((String)request.getSession().getAttribute("mId"));
//
////    System.out.println(request.getParameter("password"));
//    System.out.println(dto);
//    System.out.println(request.getParameter("password"));
//
//    if(request.getParameter("password").equals(dto.getPassword())) {
//        System.out.println("탈퇴완료");
//        request.getSession().invalidate();
//        request.setAttribute("message", "회원 탈퇴 완료");
//        request.getRequestDispatcher("home.jsp").forward(request, response);
//    } else {
//        System.out.println("탈퇴실패");
//        request.setAttribute("message", "비번을 다시 확인하세욬ㅋ");
//        request.getRequestDispatcher("/member/deleteForm.jsp").forward(request, response);
//    }
//}
//
//
//protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//    doGet(request, response);
//}
//
//}

package mvcTest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.cj.Session;

import mvcTest.StudentService;
import mvcTest.StudentDTO;

@WebServlet("/myinfo")
public class Ex02_MVC02Detail extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Ex02_MVC02Detail() {
        super();
    }
    
    //** MVC 패턴2 StudentDtail 출력하기
    //=> 요청 Service 처리
    //=> 결과 출력
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1) 요청분석 & Service 처리
		//=> 검색 대상의 id(sno) 필요(로그인시에 보관해둠)
		//=> siession에서 getAttribute
		StudentService sc = new StudentService();
		StudentDTO dto = sc.selectOne((int)request.getSession().getAttribute("loginSno"));
		//dto = null; -> Test용
		//2) View 준비
		//=> 결과를 view가 인식하도록 setAttribute
		//=> Forward
		request.setAttribute("detail", dto);
		
		request.getRequestDispatcher("mvcTestJsp/ex03_MVC02Detail.jsp").forward(request, response);
	
	}//doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	
	
	} //doPost

}//class

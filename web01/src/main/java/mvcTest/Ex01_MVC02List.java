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

import mvcTest.StudentService;
import mvcTest.StudentDTO;

/**
 * Servlet implementation class Ex02_MVC01List
 */
@WebServlet("/list2")
public class Ex01_MVC02List extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Ex01_MVC02List() {
        super();
    }

    //** MVC 패턴2 StudentList 출력
    //=> 요청 Service 처리
    //=> 결과 출력 (Java)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//=> 오청 Service 처리
		StudentService service = new StudentService();
		List<StudentDTO> list = service.selectList();
		
		//=> 결과 출력 : Jsp, Java
		//=> Service 결과확인 List를 Java가 출력할 수 있도록 Attribute만들어 보관
		//	 request.setAttribute(....)
		request.setAttribute("myList", list);
		//=> Forward
		request.getRequestDispatcher("mvcTestJsp/ex01_MVC02List.jsp").forward(request, response);
	
	}//doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	
	
	} //doPost

}//class

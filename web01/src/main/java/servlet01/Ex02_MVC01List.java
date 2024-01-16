package servlet01;

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
@WebServlet("/list")
public class Ex02_MVC01List extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Ex02_MVC01List() {
        super();
    }

    //** MVC 패턴1 StudentList 출력
    //=> 요청 Service 처리
    //=> 결과 출력
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//=> 오청 Service 처리
		StudentService sc = new StudentService();
		List<StudentDTO> list = new ArrayList<StudentDTO>();
		list = sc.selectList();
		
		//=> 결과 출력 : 출력내용을 Respose 객체의 Body 영역에 Write
		//	- 한글처리
		//	- 출력객체 생성 & 출력
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		out.print("<html><body>");
		out.print("<h2 style='color:blue;'>** Servlet_MvC1 StudentList **</h2>");
		out.print("<table border=1><tr><th>Sno</th><th>Name</th><th>Age</th><th>Jno</th><th>Info</th><th>Point</th><tr>");
		
		if(list != null) {
			for(StudentDTO s:list) {
//				out.print(s+"<br>");
				out.print("<tr><td>"+s.getSno()+"</td>");
				out.print("<td>"+s.getName()+"</td>");
				out.print("<td>"+s.getAge()+"</td>");
				out.print("<td>"+s.getJno()+"</td>");
				out.print("<td>"+s.getInfo()+"</td>");
				out.print("<td>"+s.getPoint()+"</td></tr>");
				
			}
		}else {
			out.print("<h2>~~ 출력할 Data가 없습니다. ~~</h2>");
		}
		out.print("</table></body>");
		
		
		//내가만든거
//		out.printf("<form action=\"list\"><input type=\"submit\" value=\"마지막 학생 삭제\"></input></form>");
//		out.printf("<table border = '1'><thead><tr>");
//		out.printf("<th>학생번호</th><th>이름</th><th>나이</th><th>조번호</th><th>정보</th><th>포인트</th>");
//		out.printf("</tr></thead><tbody>");
//		
//		for(int i = 0; i < list.size(); i++) {
//			out.printf("<tr>");
//			out.printf("<td>%d</td>",list.get(i).getSno());
//			out.printf("<td>%s</td>",list.get(i).getName());
//			out.printf("<td>%d</td>",list.get(i).getAge());
//			out.printf("<td>%d</td>",list.get(i).getJno());
//			out.printf("<td>%s</td>",list.get(i).getInfo());
//			out.printf("<td>%.2f</td>",list.get(i).getPoint());
//			out.printf("<tr>");
//		}
//		out.printf("</tbody></table>");
		
	} //doGet

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	
	
	} //doPost

}//class

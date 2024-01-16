package servlet02_form;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/adder")
public class Ex01_Adder extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Ex01_Adder() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1) 요청분석
		//=> request 처리 : 한글, Parameter
		String num1 = request.getParameter("num1"); //파라미터는 무조건 스트링으로!
		String num2 = request.getParameter("num2");
		
		//선생님 답
//		String number = request.getParameter("num1");
//		int num1=Integer.parseInt(number);
//		int num2=Integer.parseInt(request.getParameter("num2"));
				
		
		//2) Service & 결과 처리
		//=> response 한글처리, 출력객체 생성 & response에 담기
		response.setContentType("text/html; charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		
		out.print(Integer.parseInt(num1)+ Integer.parseInt(num2));
//		out.print("<h3>=> " +num1+ " + " +num2+" = " +(num1+num2)+"</h3>");  //선생님 답
//		out.printf("<h3>=> %d+%d = %d</h3>", num1, num2,(num1+num2));   //선생님 답
	
	}//doGet

}//class

package servlet02_form;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/check")
public class Ex03_Check extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Ex03_Check() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	response.setContentType("text/html; charset=UTF-8");
	
	// => CheckBox 처리
    //   -> 하나의 Name 에 복수개의 Value 들이 있음
    //   -> request.getParameterValues("gift") 를 이용해서 배열로 처리    
    String[] gift = request.getParameterValues("gift");

    // 2) Service & 결과 처리
    //=> response 한글처리, 출력객체생성 & response에 담기
    PrintWriter out = response.getWriter();
    out.print("<h2>** CheckBox Test **</h2>");
    
    //=> 선택여부 확인
    if(gift!=null&& gift.length>0) {
    	//=>선택함
    	for(String s:gift) {
    		out.printf("<h3>%s</h3>",s);
    	}
    }else {
    	out.print("<h3>=> 선택항목이 없음</h3>");
    }
    out.print("<br><br><h2><a href='javascript:history.go(-1)'>다시 입력하기</a></h2><br>");
	
	}//doGet

}//class

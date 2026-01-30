package sec01.ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 두번쨰 서블릿 SecondServlet 클래스
 -> 첫번째 서블릿 FirstServlet으로 부터 포워딩(재요청)되어 요청 받는 두번째 서블릿 SecondServlet 입니다.
 -> 포워딩(재요청) 받는 주소 :  http://localhost:8090/pro08/second
*/

//@WebServlet("/second")
public class SecondServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
						 								throws ServletException, IOException {
		//1. 포워딩(재요청)한 클라이언트의 웹브라우저창 으로 응답 할 데이터 유형(MIME-TYPE)을 텍스트기반의 HTML문자열로 설정하고, 
		//   문자데이터 처리방식을 UTF-8
		response.setContentType("text/html; charset=utf-8");
		
		//2. 클라이언트의 브라우저와 연결된 데이터를 내보내어 전송할 출력스트림 통로 역할의 PrintWriter객체 생성
		PrintWriter out = response.getWriter();
		
		//3. 현재 두번째 서블릿인 SecondServlet 내부에서 응답할 메세지를 생성하고 브라우저로 응답
		out.print("<html><body>");
		
		out.print("response.addHeader 메소드 호출 포워딩 Refresh방법으로 1초 휴식 후 포워딩(재요청) 당한 SecondServlet이 응답한 데이터");
		
		out.print("</body></html>");
		
	}
	
	
}












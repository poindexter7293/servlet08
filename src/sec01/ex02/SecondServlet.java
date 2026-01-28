package sec01.ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/second")
public class SecondServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	req.setCharacterEncoding("UTF-8");	
	resp.setContentType("text/html; charset = UTF-8");
	PrintWriter out = resp.getWriter();
	
	out.print("<html><body>");
	
	
	out.print("response.addHeader 메소드 호출 후 포워딩 Refresh방법으로 1초 휴식 후 "
			+ "포워딩(재요청) 당한 SecondServlet에서 재요청한 데이터");
	out.print("</body></html>");
	}
}	

package sec04.ex02;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/second")
public class SecondServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	req.setCharacterEncoding("UTF-8");	
	resp.setContentType("text/html; charset = UTF-8");
	
	String address = (String)req.getAttribute("address");
	
	
	PrintWriter out = resp.getWriter();
	
	out.print("<html><body>");
	
	out.print("공유 받아 출력하는 값 : " + address + "<br>");
	
	
	out.print("RequestDispatcher 객체의 forward() 메소드를 이용해 포워딩 당한 secondServlet이 응답한 데이터 "
			+ "첫번째 서블릿이 공유한 데이터 :" + req.getParameter("name"));
	out.print("</body></html>");
	}
}	

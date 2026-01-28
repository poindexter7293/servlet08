package sec04.ex03;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/viewMembers")
public class viewServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("UTF-8");	
		resp.setContentType("text/html; charset = UTF-8");
		PrintWriter out = resp.getWriter();
		
		List list = (List)req.getAttribute("memberList");
		
		
		out.print("<html>");
		out.print("<body>");
			out.print("<table border=1>");
				out.print("<tr align='center' bgcolor='lightgreen'>");
				out.print("<th>아이디</th>");	
				out.print("<th>비밀번호</th>");	
				out.print("<th>이름</th>");	
				out.print("<th>이메일</th>");	
				out.print("<th>가입일자</th>");		
				out.print("</tr>");
				for (int i = 0; i < list.size() ; i++) {
					MemberVO memberVO = (MemberVO)list.get(i);
					out.print("<tr align='center'>");
					out.print("<td>"+ memberVO.getId()+"</td>");	
					out.print("<td>"+ memberVO.getPwd()+"</td>");	
					out.print("<td>"+ memberVO.getName()+"</td>");	
					out.print("<td>"+ memberVO.getEmail()+"</td>");	
					out.print("<td>"+ memberVO.getJoinDate()+"</td>");	
					out.print("</tr>");
				}
				out.print("</table>");
				out.print("</body>");
				out.print("</html>");
		
		
	}

}

package sec04.ex03;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sec04.ex03.MemberDAO;
import sec04.ex03.MemberVO;


@WebServlet("/member4")
public class MemberServlet extends HttpServlet {
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doHandle(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		doHandle(req, resp);
	}
	
	
	protected void doHandle(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		MemberDAO memberdao = new MemberDAO();
		
	req.setCharacterEncoding("UTF-8");
	
	String command = req.getParameter("command");
	
	if(command != null && command.equals("addMember")){
		
		String _id = req.getParameter("id");
		String _pwd = req.getParameter("pwd");
		String _name = req.getParameter("name");
		String _email = req.getParameter("email");
		
		MemberVO vo = new MemberVO(_id, _pwd, _name, _email);
		
		int result = memberdao.addMember(vo);
		
		if (result == 1) {
			System.out.println("동작 성공");
		}else {
			System.out.println("동작 실패");			
		}
		
		List list = memberdao.listMembers();
		
		resp.setContentType("text/html; charset=UTF-8");
		PrintWriter out = resp.getWriter();	
		
		out.print("<html>");
		out.print("<body>");
			out.print("<table border=1>");
				out.print("<tr align='center' bgcolor='lightgreen'>");
				out.print("<th>아이디</th>");	
				out.print("<th>비밀번호</th>");	
				out.print("<th>이름</th>");	
				out.print("<th>이메일</th>");	
				out.print("<th>가입일자</th>");	
				out.print("<th>삭제</th>");	
				out.print("<th>수정</th>");	
				out.print("</tr>");
				for (int i = 0; i <list.size() ; i++) {
					MemberVO memberVO = (MemberVO)list.get(i);
					out.print("<tr align='center'>");
					out.print("<td>"+ memberVO.getId()+"</td>");	
					out.print("<td>"+ memberVO.getPwd()+"</td>");	
					out.print("<td>"+ memberVO.getName()+"</td>");	
					out.print("<td>"+ memberVO.getEmail()+"</td>");	
					out.print("<td>"+ memberVO.getJoinDate()+"</td>");	
					out.print("<td><a href='/pro07/member3?command=delMember&id="+ memberVO.getId()+"'>삭제</a></td>");
					out.print("<td><a href='/pro07/member3?command=fixMember&id="+ memberVO.getId()+"'>수정</a></td>");
					
					
					out.print("</tr>");
				}
				out.print(" <a href='http://localhost:8090/pro07/memberForm.html'>회원가입</a> ");
				
			out.print("</table>");
		out.print("</body>");
		out.print("</html>");
		
		}else if(command != null && command.equals("delMember")) {
			
			String id = req.getParameter("id");
			memberdao.delMember(id);
			
		}
	
	
	}
}
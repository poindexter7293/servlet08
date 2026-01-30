package sec04.ex03;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sec04.ex03.MemberDAO;
import sec04.ex03.MemberVO;

				

@WebServlet("/member4")
public class MemberServlet extends HttpServlet  {
	
	//doGet, doPost메소드 오버라이딩 -> alt + shift + s  v
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
						throws ServletException, IOException {
		doHandle(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
						throws ServletException, IOException {
		doHandle(request, response);
	}	
	
	protected void doHandle(HttpServletRequest request, HttpServletResponse response) 
						 	throws ServletException, IOException {
		
		//---- DB와 연결해서 작업할 MemberDAO클래스의 객체를 생성해서 메소드를 호출할수 있도록 하자 ------
		MemberDAO memberdao = new MemberDAO();
		
		
		//1. 웹브라우저를 사용한 클라이언트가 요청 한데이터들 중에 문자 깨짐을 방지 해서 얻기 위해 
		//	 HttpServletRequest객체 메모리의 문자 처리 방식을 UTF-8로 설정 
		//요약 : 한글처리
		request.setCharacterEncoding("UTF-8");
		
		//2.1. MemberServlet이 어떤 요청(회원조회? 회원추가? 회원삭제? 회원수정? 중 회원 추가요청)을 받았는지 판단
		//회원 추가 요청한 조건값  "addMember"얻기 
		//회원 삭제 요청한 조건값  "delMember"얻기
		String command = request.getParameter("command"); 

		// http://localhost:8090/pro07/member3?command=delMember&id=hong
		
		//2.2. DB의 t_member테이블에 새회원 추가 요청("addMember")을 서블릿이 받았다면?
		if(command != null  && command.equals("addMember")) {
			
			//2.3 회원가입(t_member테이블에 입력한  회원 추가)을 위해 입력해서 요청한 데이터 얻기
			//=============== 회원가입 폼에서 입력한 값들을 HttpServletRequest객체 메모리에서 얻기=========
			String _id = request.getParameter("id");
			String _pwd = request.getParameter("pwd");
			String _name = request.getParameter("name");
			String _email = request.getParameter("email");

			//2.4 회원가입을 위해 입력한 정보들을 MemberVO클래스의 객체를 생성하여 각 인스턴스변수에 저장
			//=============== MemberVO객체 생성 후 저장================================
			//회원 1명의 정보를 담는 객체 
			MemberVO vo = new MemberVO(_id, _pwd, _name, _email);
			
			//2.5  MemberDAO에 만들어 놓은 addMember 메소드 호출시~ 매개변수로 가입시 입력한 정보가 각 인스턴스변수에 저장된 MemberVO객체 주소를 전달하여
			//     INSERT 명령! ,  INSERT에 성공하면 1을 반환,  INSERT에 실패하면 0을 반환 받게 addMember 메소드를 호출하자.
			//=============== MemberDAO에게 새회원 추가 INSERT 요청=======================
			int result = memberdao.addMember(vo);
					
			System.out.println("회원가입 성공 하면 1 출력, 회원가입에 실패하면 0 출력 = " + result);
			
			
		//2.6. 회원 한사람의 삭제 요청 을 서블릿이 받았다면?	
		//     command변수에 저장된 값이 "delMember" 과 같다면?
		}else if(command != null  && command.equals("delMember") ) {
			
		// http://localhost:8090/pro07/member3?command=delMember&id=hong	<- 삭제 요청 받은 요청 주소 
			
			//요청한 삭제할 회원 아이디 얻기 
				//삭제할 회원의 아이디를 HttpServletRequest객체 메모리에서 꺼내오기
				//얻는 이유 : DELETE 구문 작성시  어떤 아이디를 가진 회원 레코드를 삭제할 것인지 판단해서 회원 레코드 하나만 삭제 시키기 위함
				//     예 : delete from t_member where id='hong';
			String id = request.getParameter("id");
			
			//요청 받은 삭제할 회원 아이디를 이용해 DB의 t_member테이블에 저장된 하나의 회원 레코드 삭제하기 위해 
			//MemberDAO에 만들어 놓은 delMember메소드 호출!
			memberdao.delMember(id);
								
		//2.6. 회원 한사람 수정 요청 을 서블릿이 받았다면?	
		//     command변수에 저장된 값이 "modMember" 과 같다면?
		}else if(command != null  && command.equals("modMember") ) {
			
		// http://localhost:8090/pro07/member3?command=modMember&id=hong	<- 수정 요청 받은 요청 주소 
			
			//수정할  회원 아이디 를 이용해 DB의 t_member테이블에서  조회 해 오자.
			MemberVO vo = memberdao.modMember(request.getParameter("id"));
			
			System.out.println(vo.getId());
			System.out.println(vo.getPwd());
			System.out.println(vo.getName());
			System.out.println(vo.getEmail());
			
			//조회해 온 수정할 회원 정보를 보여줄 정적 화면(memberModForm.html) 재요청시..
			//MemberVO객체를 HttpServletRequest에 담아 전달 후 꺼내서  보여주기
			
			//이단계는?  지금 배우지 않았습니다. 그러므로 배우고 한번 시도 해 보세요.
			
				
		}
		
		//흐름 : 위 새 회원 추가 후 아래의 코드 memberdao.listMembers(); 에 의해 모든 회원정보를 t_member테이블에서 조회후 보여 줄것이다.
		//흐름 : 위 회원 삭제 후     아래의 코드 memberdao.listMembers(); 에 의해 삭제 후 모든 회원정보를 t_member테이블에서 조회후 보여 줄것이다.
		
			
		//3.1. MemberDAO 객체의 listMembers() 메소드를 호출하여
		//     t_member테이블에 저장된 모든 회원 레코드 정보를 조회 하는 명령을 합니다.
		List list = memberdao.listMembers();//<---- 클라이언트의 웹브라우저로 응답할 조회된 회원레코드 전체(ArrayList배열)반환 받습니다.
		
		//3.2. 조회된 ArrayList배열 메모리 자체를? HttpServletRequest객체 메모리에 바인딩 
		request.setAttribute("membersList", list);
		
		//3.3. RequestDispatcher객체의 forward메소드 호출 방법으로  두번째 서블릿 ViewServlet으로  포워딩(재요청)시~~
		//     ArrayList배열이 값 형태로 저장된 HttpServletRequest객체 메모리 전달해서 공유!
		RequestDispatcher dispatcher = request.getRequestDispatcher("viewMembers");
		
		//실제 포워딩시 HttpServletRequest객체 메모리와 HttpServletResponse객체 메모리 공유
		dispatcher.forward(request, response);
					
		
		
			
	}//doPost 메소드 
	
}// class MemberServlet








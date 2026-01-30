package sec05.ex01;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// 순서3. 클라이언트가  다른 종류의 엣지 웹브라우저 주소창에 요청URL을 입력하여 GetServletContext 서블릿 클래스를 톰캣 서버에 요청합니다.
//       요청 하는 주소 -> http://localhost:8090/pro08/cget

// 순서4. 요청을 받은 GetServletContext class 내부에 개발자가 ServletContext객체 메모리를 얻기 위해 getServletContext()메소드 호출 구문을 작성 해 놓는다.
//		 그리고  getAttribute("key"); 메소드를 호출 하는 코드를 작성 하여  공유받은 ServletContext객체 메모리 내부에 바인딩 됬던 ArrayList배열을 얻어 사용 해 봅니다.

@WebServlet("/cget")
public class GetServletContext extends HttpServlet  {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//재료 
		//1.  요청한 클라이언트의 브라우저로 응답할 데이터 유형 설정 및 한글처리
		response.setContentType("text/html; charset=utf-8");
		
		//2. 출력스트림 생성
		PrintWriter out = response.getWriter();
		
		//실제 작업
		//1. 톰캣 웹 애플리케이션 서버가  생성 해 놓은 ServletContext 객체 메모리 주소 얻어 저장
		ServletContext servletContext = this.getServletContext();
		
		//2.getAttribute("key"); 메소드를 호출 하는 코드를 작성 하여  공유받은 ServletContext객체 메모리 내부에 바인딩 됬던 ArrayList배열을 얻어 사용 해 봅니다.
		List list = (List)servletContext.getAttribute("member");
		
		//ArrayList 배열에 저장된  이름과 나이를 얻어 저장 
		String name = (String)list.get(0);  // "이순신" 문자열 얻기 
		int age = (Integer)list.get(1);     //new Integer(30); 객체를 얻어 오토언박싱해서 30을 꺼내와 저장 		
		
		out.println("GetServletContext 서블릿 클래스가  ServletContext객체 메모리 내부의 ArrayList 자원을 공유받아 사용했다.");
		out.println("이름 : " + name + ", 나이 : " + age); //클라이언트의 엣지 브라우저로 응답
		
		/*
			결론 -   이번 예제는 ServletContext 서블릿 관련 공유 객체 메모리 내부 영역을 사용해서
			              하나의 웹애플리케이션(pro08) 내부에 만들어 놓은 모든 서블릿(서버페이지)들이 공유해서 사용해야할 데이터가 있을때....
			        ServletContext 객체 메모리 내부에 공유해서 사용할 데이터들을 바인딩 해 놓고, 얻어 사용하는 예제 였습니다.

		*/
	}
	
	
}












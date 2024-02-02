package kr.or.ddit.basic.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessionAdd.do")
public class SessionAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Session 정보 저장하기
		
		// 1.새로운  Session객체를 생성하거나 현재의 Session 가져오기
		// 형식) Request객체.getSession(); 또는 Request객체.getSession(true);
		//		==> 현재 Session이 존재하면 현재 Session을 반환하고,
		//			존재하지 않으면 새로운 Session을 생성하여 반환한다.
		// 형식) Request객체.getSession(false);
		//		==>현재 Session이 존재하면 현재 Session을 반환하고,
		//		       존재하지 않으면 새로운 Session을 생성하지 않고 null을 반환한다.
		
		HttpSession session = request.getSession();
		
		// 2. setAttribute()메서드로 Session에 값을 저장한다.
		// 형식) Session객체.setAttribute("key값", session값);
		//		==> 'key값'은 문자열, 'session값' 은 모든 종류의 데이터를 사용할 수 있다.
		session.setAttribute("testSession", "연습용 세션입니다.");
		session.setAttribute("userName", "홍길동");
		session.setAttribute("age", 30);
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'>"
				+ "<title>Session 저장연습</title></head>");
		out.println("<body>");
		
		out.println("<h3>Session이 저장되었습니다...</h3><br><br><br>");
		out.println("<a href='"+ request.getContextPath()+ "/basic/session/sessionTest01.jsp'>시작문서로 이동하기</a>");
		
		
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

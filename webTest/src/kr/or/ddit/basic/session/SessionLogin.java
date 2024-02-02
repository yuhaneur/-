package kr.or.ddit.basic.session;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/sessionLogin.do")
public class SessionLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String userId = request.getParameter("id");
		String userPw = request.getParameter("pw");
		
		System.out.println(userId);
		System.out.println(userPw);
		
		HttpSession session = request.getSession();
		
		
		session.setAttribute("userID", userId);
		session.setAttribute("userPW", userPw);
		String chkId = (String) session.getAttribute("userID");
		if(userId.equals("admin") && userPw.equals("1234")) {
			out.println("<html>");
			out.println("<head><meta charset='utf-8'>"
					+ "<title>Session 저장연습</title></head>");
			out.println("<body>");
			
			out.println("<h1>"+chkId +"님 반갑습니다.</h1><br><br>");
			out.println("<a href='/webTest/sessionLogout.do'>로그아웃</a>");
			
			
			out.println("</body></html>");
		}else {
			
			response.sendRedirect(request.getContextPath() + "/basic/session/sessionLogin.jsp");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

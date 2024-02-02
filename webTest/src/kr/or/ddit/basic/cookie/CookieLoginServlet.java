package kr.or.ddit.basic.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieLoginServlet.do")
public class CookieLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();

		String vid = request.getParameter("id");
		String vpass =request.getParameter("pass");
		String chk = request.getParameter("chkBox");
		Cookie id = new Cookie("id",vid);
		System.out.println(chk);
		if(chk==null) {	
			id.setMaxAge(0);
		}
		response.addCookie(id);
		// 로그인 성공 여부 확인
		if("test".equals(vid) && "1234".equals(vpass)) {
			// 로그인 성공
			response.sendRedirect(request.getContextPath() + "/basic/cookie/cookieMain.jsp");
		}else {
			response.sendRedirect(request.getContextPath() + "/basic/cookie/cookieLogin.jsp");
		}
		
	}


}

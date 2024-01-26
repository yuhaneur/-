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
		System.out.println(vid);
		System.out.println(vpass);
		System.out.println(chk);
		
		Cookie id = new Cookie("id",vid);
		Cookie pass = new Cookie("pass",vpass);
		Cookie chkBox = new Cookie("pass",chk);
		response.addCookie(id);
		response.addCookie(pass);
		if(chk.equals("on")) {	
			Cookie[] cookieArr = request.getCookies();
			for(Cookie cookie : cookieArr) {
				if("id".equals(id.getName())) {
					request.setAttribute("idVal", id.getValue()); 
					request.setAttribute("chkVal", chkBox.getValue()); 
				}
			}
		}else {
			id.setMaxAge(0);
		}
		
	}


}

package kr.or.ddit.basic.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieCountServlet.do")
public class CookieCount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		int cnt=0;
		Cookie[] cookieArr = request.getCookies();
		if(cookieArr!=null) {
			for(Cookie cookie : cookieArr) {
				if(("count").equals(cookie.getName())) {
					cnt =Integer.parseInt(cookie.getValue());
					break;
				}
			}
		}
		cnt++;
		Cookie count = new Cookie("count",cnt+"");
		response.addCookie(count);
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'>"
				+ "<title>Cookie 카운트</title></head>");
		out.println("<body>");
		out.println("<h1>어서오세요 당신은 "+count.getValue()+"번째 방문입니다.");
		out.println("<a href='"+ request.getContextPath()+ "/cookieCountServlet.do'>카운트 증가하기</a>");
		out.println("<a href='"+ request.getContextPath()+ "/basic/cookie/cookieTest02.jsp'>시작문서로 이동하기</a>");
		cnt++;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

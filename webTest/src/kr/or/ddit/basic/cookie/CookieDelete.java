package kr.or.ddit.basic.cookie;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookieADelete.do")
public class CookieDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		/*
		- Cookie 정보 삭제하기
			==> 쿠키 데이터의 삭제는 쿠키의 유지시간을 0으로 설정하는 방법을 사용한다.
			==> 삭제할 쿠키를 찾은 후 해당 쿠키의 속성 중 유지시간을 0으로 변경한 후
				다시 저장하는 방법을 사용한다.
		
		
		1. 전체 쿠키 정보를 Request객체를 통해서 가져온다.
				==> 가져온 쿠키 정보들은 배열에 저장되어 반환된다.
		형식 ) Cookie[] 쿠키배열변수 = request.getCookies();
		 */
		Cookie[] cookieArr = request.getCookies();
		
		if(cookieArr!=null) {
		// 2. 쿠키 배열에서 삭제할 쿠키 정보를 구해온다.
			for(Cookie cookie : cookieArr) {
				String name = cookie.getName();  // 쿠키이름 구하기
				
				// 3. 삭제할 쿠키와 이름이 같은 Cookie객체를 구한다.
				//		예) 쿠키이름이 'gender'인 쿠키 삭제하기
				if("gender".equals(name)) {
					// 4. 삭제할 쿠키의 유지시간을 0으로 다시 설정한다.
					cookie.setMaxAge(0);
					
					// 5. 삭제할 Cookie객체를 다시 저장한다.
					response.addCookie(cookie);
				}
			}
		}
		
		out.println("<html>");
		out.println("<head><meta charset='utf-8'>"
				+ "<title>Cookie 삭제 연습</title></head>");
		out.println("<body>");
		
		out.println("<h2>쿠키 삭제 하기</h2>");
		
		out.println("<hr>");
		out.println("<a href='"+ request.getContextPath()+ "/basic/cookie/cookieTest01.jsp'>시작문서로 이동하기</a>");
		
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}

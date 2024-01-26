package kr.or.ddit.basic.reqNresp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestTest02
 */
@WebServlet("/requestTest01.do")
public class RequestTest01 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RequestTest01() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 클라이언트가 보낸 데이터의 문자 인코딩 방식 지정==>POST방식의설정
		request.setCharacterEncoding("utf-8");
		
		// 클라이언트가 보낸 데이터(Parameter) 받기
		
		// 방법1) request.getParameter("파라미터명");
		//		==> 주어진 '파라미터명'에 설정된 '값'을 가져온다.
		//		==> 가져오는 '값'의 자료형은 'String' 이다.
		
		String userName = request.getParameter("userName");
		String job = request.getParameter("job");
		
		// 방법2) request.getParameterValues("파라미터명");
		//		==> '파라미터명'이 같은 것이 여러개 일 경우에 사용한다.
		//		==> 가져오는 '값'의 자료형은 'String[]' 이다.
		
		// 취미 데이터 받기
		String hobbies[] = request.getParameterValues("hobby");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>객체 연습</title></head>");
		out.println("<body>");
		
		out.println("<h2>Request의 파라미터 데이터 받기</h2>");
		out.println("<table border='1'>");
		
		out.println("<tr><td>이 름</td>");
		out.println("<td>" + userName + "</td></tr>");

		out.println("<tr><td>직 업</td>");
		out.println("<td>" + job + "</td></tr>");

		out.println("<tr><td>취 미</td>");
		out.println("<td>");
		if(hobbies!=null) {
			// 배열 개수만큼 반복 처리
			// 향상된 for문
			for(String hobby: hobbies) {
				out.println(hobby + "<br>");
			}
			// 일반 for문
			for(int i=0; i<hobbies.length; i++) {
				out.println(hobbies[i] + "<br>");				
			}
		}
		out.println("</td></tr>");
		out.println("</table>");
		
		out.println("<hr>");
		
		out.println("<h2> Request객체 제공 메서드들...</h2>");
		out.println("<ol>");
		out.println("<li>클라이언트 주소 : " + request.getRemoteAddr()+ "</li>");
		out.println("<li>요청 메서드 : " + request.getMethod()+ "</li>");
		out.println("<li>ContextPath : " + request.getContextPath()+ "</li>");
		out.println("<li>프로토콜 : " + request.getProtocol()+ "</li>");
		out.println("<li>URL정보 : " + request.getRequestURL()+ "</li>");
		out.println("<li>URI정보 : " + request.getRequestURI()+ "</li>");
		out.println("</ol>");
		
		out.println("<hr>");
		
		// request객체.getParameterMap()메서드
		// ==> 전송된 모든 파라미터를 Map객체에 담아서 반환한다.
		// ==> 이 Map객체의 key값은 '파라미터명'이며 자료형은 'String'이고,
		//		value값은 해당 파라미터에 저장된'값'이며 자료형은 'String[]'이다.
		
		Map<String, String[]> paramMap = request.getParameterMap(); 
		out.println("<h2>getParameterMap()메서드 처리결과 </h2>");
		out.println("<ul>");
		
		// Map의 개수만큼 반복처리
		for(String key : paramMap.keySet()) {
			// value값 즉, 파라미터 값 구하기
			String[] valueArr = paramMap.get(key);
			out.println("<li>");
			out.println("파라미터명 : " + key + ", 값 = ");
			if(valueArr==null || valueArr.length==0) {// 값이 없을떄
				continue;
			}else if(valueArr.length==1){// 파라미터가 배열이 아닐 때
				out.println(valueArr[0]);
			}else { // 파라미터가 배열일 때...
				for(String val : valueArr) {
					out.println(val + "\t");
				}
			}
			
			
			out.println("</li>");
			
		}
		
		out.println("</ul>");
		
		
		
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

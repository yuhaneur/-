package kr.or.ddit.basic.reqNresp;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/forwardMain.do")
public class ForwardMain extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		- forward 방식
			1) 특정 서블릿이나 JSP에 대한 요청을 다른 서블릿이나 JSP로 넘겨준다.
			   (이 때 HttpServletRequest객체와 HttpServletResponse객체를 공유하기
			       때문에 파라미터를 넘길 수 있다.)
			2) URL주소는 처음 요청할 때의 주소가 바뀌지 않는다.
			3) 서버 내부에서만 접근이 가능하다.
			
		- 데이터를 전달하는 방법
		  이동되는 페이지로 데이터를 넘기려면 Request객체의 setAttribute()메서드를 
		  이용하여 데이터를 저장하여 보내고, 받는 쪽에서는 getAttribute()메서드를
		  이용하여 데이터를 읽어온다.
		  
		 저장 형식) Request객체.setAttribute("key값", 저장할 데이터);
		 읽기 형식) Request객체.getAttribute("key값);
			-'key값'은 문자열로 지정하고, '저장할 데이터'는 모든 데이터를 사용할 수 있다.
		*/
		request.setAttribute("tel", "010-1111-1111");
		
		// forward방식으로 이동하기
		// 1) Request객체의 getRequestDispatcher()메서드에 이동할 서블릿이나
		//    JSP를 지정해 준다.
		//    (이 때의 경로는 전체 URI경로 중 ContextPath이후의 경로를 지정해 준다.)
		//    예) 이동할 전체 경로가 '/webTest/forwardTarget.do'인 경우에는
		//		'/forwardTarget.do'를 경로로 지정해 준다.
		RequestDispatcher rd = request.getRequestDispatcher("/forwardTarget.do");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

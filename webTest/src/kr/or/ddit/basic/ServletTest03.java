package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
	Servlet 동작 과정...(Servlet의 LifeCycle)
1. 사용자(클라이언트)가 URL을 입력하거나 클릭하면 HTTP 요청(Request)을
	Servlet Container로 전송(요청) 한다.
2. 컨테이너는 web.xml에 정의된 URL 패턴을 확인하여 어느 서블릿을 통해 처리할지를 검색 한다.
	(로딩이 안된 경우에는 해당 서블릿을 로딩한다. 처음 로딩시 init()메서드가 자동으로 호출된다.
	(Servlet 3.0이상에서는 애노테이션으로 설정이 가능하다.)
3. Servlet Container는 개별 요청을 처리할 쓰레드를 생성하여 해당 서블릿 객체의
	service()메서드를 호출한다. (이 때 HttServletRequest객체와 HttpResponse 객체를 
	생성하여 파라미터로 넘겨 준다.)
4. service()메서드는  전송방식(GET, POST등)을 체크하여 적절한 메서드를 호출한다.
	(doGet(), doPost(), doPut(), doDelete()등...)
5. 요청과 요청에 대한 응답이 모두 완료되면 HttpServletRequest객체와
	HttpServletResponse객체는 자동으로 소멸된다.
6. 컨테이너로부터 서블릿이 제거되는 경우에는 destroy()메서드가 호출된다.
*/
@WebServlet("/servletTest03.do")
public class ServletTest03 extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException {
		System.out.println(this.getServletName() + "에서 init()메서드 호출입니다...");
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("service()메서드 시작...");
		
		// GET방식과 POST방식을 구분하여 그에 맞는 메서드 호출하기
		
		// 방법1) 부모 클래스인 HttpServlet의 service()메서드로 위임하기
		//super.service(request, response);
		
		// 방법2) 클라이언트의 전송방식(GET, POST등)을 구분해서 직접 메서드 호출하기
		String method = request.getMethod();  // 전송방식 구하기
		System.out.println("method = " + method);
		
		if("GET".equals(method)) {
			this.doGet(request, response);
		}else {
			this.doPost(request, response);
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doGet()메서드 시작...");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><meta charset='utf-8'></head>"
				+ "<body><h1 style='color:red;'>doGet()메서드의 처리 결과입니다.</h1>"
				+ "</body></html>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doPost()메서드 시작...");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html><head><meta charset='utf-8'></head>"
				+ "<body><h1 style='color:blue;'>doPost()메서드의 처리 결과입니다.</h1>"
				+ "</body></html>");
	}
	
	@Override
	public void destroy() {
		System.out.println(this.getServletName() + "에서 destroy()메서드 호출입니다...");
	}
}

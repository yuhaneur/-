package kr.or.ddit.basic;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 *	서블릿이란 ? ==> 컨테이너에 의해 관리되는 자바 기반 웹 컴포넌트로서 
 *		동적인 웹 컨텐츠 생성을 가능하게 해 준다.
 *  이 예제는 베포 서술자(web.xml)를 이용해서 실행할 Servlet을 설정하는 예제이다.
 */

/*
	- 요청 URL : http://localhost:80/webTest/servletTest01.do
	1) http 		==> 프로토콜 
	2) localhost 	==> 컴퓨터이름(도메인명) 또는 IP주소
	3) 80 			==> 포트번호(80번은 생략 가능)
	4) /webTest 	==> 컨텍스트 패스 (보통 프로젝트 이름으로 지정된다.)
	5) /servletTest01.do ==> 서블릿 요청 URL패턴
*/

// Servelt 클래스는 HttpServlet클래스를 상속해서 작성한다.
public class ServletTest01 extends HttpServlet{
	 
	// 이 영역에서는 service()메서드 또는 doGet()메서드sk doPost()메서드를
	// 재정의 해서 작성한다.
	
	// doGet()메서드나 doPost()메서드는
	// service()메서드를 통해서 자동으로 호출된다.
	
	// 이 메서드들의 매개변수들
	// - HttpServletRequest객체 ==>	서비스 요청에 관련된 정보 및 메서드를 관리하는 객체
	// - HttpServletResponse 객체 ==> 서비스 응답에 관련된 정보 및 메서드를 관리하는 객체
	
	// doGet()메서드 ==> GET방식의 요청을 처리하는 메서드
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = "홍길동";
		
		response.setCharacterEncoding("utf-8"); // 응답 문서의 인코딩 방식 지정
		response.setContentType("text/html; charset=utf-8"); // 응답 문서의 Content Type 설정
		
		// 처리한 내용을 응답으로 보내기 위한 스트림 객체 생성 => PrintWriter객체 생성
		PrintWriter out = response.getWriter();
		
		// 처리한 내용을 출력한다. (HTML형식의 내용을 작성하여 출력한다.)
		
		// 방법1) append()메서드 이용하기
		out.append("<html>")
			.append("<head>")
			.append("<meta charset='utf-8'>")
			.append("<title>첫번째 Servlet 연습</title>")
			.append("</head>")
			.append("<body>")
			.append("<h1 style='text-align:center;'> ")
			.append(name + "씨 안녕하세요. 첫번째 Servlet 프로그램입니다.</h1>")
			.append("</body></html>");
		
	}
	
	// doPost()메서드 ==> POST방식의 요청을 처리하는 메서드
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}

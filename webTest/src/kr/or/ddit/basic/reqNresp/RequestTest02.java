package kr.or.ddit.basic.reqNresp;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestTest02
 */
@WebServlet("/requestTest02.do")
public class RequestTest02 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html; charset=utf-8");
		
		String su1 = request.getParameter("su1");
		int num = Integer.parseInt(su1);
		String su2 = request.getParameter("su2");
		int num1 = Integer.parseInt(su2);
		String calc = request.getParameter("calc");
		double sum=0.0;
		boolean calcOk = true; // 계산이 성공하면 true값으로 셋팅
		if(calc.equals("+")) {
			 sum=num+num1;
		}else if(calc.equals("-")) {
			sum=num-num1;
		}else if(calc.equals("*")) {
			sum=num*num1;
		}else if(calc.equals("/")) {
			if(num1!=0) {
				sum=(double)num/num1;
			}else {
				calcOk=false;
			}
		}else if(calc.equals("%")) {
			if(num1!=0) {
				sum=num%num1;
			}else {
				calcOk=false;
			}
		}
		PrintWriter out = response.getWriter();
		out.println("<html>");
		out.println("<head><meta charset='utf-8'><title>계산 연습</title></head>");
		out.println("<body>");
		
		out.println("<h1>계산 결과</h1>");
		out.println("<hr>");
//		out.println(su1 +calc + su2 +" = "+ sum );
		out.print(su1 +calc + su2 +" = ");
		if(calcOk) {
			out.println(sum);
		}else {
			out.println("계산 불능(0으로 나누기)");
		}
		
		out.println("</body></html>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

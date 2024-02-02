package kr.or.ddit.basic.json;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/lprod/lprodList.do")
public class LprodList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("application/json; charset=utf-8");
		
		PrintWriter out = response.getWriter();
		
		Gson gson = new Gson();
		
		
		// DB에 저장된 자료 가져오기
		ILprodService service = LprodServiceImpl.getInstance();
		
		List<LprodVO> lprodList = service.getAllLprod();
		
		// 가져온 데이터를 JSON문자열로 변환한다.
		String jsonData = gson.toJson(lprodList);
		
		//변환된 JSON문자열을 응답으로 전송한다.
		out.write(jsonData);
		response.flushBuffer();
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

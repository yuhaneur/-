package kr.or.ddit.mymember.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mymember.service.IMemberService;
import kr.or.ddit.mymember.service.MemberServiceImpl;
import kr.or.ddit.mymember.vo.MyMemberVO;

@WebServlet("/memberInsert.do")
public class MemberInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pw = request.getParameter("pw");
		String name=request.getParameter("name");
		String tel = request.getParameter("tel");
		String addr = request.getParameter("addr");
		String file = request.getParameter("file");
		
		MyMemberVO vo = new MyMemberVO();
		vo.setMem_pass(pw);
		vo.setMem_name(name);
		vo.setMem_tel(tel);
		vo.setMem_addr(addr);
		vo.setMem_photo(file);
		
		IMemberService service = MemberServiceImpl.getInstance();
		
		int res = service.insertMember(vo);
		
		request.setAttribute("update", res);
		
		request.getRequestDispatcher("/memberList.do").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

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

@WebServlet("/MemberInfo.do")
public class MemberInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String id = request.getParameter("memid");
		System.out.println(id);
		IMemberService service = MemberServiceImpl.getInstance();
		
		MyMemberVO vo = service.selectMember(id);
		
		request.setAttribute("memvo", vo);
		System.out.println(vo);
		request.getRequestDispatcher("/member/memberInfo.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

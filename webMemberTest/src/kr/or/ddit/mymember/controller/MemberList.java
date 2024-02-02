package kr.or.ddit.mymember.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.or.ddit.mymember.service.IMemberService;
import kr.or.ddit.mymember.service.MemberServiceImpl;
import kr.or.ddit.mymember.vo.MyMemberVO;

@WebServlet("/memberList.do")
public class MemberList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<MyMemberVO> list = new ArrayList<MyMemberVO>();
		IMemberService service = MemberServiceImpl.getInstance();
		
		list = service.selectMemberList();
		
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("/member/memberList.jsp").forward(request, response);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

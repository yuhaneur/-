<%@page import="kr.or.ddit.mymember.vo.MyMemberVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%MyMemberVO vo = (MyMemberVO)request.getAttribute("memvo"); %>
</head>
<body>
<table border='1'>
	<tr>
		<td colspan="3">
			<img alt="" src="">
		</td>
	</tr>
	<tr>
		<td>회원ID</td>
		<td colspan="2"><%=vo.getMem_id()%></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td colspan="2"><%=vo.getMem_pass()%></td>
	</tr>
	<tr>
		<td>회원이름</td>
		<td colspan="2"><%=vo.getMem_name() %></td>
	</tr>
	<tr>
		<td>전화번호</td>
		<td colspan="2"><%=vo.getMem_tel() %></td>
	</tr>
	<tr>
		<td>회원주소</td>
		<td colspan="2"><%=vo.getMem_addr() %></td>
	</tr>
	<tr>
		<td colspan="3">
			<a href="<%=request.getContextPath()%>/memberUpdate.do?memid=<%=vo.getMem_id()%>"><input type="submit" value="수정"></a>
			<a href="<%=request.getContextPath()%>/memberDelete.do"><input type="submit" value="삭제"></a>
			<a href="<%=request.getContextPath()%>/memberList.do"><input type="submit" value="회원목록"></a>
		</td>
	</tr>
</table>
</body>
</html>
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
<form action="<%=request.getContextPath()%>/memberInsert.do">
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
		<td colspan="2">
			<input type="password" name="pw">
		</td>
	</tr>
	<tr>
		<td>회원이름</td>
		<td colspan="2"><input type="text" value="<%=vo.getMem_name() %>" name="name"></td>
	</tr>
	<tr>
		<td>전화번호</td>
		<td colspan="2"><input type="text" value="<%=vo.getMem_tel() %>" name="tel"></td>
	</tr>
	<tr>
		<td>회원주소</td>
		<td colspan="2"><input type="text" value="<%=vo.getMem_addr() %>" name="addr"></td>
	</tr>
	<tr>
		<td>프로필사진</td>
		<td colspan="2"><input type="File" name="file"></td>
	</tr>
	<tr>
		<td colspan="3">
			<input type="submit" value="저장">
			<input type="reset" value="취소">
			<a href="<%=request.getContextPath()%>/memberList.do"><input type="submit" value="회원목록"></a>
		</td>
	</tr>
</table>
</form>
</body>
</html>
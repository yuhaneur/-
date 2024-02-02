<%@page import="kr.or.ddit.mymember.vo.MyMemberVO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
</style>
</head>
<body>
<%List<MyMemberVO> list = (List<MyMemberVO>)request.getAttribute("list");%>

<form action="">
<table border='1'>
	<tr>
		<td colspan="5"><input type="submit" value="회원추가"></td>
	</tr>
	<tr>
		<td>ID</td>
		<td>비밀번호</td>
		<td>이름</td>
		<td>전화</td>
		<td>주소</td>
	</tr>
	<%
		if(list==null || list.size()==0){
	%>
	<tr><td colspan="5">회원 목록이 하나도 없습니다.</td></tr>
	<% 
		}else{	
			for(MyMemberVO vo : list){
	%>
	<tr>
		<td><a href="<%=request.getContextPath()%>/MemberInfo.do?memid=<%=vo.getMem_id()%>"><%=vo.getMem_id()%></a></td>
		<td><%=vo.getMem_pass()%></td>
		<td><%=vo.getMem_name()%></td>
		<td><%=vo.getMem_tel()%></td>
		<td><%=vo.getMem_addr()%></td>
	</tr>
	<% 
			}
		}
	%>
</table>
</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% String uId="";
uId =  (String)session.getAttribute("userID");
if(uId==null||uId.length()==0){
	uId="";
}
%>
<form action="/webTest/sessionLogin.do">
	<table border='1'>
		<tr>
			<td>ID :</td>
			<td><input name="id" type="text" placeholder="ID를 입력하세요." value="<%= uId%>"></td>
		</tr>
		<tr>
			<td>PASS :</td>
			<td><input name="pw"type="password" placeholder="PASSWORD를 입력하세요"></td>
		</tr>
		<tr>
			<td colspan="2"><input type="submit"  value="login"></td>
		</tr>
	</table>
</form>
</body>
</html>
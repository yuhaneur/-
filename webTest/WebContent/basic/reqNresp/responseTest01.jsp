<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>forward, redirect 방식 연습</h2><hr>
<form method="post" action="<%=request.getContextPath() %>/forwardMain.do">
	forward 방식 : <input type="text" name="username">
	<input type="submit" value="확인">
</form>

<br><hr><br>

<form method="post" action="<%=request.getContextPath() %>/redirectMain.do">
	redirect 방식 : <input type="text" name="username">
	<input type="submit" value="확인">
</form>
</body>
</html>
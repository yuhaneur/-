<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">
$(()=>{
	
	
})
</script>
<style type="text/css">
	label{
		display: inline-block;
		width: 50px;
	}
</style>
</head>
<body>
<form action="/webTest/cookieLoginServlet.do">
	<label>ID :</label>
	<input type="text" name ="id" id="id" placeholder="ID 입력하세요.">
	<br>
	<label>PASS :</label>
	<input type="text" name="pass" id="pass" placeholder="PassWord 입력하세요.">
	<br>
	<input type="checkbox" name="chkBox">id 기억하기
	<br>
	<input type="submit" value="Login">
	<%
		String idv = (String)request.getAttribute("idVal");
	%>
	<%= idv %>
</form>
</body>
</html>
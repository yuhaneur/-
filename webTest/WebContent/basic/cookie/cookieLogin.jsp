<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="../js/jquery-3.7.1.min.js"></script>
<script type="text/javascript">

</script>
<style type="text/css">
	label{
		display: inline-block;
		width: 50px;
	}
</style>
</head>
<body>
<%
// 쿠키값(userId)가 저장될 변수 선언
String cookieUserID = "";

// checkbox 체크 여부를 나타낼 변수 선언
String chk = "";

// 쿠키 정보 가져오기
Cookie[] cookieArr = request.getCookies();
if(cookieArr !=null){
	for(Cookie cookie : cookieArr){
		// 쿠키이름이 "id"인 쿠키의 쿠키값 구하기
		if("id".equals(cookie.getName())){
			cookieUserID= cookie.getValue();
			chk="checked";
			break;
		}
	}
}
%>
<form action="/webTest/cookieLoginServlet.do">
	<label>ID :</label>
	<input type="text" name ="id" id="id" placeholder="ID 입력하세요."value="<%=cookieUserID%>">
	<br>
	<label>PASS :</label>
	<input type="text" name="pass" id="pass" placeholder="PassWord 입력하세요." >
	<br>
	<input type="checkbox" name="chkBox" <%=chk%>>id 기억하기
	<br>
	<input type="submit" value="Login">
</form>
</body>
</html>
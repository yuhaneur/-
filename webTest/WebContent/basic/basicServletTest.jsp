<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	window.onload = function(){
		document.getElementById("getBtn").addEventListener("click",function(){
			location.href = "http://localhost/webTest/servletTest03.do";
		})
	}
</script>
</head>
<body>
	<h2>Servlet 요청 연습</h2><hr><br>
	
	<h3>GET방식 요청하기 1 ==> 링크 방식</h3>
	<a href="http://localhost/webTest/servletTest03.do">Get방식 요청1</a>
	<hr>
	
	<h3>GET방식 요청하기 2 ==> form태그의 method속성 이용 </h3>
	<!--  
		<form>태그의 method속성이 생략되거나 method='GET'으로 설정하면 
		GET방식으로 요청 된다.
	-->
	<form action="http://localhost/webTest/servletTest03.do" method="get">
		<input type="submit" value="Get방식 요청2">
	</form>
	
	<hr>
	
	<h3>GET방식 요청하기 3 ==> JavaScript의 location.href속성 이용하기</h3>
	<form>
		<input type="button" value="Get방식 요청3" id="getBtn">
	</form>
	
	<hr>
	<h3>POST방식 요청하기 ==> form태그의 method속성에 속성값을 post로 설정</h3>
	<form action="http://localhost/webTest/servletTest03.do" method="post">
		<input type="submit" value="Post방식 요청">
	</form>
	
	
</body>
</html>
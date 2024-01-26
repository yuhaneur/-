<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<a href="<%=request.getContextPath()%>/cookieCountServlet.do">Cookie Count 증가하기</a>


<a href="<%=request.getContextPath()%>/cookieDelServlet.do">Cookie Count 초기화 하기</a>
</body>
</html>
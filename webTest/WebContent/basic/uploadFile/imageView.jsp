<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	img{
		width: 200px;
		height: 200px;
		
	}
</style>
</head>
<body>
<img alt="" src="../images/원숭이.png"><br><br>
<img alt="" src="<%=request.getContextPath()%>/basic/images/원숭이.png"><br><br>
<img alt="" src="<%=request.getContextPath()%>/images/imageView.do?fileno=6"><br><br>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>file upload form</title>
</head>
<body>
<h2>File Upload 연습 Form</h2>
<!-- 
	파일을 업로드할 <form>태그에는 enctype='multipart/form-data' 속성과
	method='post'속성이 반드시 있어야 한다. 
 -->
<form method="post" enctype="multipart/form-data" action="<%=request.getContextPath()%>/fileUpload.do">
작성자 이름 : <input type="text" name="username"><br><br>
한 개의 파일 선택 : <input type="file" name="upFile1"><br><br>
여러개 개의 파일 선택 : <input type="file" name="upFile2" multiple><br><br>
<input type="submit" value="전 송">
</form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 
	HTML 주석
 -->
 <%--
 	이것은 JSP 주석을 나타냅니다...
  --%>
  <%
  	// 이영역은 JSP문서에서 JAVA명령을 사용할 수 있는 영역으로
  	// '스크립트릿'이라고 한다.
  	String name = "홍길동";
  %>
  <%-- 
  <%= 수식이나 변수명 %> ==> JSP문서에서 변수나 수식의 결과를 출력할 때 사용한다.
  				   ==> '표현식(Expression)'이라고 한다. 
  --%>
  <!-- 
  	<form>태그의 속성
  	1) action => <form>에서 만들어진 데이터를 받아서 처리할 문서명 또는 서블릿 URL주소
  	2) method => 전송방식(GET 또는 POST) ==> 기본값 :GET
  	3) target => 응답이 나타날 프레임 지정
  	4) enctype => 서버로 파일을 전송할 때 사용하는 속성으로 속성값은
  				 'multipart/form-data'로 지정한다.
   -->
  
  <h2>Request연습용 Form</h2>
  <form action="/webTest/requestTest01.do" method="GET">
  	<table border="1">
  		<tr>
  			<td>이름</td>
  			<td><input type="text" name="userName"></td>
  		</tr>

  		<tr>
  			<td>직 업</td>
  			<td>
  			  <select name="job">
  				<option value="회사원">회사원</option>
  				<option value="전문직">전문직</option>
  				<option value="백수">백수</option>
  				<option value="학생">학생</option>
  			  </select>
  			</td>
  		</tr>

  		<tr>
  			<td>취 미</td>
  			<td>
  				<input type="checkbox" name="hobby" value="독서">독서
  				<input type="checkbox" name="hobby" value="음악">음악
  				<input type="checkbox" name="hobby" value="산책">산책
  				<input type="checkbox" name="hobby" value="운동">운동
  				<input type="checkbox" name="hobby" value="수영">수영
  			</td>
  		</tr>
  		<tr>
  			<td colspan="2" style="text-align:center;">직 업
  			<input type="submit" value= "전송">
  			<input type="reset" value="취소">
  			</td>
  		</tr>
  	</table>
  </form>
</body>
</html>
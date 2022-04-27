<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>SessionScope</h2>
	<%
		//SessionScope session내장객체
		session.setAttribute("email", "hong@gmail.com");
		//requestScope
		request.setAttribute("email", "java@gmail.com");
	%>
	<h3>jsp표현식</h3>
	이메일 : <%=session.getAttribute("email") %> <br>
	
	<h3>EL</h3>
	이메일 : ${sessionScope.email} <br>
	
	<h3>EL(생략가능)</h3>
	${email} <!-- requestScope가 중복으로 있으면 requestScope를 가져온다 -->
	
</body>
</html>
<%@page import="dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Member member = new Member("java","1111","김자바","java@gmail.com");
		request.setAttribute("member", member);
	%>
	<h2>jsp표현식</h2>
	<%=((Member)request.getAttribute("member")).getUserid() %> <br>
	<%=((Member)request.getAttribute("member")).getPasswd() %> <br>
	<%=((Member)request.getAttribute("member")).getName() %> <br>
	<%=((Member)request.getAttribute("member")).getEmail() %> <br>
	
	<h2>El</h2>
	${member.userid} <br> <!-- 내부적으로 위와같이 작동 게터없으면 작동안됨 -->
	${member.passwd} <br>
	${member.name} <br>
	${member.email} <br>
	
</body>
</html>
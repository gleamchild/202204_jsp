<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>jsp파라미터</h2>
	아이디 : <%=request.getParameter("userid") %> <br>
	이름 : <%=request.getParameter("name") %>
	<hr>
	<h2>EL파라미터</h2>
	${param.userid} <br>
	${param.name}
	
</body>
</html>
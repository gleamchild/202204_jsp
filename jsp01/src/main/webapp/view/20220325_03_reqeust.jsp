<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>request객체</h2>
	요청프로토콜 : <%=request.getProtocol()%> <br>
	요청메서드방식:<%=request.getMethod()%> <br>
	현재페이지경로(contextPath) : <%=request.getContextPath() %><br>
	요청url(주소) : <%=request.getRequestURL() %> <br>
	
</body>
</html>
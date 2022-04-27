<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>결과창</h2>
	
	<%-- <%
		int age = (int)request.getAttribute("age");
		String grade = (String)request.getAttribute("grade");
	%> --%>
	당신은 <%=request.getAttribute("age") %>살 <br>
	<%=request.getAttribute("grade") %> 입니다.
	
</body>
</html>
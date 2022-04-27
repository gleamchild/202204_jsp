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
		//post방식일때 request객체의 인코딩을 utf-8로 
		request.setCharacterEncoding("UTF-8");
		String favColor = request.getParameter("favColor");
	%>
	
	가장좋아하는 컬러 : <%=favColor%>
	
</body>
</html>
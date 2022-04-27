<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>사각형의 넓이 결과창</h2>
	<%
		//기본형간의 형변환
		int a = (int)3.14;
	
		//참조형간의 형변환
		Object obj = "aaa";
		String s = (String)obj;
		
		
		//참조형 -> 기본형 
		//래퍼클래스를 이용 형변환 : String -> int
		int width = Integer.parseInt(request.getParameter("width"));
		int height = Integer.parseInt(request.getParameter("height"));
	%>
	사각형의 넓이 : <%=width*height%>
	
</body>
</html>
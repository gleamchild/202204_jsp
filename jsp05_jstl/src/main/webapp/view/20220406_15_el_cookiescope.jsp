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
		//쿠키 읽기
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(Cookie cookie:cookies){
	%>
				<%=cookie.getName() %> <br>	
				<%=cookie.getValue() %> <br>
	<%		}
		}
	%>
	<h2>cookie</h2>
	${cookie.JSESSIONID.value}	
	
</body>
</html>
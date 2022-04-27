<%@page import="java.net.URLEncoder"%>
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
		//msg : 학과의 계열이 무엇인지 메세지 출력
		String name = request.getParameter("sname");
		String major = request.getParameter("major");
		String prefix = major.substring(0, 1);
		String msg;
		
		if(prefix.equals("A")){
			msg = "공학계열";
		}else if(prefix.equals("B")){
			msg = "자연계열";
		}else{
			msg = "계열 없음";
		}
		
		//자바의 인코딩과 url인코딩이 다르다
		name = URLEncoder.encode(name, "utf-8");
		msg = URLEncoder.encode(msg, "utf-8");
		response.sendRedirect("20220328_15_result.jsp?name="+name+"&msg="+msg);
	%>
</body>
</html>
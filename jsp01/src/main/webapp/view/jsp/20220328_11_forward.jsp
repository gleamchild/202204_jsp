<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>forward방식 이동</h2>
	<%
		String name = "hong gil dong";	
		int age = 25;
		//모든형을 전송가능(대량정보 전송 가능)
		request.setAttribute("name", name); //request객체에 정보를 담는다
		request.setAttribute("age", age); //(String(변수와 짝지을 키), Object(값이 들어있는 변수))
		
		//forward 이동
		//특징 : 경로변경이 안된다 - 불러온 곳에서 결과창이 뜬다
		//1)이동경로의 정보획득
		RequestDispatcher rd = request.getRequestDispatcher("20220328_11_result.jsp");
		//2)forward
		rd.forward(request, response);
	
	
	%>
</body>
</html>
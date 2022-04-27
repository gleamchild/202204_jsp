<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>요청 parameter 정보(get)</h2>
	<!-- 전송 메서드 : get방식 (데이터 전송을 url에 담아서)
				  post방식 (request내부 객체에 담아서) -->
	<form action="">
		이름 : <input type="text" name="name"> <br>	
		나이 : <input type="number" name="age"> <br>
		<!-- submit기능 : action값:url(공백이면 자신의 폼요청)
		form안의 name의 정보를 request(get(url에 포함해서),post(객체에 담아)) 보낸다(서버가 받는다) -->
		<button>전송</button>
	</form>
	
	<%
		//스크립틀릿(servlet):서버 - 서버에서 동작
		String name = request.getParameter("name");
		String age = request.getParameter("age");
	%>
	이름 : <%=name %><br>
	나이 : <%=age %><br>
	
</body>
</html>
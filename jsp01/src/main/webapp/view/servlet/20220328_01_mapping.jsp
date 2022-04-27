<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>서블릿 매핑(디렉토리 패턴)</h2>
	<!-- contextpath + webservlet의 매핑정보 -->
	<!-- 절대경로 : contextpath부터 적어야 한다 : /jsp01/J20220328_01 -->
	<!-- 상대경로 : J20220328_01 -->
	<form action="/jsp01/J20220328_01" method="post">
		이름 : <input type="text" name="name"> <br>
		나이 : <input type="number" name="age"> <br>
		<button>전달</button>
	</form>
	이름 : <%=request.getAttribute("name") %><br>
	나이 : <%=request.getParameter("age") %>
</body>
</html>
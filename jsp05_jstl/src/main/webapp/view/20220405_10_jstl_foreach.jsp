<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>배열변수 출력</h2>
	<!-- 배열변수 만들기 -->
	<c:set var="arr">one,two,three,four,five</c:set>
	${arr}
	<hr>
	<!-- items - 풀어쓸 배열, varStatus - 속성값 -->
	<c:forEach var="eng" items="${arr}" varStatus="status">
		${status.index} ${status.count} ${eng}<br>
	</c:forEach>
</body>
</html>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@include file="header.jsp" %>
	<section>
		<h2 align="center">회원매출조회</h2>
		<table>
			<tr>
				<th>회원번호</th>
				<th>회원성명</th>
				<th>고객등급</th>
				<th>매출</th>
			</tr>
	<%
		List<Map<String, Object>> slist = (List<Map<String, Object>>)request.getAttribute("slist");
		if(slist != null){
			for(Map<String, Object> map:slist){
	%>
				<tr>
					<td><%=map.get("custno") %></td>
					<td><%=map.get("custname") %></td>
					<td><%=map.get("grade") %></td>
					<td><%=map.get("price") %></td>
				</tr>
	<%		}
		}
	%>
		</table>
	</section>
	<%@include file="footer.jsp" %>
</body>
</html>
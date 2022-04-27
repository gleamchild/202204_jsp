<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includefile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="../header.jsp" %>
	<h2>회원조회</h2>
	<form action="${path}/list.member">
		<select name="findkey">
			<option value="userid">아이디</option>
			<option value="addrload">도로명주소</option>
			<option value="regidate">등록일자</option>
		</select>
		<input type="text" name="findvalue">
		<button>조회</button>
	</form>
	
	<table border="1">
		<tr>
			<th>순번</th>
			<th>아이디</th>
			<th>우편번호</th>
			<th>도로명주소</th>
			<th>상세주소</th>
			<th>등록일자</th>
		</tr>
		<c:forEach var="member" items="${mlist}" varStatus="status">
			<tr>
				<td>${status.count}</td>
				<td><a href="${path}/modiform.member?userid=${member.userid}">${member.userid}</a></td>
				<td>${member.zipcode}</td>
				<td>${member.addrload}</td>
				<td>${member.addrdetail}</td>
				<td><fmt:formatDate value="${member.regidate}" pattern="yyyy년 MM월 dd일"/></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
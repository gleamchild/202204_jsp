<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
</script>
</head>
<body>
	<h2>댓글창</h2>
	<form action="${path}/reply/add" method="post">
		게시물번호 : <input type="text" name="bnum" value="${param.bnum}"> <br>
		<input type="hidden" name="restep" value="${param.restep}">
		<input type="hidden" name="relevel" value="${param.relevel}">
		<hr>
		댓글 <textarea rows="5" cols="20" name="content"></textarea>
		<button>추가</button>
	</form>
</body>
</html>
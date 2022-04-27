<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function modifyCheck(e){
		e.preventDefault(); //기본 이벤트 제거
		const content = document.getElementById('content');
		if(content.value.trim() == ''){
			alert('내용을 입력해 주세요!');
			return;
		}
		document.getElementById('frmReplyModify').submit();
	}
</script>
</head>
<body>
	<h2>댓글 수정창</h2>
	<form name="frmReplyModify" action="${path}/reply/modify" method="post">
		<input type="hidden" name="bnum" value="${reply.bnum}">
		댓글번호 : <input type="text" name="rnum" value="${reply.rnum}" readonly> <br>
		<hr>
		댓글 <textarea rows="5" cols="20" name="content" id="content">${reply.content}</textarea>
		<button onclick="modifyCheck(event)">수정</button>
	</form>
</body>
</html>
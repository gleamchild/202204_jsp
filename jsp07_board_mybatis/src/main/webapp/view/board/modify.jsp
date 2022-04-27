<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>수정폼</h2>
	<h5>번호 : ${board.bnum}</h5>
	<form name="frmboard" action="${path}/board/modify" method="post" enctype="multipart/form-data">
	<input type="hidden" name="bnum" value="${board.bnum}">
		<table>
			<tr>
				<th>제목</th>
				<td><input type="text" name="subject" value="${board.subject}"></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><input type="text" name="userid" value="${board.userid}"></td>
			</tr>
			<tr>
				<th>조회수</th>
				<td><input type="text" value="${board.readcnt}" readonly></td>
			</tr>
			<tr>
				<th>등록일자</th>
				<td> <fmt:formatDate value="${board.regidate}" pattern="yyyy. MM.dd"/> </td>
			</tr>
			<tr>
				<th>내용</th>
				<td><textarea name="content" rows="5" cols="25">${board.content}</textarea></td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td> 
					<c:forEach var="boardfile" items="${bflist}">
						 ${boardfile.filename} <input type="checkbox" name="removefile" value="${boardfile.bfnum}">삭제 <br>
					</c:forEach>
					<hr> 
					<input type="file" name="file1"> <br>
					<input type="file" name="file2"> <br>
					<input type="file" name="file3"> <br>
					<input type="file" name="file4"> <br>
					<input type="file" name="file5"> <br>
				</td>
			</tr>
			<tr>
				<td colspan="2" align="center"> 
					<button>수정</button> 
					<button type="reset">취소</button> 
				</td>
			</tr>
		</table>
	</form>
</body>
</html>
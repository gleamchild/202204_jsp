<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includeFile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.reply{
		display:flex;
	}
</style>
<script type="text/javascript">
	//게시물삭제 - bnum은 이 페이지에서 유일
	function removeCheck(){
		const result = confirm('게시물을 삭제 하시겠습니까?');
		if(result) location.href='${path}/board/remove?bnum=${board.bnum}';
	}
	//댓글삭제 - rnum은 댓글에 따라 달라지므로 매개변수로 넘겨줘야 한다
	function removeReply(rnum){
		const result = confirm('댓글을 삭제 하시겠습니까?');
		if(result) location.href='${path}/reply/remove?bnum=${board.bnum}&rnum='+rnum;
	}
</script>
<script src="https://kit.fontawesome.com/d6574640f6.js" crossorigin="anonymous"></script>
</head>
<body>
	<h2>상세조회</h2>
	<h5>번호 : ${board.bnum}</h5>
	<table border="1">
		<tr>
			<th>제목</th>
			<td><input type="text" value="${board.subject}"></td>
		</tr>
		<tr>
			<th>작성자</th>
			<td><input type="text" value="${board.userid}"></td>
		</tr>
		<tr>
			<th>조회수</th>
			<td><input type="text" value="${board.readcnt}"></td>
		</tr>
		<tr>
			<th>등록일자</th>
			<td> <fmt:formatDate value="${board.regidate}" pattern="yyyy. MM.dd"/> </td>
		</tr>
		<tr>
			<th>내용</th>
			<td><pre>${board.content}</pre></td>
		</tr>
		<tr>
			<th>첨부파일</th>
			<td> 
				<c:forEach var="boardfile" items="${bflist}">
					<a href="${path}/file/download?filename=${boardfile.filename}">${boardfile.filename}</a><br>
				</c:forEach> 
			</td>
		</tr>
		<tr>
			<td colspan="2" align="center"> 
				<button onclick="location.href='${path}/board/modiform?bnum=${board.bnum}'">수정하기</button> 
				<button onclick="removeCheck()">삭제</button> 
				<button onclick="location.href='${path}/view/board/replyadd.jsp?bnum=${board.bnum}&restep=0&relevel=0'">댓글창</button> 
				<button onclick="location.href='${path}/board/list'">목록</button>
			</td>
		</tr>
	</table>
	<hr>
	<c:forEach var="reply" items="${rlist}">
		<div class="reply">
			<c:forEach var="i" begin="1" end="${reply.relevel}">
				<div>
					<i class="fa-brands fa-replyd"></i>
				</div>
			</c:forEach>
			<div>
				번호:${reply.rnum}<br>
				${reply.content} <fmt:formatDate value="${reply.regidate}" pattern="HH:mm:ss"/> <br>
				<button onclick="location.href='${path}/view/board/replyadd.jsp?bnum=${board.bnum}&restep=${reply.restep}&relevel=${reply.relevel}'">댓글</button>
				<button onclick="location.href='${path}/reply/modiform?rnum=${reply.rnum}'">수정</button>
				<button onclick="removeReply(${reply.rnum})">삭제</button>
			</div>
		</div>
		<hr>
	</c:forEach>
</body>
</html>
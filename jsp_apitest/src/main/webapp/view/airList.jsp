<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value='${pageContext.request.contextPath}'/>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	var[] districtName = {"서울", "부산", "대구", "인천", "광주", "대전", "울산", "세종", "경기",
			 				"강원", "충북", "충남", "전북", "전남", "경북", "경남", "제주"};
	if('${param.msg}' != ''){
		alert('${param.msg}');
	}
</script>
</head>
<body>
	<h2>미세먼지 경보 발령 현황</h2>
	<form action="${path}/add.air">
		연도:<input type="month" name="year">
		<select name="itemCode">
			<option>PM10</option>
			<option>PM25</option>
		</select>
		<button>db저장</button>
	</form>
	<hr>
	<form action="${path}/selectList.air">
		<select name="districtName">
			<c:forEach var="i" begin="0" end="16" step="1">
				<option>${districtname.get(i)}</option>
			</c:forEach>
			<%-- <option ${alist.get(0).districtName=='서울'?'selected':''}>서울</option>
			<option ${alist.get(0).districtName=='부산'?'selected':''}>부산</option>
			<option ${alist.get(0).districtName=='대구'?'selected':''}>대구</option>
			<option ${alist.get(0).districtName=='인천'?'selected':''}>인천</option>
			<option ${alist.get(0).districtName=='광주'?'selected':''}>광주</option>
			<option ${alist.get(0).districtName=='대전'?'selected':''}>대전</option>
			<option ${alist.get(0).districtName=='울산'?'selected':''}>울산</option>
			<option ${alist.get(0).districtName=='세종'?'selected':''}>세종</option>
			<option ${alist.get(0).districtName=='경기'?'selected':''}>경기</option>
			<option ${alist.get(0).districtName=='강원'?'selected':''}>강원</option>
			<option ${alist.get(0).districtName=='충북'?'selected':''}>충북</option>
			<option ${alist.get(0).districtName=='충남'?'selected':''}>충남</option>
			<option ${alist.get(0).districtName=='전북'?'selected':''}>전북</option>
			<option ${alist.get(0).districtName=='전남'?'selected':''}>전남</option>
			<option ${alist.get(0).districtName=='경북'?'selected':''}>경북</option>
			<option ${alist.get(0).districtName=='경남'?'selected':''}>경남</option>
			<option ${alist.get(0).districtName=='제주'?'selected':''}>제주</option> --%>
		</select>
		<button>조회</button>
	</form>
	<p></p>
	<table border="1"> 
		<tr>
			<th>일련번호</th>
			<th>발령일</th>
			<th>지역명</th>
			<th>권역명</th>
			<th>항목명</th>
			<th>경보단계</th>
			<th>발령시간</th>
			<th>발령농도</th>
			<th>해제일</th>
			<th>해제시간</th>
			<th>해제농도</th>
		</tr>
		<c:forEach var="air" items="${alist}">
			<tr>
				<td>${air.sn}</td>
				<td><fmt:formatDate value="${air.dataDate}" pattern="yyyy-MM-dd"/></td>
				<td>${air.districtName}</td>
				<td>${air.moveName}</td>
				<td>${air.itemCode}</td>
				<td>${air.issueGbn}</td>
				<td>${air.issueTime}</td>
				<td>${air.issueVal}</td>
				<td><fmt:formatDate value="${air.clearDate}" pattern="yyyy-MM-dd"/></td>
				<td>${air.clearTime}</td>
				<td>${air.clearVal}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
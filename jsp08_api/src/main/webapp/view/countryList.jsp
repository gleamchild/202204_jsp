<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<% request.setCharacterEncoding("UTF-8");%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value='${pageContext.request.contextPath}'/> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	if('${param.msg}' != ''){
		alert('${param.msg}');
	}
	
	//파싱후 db저장
	function parsingSave(e){
		e.preventDefault();
		frmCountry.action = '${path}/dbsave.country'
		frmCountry.submit();
	}
</script>
</head>
<body>
	<h2>국가별 최신 안전소식</h2>
	<form name="frmCountry" action="${path}/list.country">
		국가
		<select name="iso">
			<c:forEach var="isomap" items="${isolist}">
					<option value="${isomap.CODE}"  ${param.iso==isomap.CODE?'selected':''}>${isomap.NAME}</option>
			</c:forEach>
			<!-- <option value="US">미국</option>
			<option value="JP">일본</option>
			<option value="GB">영국</option>
			<option value="DE">독일</option> -->
		</select>
		<button>조회</button>
		<button onclick="parsingSave(event)">파싱후db저장</button>
	</form>
	<%-- ${clist} --%>
	<c:forEach var="country" items="${clist}">
		국가 : ${country.country_eng_nm}<br>
		날짜 : ${country.wrt_dt}<br>
		제목 : <a href="${path}/detail.country?sfty_notice_id=${country.sfty_notice_id}">${country.title}</a><br>
		<%-- 내용 : ${country.txt_origin_cn} --%>
		<hr>
	</c:forEach>
		댓글 : <input type="text" name="reply">
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
	
	// 중복되는 유효성 체크 함수 따로 빼서 만들기
	function valueCheck() {
		const startDt = frmList.startDt;
		const endDt = frmList.endDt;
		console.log(startDt);
		console.log(endDt);
		if (startDt.value==''){
			alert('시작일을 입력해 주세요!');
			startDt.focus();
			return false; //유효성 체크 실패
		}else if(endDt.value==''){
			alert('종료일을 입력해 주세요!');
			endDt.focus();
			return false;
		}
		return true; //유효성 체크 성공
	}
	
	function listCheck(e){
		e.preventDefault();
		if(valueCheck()){
			frmList.action = '${path}/list.covid';
			frmList.submit();
		}
	}

	function parsingSave(e){
		e.preventDefault();
		if(valueCheck()){
			frmList.action = '${path}/dbsave.covid';
			frmList.submit();
		}
	}
</script>
</head>
<body>
	<h2>코로나 확진자 현황</h2>
	<form name="frmList" action="${path}/list.covid">
		기간 <input type="date" name="startDt" value="${param.startDt}"> ~
				<input type="date" name="endDt" value="${param.endDt}">
		<button onclick="listCheck(event)">조회</button>
		<button onclick="parsingSave(event)">파싱후db저장</button>
	</form>
	<hr>
	<c:forEach var="covid" items="${covidList}">
		날짜 : ${covid.createDt} <br>
		총 확진자 : ${covid.decideCnt} <br>
		일일 확진자 : ${covid.dailyDecideCnt} <br>
		사망자 : ${covid.deathCnt} <br>
		일일 사망자 : ${covid.dailyDeathCnt}
		<hr>
	</c:forEach>
</body>
</html>
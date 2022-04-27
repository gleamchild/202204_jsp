<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../include/includefile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function goPopup(){
		// 주소검색을 수행할 팝업 페이지를 호출합니다.
		// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://www.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
		var pop = window.open("${path}/member/jusoPopup.jsp","pop","width=570,height=420, scrollbars=yes, resizable=yes"); 
	}
	//주소팝업 호출 후 실행할 함수
	function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
		// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
		document.frmModify.addrload.value = roadAddrPart1;
		document.frmModify.addrdetail.value = addrDetail;
		document.frmModify.zipcode.value = zipNo;
	}
	
	
	
</script>
</head>
<body>
<%@ include file="../header.jsp" %>
	<h2>회원정보 수정</h2>
	<form name="frmModify" action="${path}/modify.member" method="post" enctype="multipart/form-data">
		<table border="1">
			<tr>
				<th>아이디</th>			
				<td> <input type="text" name="userid" value="${member.userid}" readonly> </td>			
			</tr>
			<tr>
				<th>기존비밀번호</th>			
				<td> <input type="password" name="passwd"> </td>			
			</tr>
			<tr>
				<th>새로운비밀번호</th>			
				<td> <input type="password" name="newpasswd"> </td>			
			</tr>
			<tr>
				<th>우편번호</th>			
				<td> 
					<input type="text" name="zipcode" size="5" value="${member.zipcode}"> 
					<button type="button" onclick="goPopup()">검색</button>
				</td>			
			</tr>
			<tr>
				<th>도로명주소</th>			
				<td> <input type="text" name="addrload" size="30" value="${member.addrload}"> </td>			
			</tr>
			<tr>
				<th>상세주소</th>			
				<td> <input type="text" name="addrdetail" value="${member.addrdetail}"> </td>			
			</tr>
			<tr>
				<th>사진</th>			
				<td> 
					<img alt="사진없음" src="/savedir/${member.filename}" width="150px">
					<input type="hidden" name="filename" value="${member.filename}">
					${member.filename}
					<hr>
					<input type="file" name="photo"> 
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
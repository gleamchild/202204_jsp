<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="./include/includefile.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	//엔터입력으로 로그인
	function enterkey(){
		if(window.event.keyCode!=13) return;
		loginCheck();
	}

	function loginCheck(){
		const userid = frmLogin.userid;
		const passwd = frmLogin.passwd;
		
		if(userid.value ==''){
			alert('아이디를 입력하세요');
			userid.focus();
		}else if(passwd.value == ''){
			alert('비밀번호를 입력하세요');
			passwd.focus();
		}else{
			frmLogin.action = '${path}/login.member';
			frmLogin.method = 'post';
			frmLogin.submit();
		}
	}
</script>
	
</head>
<body>
<%@ include file="./header.jsp" %>
	<h2>로그인</h2>
	<form name="frmLogin">
		<fieldset>
			아이디 <input type="text" name="userid" value="${cookie.userid.value}"> <br>
			비밀번호 <input type="password" name="passwd" onkeydown="enterkey()"> <br>
			아이디기억하기 
			<!-- cookie.userid.value가 null이라면 -->
			<c:out value="${empty cookie.userid.value?'':'checked'}"/>
			<input type="checkbox" name="idsave" checked> <br>
			<button type="button" onclick="loginCheck()">로그인</button> <button type="reset">취소</button>
		</fieldset>	
	</form>
</body>
</html>
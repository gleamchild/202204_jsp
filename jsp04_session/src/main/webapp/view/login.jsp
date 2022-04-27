<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script type="text/javascript">
	if('<%=request.getParameter("msg")%>' != 'null'){
		alert('<%=request.getParameter("msg")%>');
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
			frmLogin.action = '/jsp04_session/login.log';
			frmLogin.method = 'post';
			frmLogin.submit();
		}
	}
	
</script>
	
</head>
<body>
	<%
		//쿠키 읽기
		String userid = "";
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(Cookie cookie:cookies){
				if(cookie.getName().equals("userid")){
					userid = cookie.getValue();
				}
			}
		}

	%>
	<h2>로그인</h2>
	<form name="frmLogin">
		<fieldset>
			아이디 <input type="text" name="userid" value="<%=userid %>"> <br>
			비밀번호 <input type="password" name="passwd"> <br>
			아이디기억하기 <input type="checkbox" name="idsave" checked> <br>
			<button type="button" onclick="loginCheck()">로그인</button> <button type="reset">취소</button>
		</fieldset>	
	</form>
</body>
</html>
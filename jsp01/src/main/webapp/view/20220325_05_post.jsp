<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>데이터 전송(post)</h2>
	<!-- form default전송방식 - get 생략가능 
							   post : request객체에 담아서 전송
	-->
	<form action="" method="post">
		아이디 : <input type="text" name="userid"> <br>
		비밀번호 : <input type="password" name="passwd"> <br>
		<button>로그인</button>
	</form>
	<%
	String userid = request.getParameter("userid");
	
		if(userid!=null){
	%>
		<fieldset>
			<legend>로그인 정보</legend>
			아이디 : <%=userid %> <br>
			비밀번호 : <%=request.getParameter("passwd") %> <br>
		</fieldset>
	<%
		}
	%>
</body>
</html>
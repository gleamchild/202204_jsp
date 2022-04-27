<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 주말 과제 -->
	
	<!-- 입력항목
	아이디, 비밀번호, 이메일, 이름, 성별(radio), 가입경로(select), 
	알바가능시간(checkbox):오전,오후,저녁,야간 
	
	결과창
	버튼 눌럿을때 결과창에 가입정보 출력
	-->
	
	<h2>회원가입</h2>
	<form action="">
		<fieldset>
			<legend>회원정보</legend>
			아이디 <input type="text" name="userid"> <br>
			비밀번호 <input type="password" name="passwd"> <br>
			이메일 <input type="email" name="email"> <br>
			이름 <input type="text" name="username"> <br>
			성별 <input type="radio" name="sex" value="man">남자 <input type="radio" name="sex" value="women">여자 <br>
			가입경로<select>
						
					</select> <br>
			선호시간  <br>
		</fieldset>
	</form>
	
</body>
</html>
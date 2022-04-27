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
<!--입력항목 
 	아이디, 비밀번호, 이메일, 이름, 성별(radio), 가입경로(select), 
	알바가능시간대(checkbox):새벽,오전,오후,저녁
	
	버튼을 눌렀을때 결과창에 가입정보 출력 
 -->	  
	<h2>회원가입</h2>
	
	<form action="20220325_10_result.jsp" method="post">
		<fieldset>
			<legend>회원정보</legend>
			아이디 <input type="text" name="userid"> <br>
			비밀번호 <input type="password" name="passwd"><br>
			이메일 <input type="email" name="email"><br>
			이름 <input type="text" name="username"><br>
			성별 <input type="radio" name="sex" value="남자">남자 <input type="radio" name="sex" value="여자">여자<br>
			가입경로 <select name="signuppath">
						<option>SNS광고</option>
						<option>웹페이지 광고</option>
						<option>지인추천</option>
					</select> <br>
			알바 가능 시간대 <input type="checkbox" name="wtime" value="새벽">새벽
			 				 <input type="checkbox" name="wtime" value="오전">오전
			 				 <input type="checkbox" name="wtime" value="오후">오후
			 				 <input type="checkbox" name="wtime" value="저녁">저녁
			 				 <input type="checkbox" name="wtime" value="야간">야간
		</fieldset>
		<button>회원등록</button>
		<button type="reset">취소</button>
	</form>
</body>
</html>


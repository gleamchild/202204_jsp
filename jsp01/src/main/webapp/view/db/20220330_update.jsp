<%@page import="s02_member.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
	<% 
		Member member = (Member)request.getAttribute("member"); 
	%>
<script type="text/javascript">
	
	function updateCheck(){
		var userid = document.getElementById("userid");
		var passwd = document.getElementById("passwd");
		var name = document.getElementById("name");
		var email = document.getElementById("email");
		
		if(userid.value == ''){
			alert("아이디를 입력하세요");
			userid.focus();
			return;
		}else if(passwd.value == ''){
			alert("비밀번호를 입력하세요")
			passwd.focus();
			return;
		}else if(name.value == ''){
			alert("이름을 입력하세요")
			name.focus();
			return;
		}else if(email.value == ''){
			alert("이메일을 입력하세요")
			email.focus();
			return;
		}
		document.getElementById("frmupdate").submit();
	}
	
	//삭제체크
	function removeCheck(){
		//삭제 확인창
		var result = confirm('삭제 하시겠습니까?');	
		if(result){
			location.href = '/jsp01/member/remove?userid=<%=member.getUserid()%>';
		}
	}
	
</script>
<body>
	<h2>회원수정</h2>
	<form action="/jsp01/member/update" method="post" id="frmupdate">
		아이디 <input type="text" name="userid" id="userid" value="<%=member.getUserid() %>"> <br>
		비밀번호 <input type="password" name="passwd" id="passwd" value="<%=member.getPasswd() %>"> <br>
		이름 <input type="text" name="name" id="name" value="<%=member.getName() %>"> <br>
		이메일 <input type="email" name="email" id="email" value="<%=member.getEmail() %>"> <br>
		<button type="button" onclick="updateCheck()">수정</button>
		<button type="button" onclick="removeCheck()">삭제</button>
	</form>
	
</body>
</html>

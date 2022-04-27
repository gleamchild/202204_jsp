<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>회원가입 정보</h2>
	<%
		request.setCharacterEncoding("utf-8");
		String userid = request.getParameter("userid");
		String passwd = request.getParameter("passwd");
		String email = request.getParameter("email");
		String username = request.getParameter("username");
		String sex = request.getParameter("sex");
		String signuppath = request.getParameter("signuppath");
		String[] wtime = request.getParameterValues("wtime");
	%>
	아이디 : <%=userid%> <br>
	비밀번호 : <%=passwd%> <br>
	e-mail : <%=email %> <br>
	이름 : <%=username %> <br>
	<%
		if(sex!=null){
	%>
			성별 : <%=sex %> <br>
	<%	}
	%>
	가입경로 : <%=signuppath %> <br>
	<%
		if(wtime!=null){
	%>
			가능 시간대 :
	<%		 
			for(String t:wtime){
			
	%>
				<%=t %>
			 
	<%		}
		}
	%>
	
</body>
</html>
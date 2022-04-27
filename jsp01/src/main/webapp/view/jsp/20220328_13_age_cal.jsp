<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		//미성년자 체크
		int age = Integer.parseInt(request.getParameter("age"));
		String grade;
		if(age>19){
			grade = "성인";
		}else{
			grade = "미성년자";
		}
		//데이터를 담기
		request.setAttribute("grade", grade);
		request.setAttribute("age", age);
		
		//결과창으로 이동
		request.getRequestDispatcher("20220328_13_result.jsp").forward(request, response);
		
	%>
</body>
</html>
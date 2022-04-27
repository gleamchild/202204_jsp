<%@page import="java.util.Scanner"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>표현식</h2>
	<%
		int a=10, b=20;
		out.println("<div id='add'>합:" + (a+b) + "</div>");
	%>
	<hr>
	<!-- 변수, 값 -->
	<div id="add">
		합:<%=a+b%>
	</div>
	<hr>
	<!-- 나이가 20이상이면 성인 ,아니면 미성년자 dom에 출력(표현식을이용) -->
<!-- 	예) 21살은 성인
	        18살은 미성년자 
 -->	
 <!-- jsp 주석(<%----%>) -->
<%--  	<%
 		
 		int age = 21;
 		String s=null;
 		if (age>19){
 			//out.println(age+"살은 성인");
 			s = age+"살은 성인";
 		}else{
 			//out.println(age+"살은 미성년자");
 			s = age+"살은 미성년자";
 		}
 	
 	%>
	<%=s%> <!-- 표현식 --> --%>
	<!-- 표현식으로 --> 
<%--  	<%
 		Scanner sc = new Scanner(System.in);
 		System.out.print("몇살?");
 		int age = sc.nextInt();
 		if (age>19){
	%> 			
 			<%=age%>살은 성인

 	<% 	}else{
 	%>
 			<%=age%>살은 미성년자
 	<% 	}
 	
 	%> --%>
<%-- 	<hr>
	<h5>구구단출력</h5>
	<%
		int dan=3;
		for(int i=1; i<10; i++){
	%>		
			<%=dan%> * <%=i%> = <%=dan*i%> <br>
	<%	}
	%> --%>
	
	<hr>
	<!-- 2~9단까지 출력 -->
	<%
		for(int i=2; i<10; i++){
	%>
			<div>★<%=i%>단★</div> 
	<%		for(int j=1; j<10; j++){
	%>
				<%=i%> * <%=j%> = <%=i*j%><br>		
	<%		}
		}
	%>
	
	<hr>
</body>
</html>
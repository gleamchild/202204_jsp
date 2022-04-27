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
	<%-- <%
		int a=10, b=20;
		out.println("<div id='add'>합:" + (a+b) + "</div>");
	
	%>
	<hr>
	<!-- 변수, 값 출력 -->
	<div id="add">
		<%=a+b%>
	</div>
	<hr> --%>
	<!-- 나이가 20이상 성인, 아니면 미성년자 출력 (표현식 이용) --> 
	<!-- jsp주석 : <%-- --%> -->
	<%-- <%
		int age = 7;
		String s = null;
		
		if(age>19){
			//out.println(age+"살은 성인");
			s = age+"살은 성인";
		}else{
			//out.println(age+"살은 미성년자");
			s = age+"살은 미성년자";
		}
	%>
	<%=s%> --%>
	<%-- <hr>
	<%
		Scanner sc = new Scanner(System.in);
		System.out.print("몇살?");
		int age = sc.nextInt();
		if(age>19){
			//out.println(age+"살은 성인");
	%>
			<%=age%>살은 성인
			
	<% 	}else{
			//out.println(age+"살은 미성년자");
	%>
			<%=age%>살은 미성년자
	<%	}
	%> --%>
	<hr>
	<h5>구구단출력(2단)</h5>
	<%
		for(int i=1;i<10;i++){
	%>
			2 * <%=i%> = <%=2*i%> <br>
	<% 	}
	%>
	<hr>
	<h4>구구단 출력</h4>
	<table border="1">
		<tr>
		<%
			for(int i=2;i<10;i++){
		%>
					<th>
						<%=i%>단
					</th>
				
		<% 		for(int j=1;j<10;j++){
		%>			
					<td>
						<%=i%> * <%=j%> = <%=i*j%> 
					</td>
		<% 		}
		 	}
		%>	
		</tr>
	</table>
	
	
	
	
	
	
	
</body>
</html>
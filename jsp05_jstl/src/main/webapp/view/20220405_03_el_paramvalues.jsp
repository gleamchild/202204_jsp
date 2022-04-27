<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action="">
		<fieldset>
			<legend>판매메뉴</legend>
			<input type="checkbox" name="menu" value="자장면">자장면
			<input type="checkbox" name="menu" value="치킨">치킨
			<input type="checkbox" name="menu" value="피자">피자
		</fieldset>
		<button>선택</button>
	</form>
	
	<%
		String[] menus = request.getParameterValues("menu");
		if(menus != null){
			for(String menu:menus){
	%>				
				<li><%=menu %></li>			
				<li>${paramValues.menu}</li> <!-- 주소만 나옴 -->
	<%		}
		}
	%>
	<h2>EL</h2>
	<!-- null자동처리 -->
	<!-- 반복문은 jstl로 -->
	<li>${paramValues.menu[0]}</li>
	<li>${paramValues.menu[1]}</li>
	<li>${paramValues.menu[2]}</li>
	
	
</body>
</html>
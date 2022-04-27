<%@page import="s02_member.Member"%>
<%@page import="java.util.List"%>
<%@page import="javax.swing.text.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script>
	
</script>
</head>
<body>
	<h2>회원리스트</h2>
	<button onclick="location.href = '/jsp01/member/selectList';">조회</button>
	<button onclick="location.href = '/jsp01/view/db/20220329_01_insert.jsp';">가입</button>
	<table border="1">
		<tr>
			<th>아이디</th>
			<th>비밀번호</th>
			<th>이름</th>
			<th>이메일</th>
		</tr>
		<%
			List<Member> mlist = (List<Member>)request.getAttribute("mlist");
			if(mlist != null){
				for(int i = 0;i<mlist.size(); i++){
		%>	
				<tr>
					<td><a href="/jsp01/member/modify?userid=<%=mlist.get(i).getUserid() %>">
						<%=mlist.get(i).getUserid() %></a>
					</td>
					<td><%=mlist.get(i).getPasswd() %></td>
					<td><%=mlist.get(i).getName() %></td>
					<td><%=mlist.get(i).getEmail() %></td>
				</tr>				
		<%		}		
			}
		%>		
	<script>
		if('<%=request.getParameter("msg") %>' != 'null'){
			<alert(<%=request.getParameter("msg") %>);		
		}
	</script>
		
	</table>
</body>
</html>
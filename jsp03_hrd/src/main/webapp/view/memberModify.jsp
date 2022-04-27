<%@page import="dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<script type="text/javascript">
		if('<%=request.getAttribute("msg") %>' != 'null'){
			alert('<%=request.getAttribute("msg") %>');  
		}
	
		function modifyCheck(){
			var custname = document.getElementById('custname');
			var phone = document.getElementById('phone');
			var address = document.getElementById('address');
			var joindate = document.getElementById('joindate');
			var grade = document.getElementById('grade');
			var city = document.getElementById('city');
			
			if(custname.value == ''){
				alert('회원성명이 입력되지 않았습니다.');
				custname.focus();
				return;
			}else if(phone.value == ''){
				alert('전화번호가 입력되지 않았습니다.');
				phone.focus();
				return;
			}else if(address.value == ''){
				alert('주소가 입력되지 않았습니다');
				address.focus();
				return;
			}else if(joindate.value == ''){
				alert('가입일자가 입력되지 않았습니다');
				joindate.focus();
				return;
			}else if(grade.value == ''){
				alert('고객등급이 입력되지 않았습니다');
				grade.focus();
				return;
			}else if(city.value == ''){
				alert('도시코드가 입력되지 않았습니다');
				city.focus();
				return;
			}else{
				document.getElementById('frmUpdate').submit();
			}
		}
	</script>
</head>
<body>
	<%
		Member member = (Member)request.getAttribute("member");
		String grade = member.getGrade();
	%>
	<%@include file="header.jsp" %>
	<section>
		<h2>홈쇼핑 회원 정보 수정</h2>
		<form action="/jsp03_hrd/Member/update" method="post" id="frmUpdate">
			<table>
				<tr>
					<th>회원번호(자동발생)</th>
					<td> <input type="number" name="custno" id="custno" readonly value="<%=member.getCustno()%>"> </td>
				</tr>
				<tr>
					<th>회원성명</th>
					<td> <input type="text" name="custname" id="custname" value="<%=member.getCustname()%>"> </td>
				</tr>
				<tr>
					<th>회원전화</th>
					<td> <input type="text" name="phone" id="phone" value="<%=member.getPhone()%>"> </td>
				</tr>
				<tr>
					<th>회원주소</th>
					<td> <input type="text" name="address" id="address" value="<%=member.getAddress() %>"> </td>
				</tr>
				<tr>
					<th>가입일자</th>
					<td> <input type="date" name="joindate" id="joindate" value="<%=member.getJoindate()%>"> </td>
				</tr>
				<tr>
					<th>고객등급[A:VIP,B:일반,C:직원]</th>
					<td> 
						<select name="grade" id="grade">
							<option value="A" <%out.print(grade.equals("A")?"selected":"");%>>VIP</option>
							<option value="B" <%out.print(grade.equals("B")?"selected":"");%>>일반</option>
							<option value="C" <%out.print(grade.equals("C")?"selected":"");%>>직원</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>도시코드</th>
					<td> <input type="text" name="city" id="city" value="<%=member.getCity()%>"> </td>
				</tr>
				<tr>
					<td></td>
					<td colspnan="2" > 
						<button type="button" onclick="modifyCheck()">수정</button>
						<button type="button" onclick="memberList()">조회</button>
					</td>
				</tr>
			</table>
		</form>
	</section>
	<%@include file="footer.jsp" %>
</body>
</html>
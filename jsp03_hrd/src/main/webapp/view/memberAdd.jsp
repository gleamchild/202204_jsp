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
	
	function joinCheck(){
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
			document.getElementById('frmadd').submit();
		}			
	}
	
	function memberList(){
		location.href = '/jsp03_hrd/Member/memberList';	
	}
</script>
</head>
<body>
	<%@include file="header.jsp" %>
	<h2>홈쇼핑 회원 등록</h2>
	<form action="/jsp03_hrd/Member/add" method="post" id="frmadd">
		<table>
			<tr>
				<th>회원번호(자동발생)</th>
				<td> <input type="number" name="custno" id="custno" readonly> </td>
			</tr>
			<tr>
				<th>회원성명</th>
				<td> <input type="text" name="custname" id="custname"> </td>
			</tr>
			<tr>
				<th>회원전화</th>
				<td> <input type="text" name="phone" id="phone"> </td>
			</tr>
			<tr>
				<th>회원주소</th>
				<td> <input type="text" name="address" id="address"> </td>
			</tr>
			<tr>
				<th>가입일자</th>
				<td> <input type="date" name="joindate" id="joindate"> </td>
			</tr>
			<tr>
				<th>고객등급[A:VIP,B:일반,C:직원]</th>
				<td> 
					<select name="grade" id="grade">
						<option value="A">VIP</option>
						<option value="B">일반</option>
						<option value="C">직원</option>
					</select>
				</td>
			</tr>
			<tr>
				<th>도시코드</th>
				<td> <input type="text" name="city" id="city"> </td>
			</tr>
			<tr>
				<td></td>
				<td colspnan="2" > 
					<button type="button" onclick="joinCheck()">등록</button>
					<button type="button" onclick="memberList()">조회</button>
				</td>
			</tr>
		</table>
	</form>
	<%@include file="footer.jsp" %>
</body>
</html>
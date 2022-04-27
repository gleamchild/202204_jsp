<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Parameter-post</title>
</head>
<body>
   <h2>데이터 전송(post)</h2>
   
   <h5>ID/PW 입력받아 전송</h5>
   
   <form action="" method="post" > 
   <!-- method default : get -->
   <!-- post : request 객체에 담아서 전송(url에 표시가 되지 않음; 즉, 비밀번호같은 것에 유용) -->
      ID : <input type="text" name="userid"><br>
      PW : <input type="password" name="userpw"><br>
      
      <button>로그인</button>
   </form>
   
   <%
   
      String userid = request.getParameter("userid");
      String userpw = request.getParameter("userpw" );
      if (userid != null){
   %>
   
   <fieldset>
      <legend> 로그인 정보 </legend>
      아이디: <%= userid %> <br>
      비밀번호: <%= userpw %>
   </fieldset>
   
   <%
      }
   %>
</body>
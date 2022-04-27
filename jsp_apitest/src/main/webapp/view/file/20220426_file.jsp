<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>파일 업로드</h2>
	<form action="/jsp_apitest/upload.file" method="post" enctype="multipart/form-data">
		파일 <input type="file" name="file1">
		<button>전송</button>
	</form>
	<hr>
	<h2>파일 다운로드</h2>
	<a href="/jsp_apitest/download.file?filename=${filename}">${filename}</a>
	
</body>
</html>
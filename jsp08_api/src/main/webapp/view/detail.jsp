<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>${country.country_nm}(${country.country_eng_nm}) 안전정보</h2>
	대륙코드 : ${country.continent_cd}
	영문대륙명 : ${country.continent_eng_nm}
	한글대륙명 : ${country.continent_nm}
	iso코드 : ${country.country_iso_alp2}
	작성일 : ${country.wrt_dt}
	<fieldset>
		<h3>제목 : ${country.title}</h3> 
		${country.html_origin_cn}
		<hr>
		${country.txt_origin_cn}
		<fieldset>
			<legend>첨부파일</legend>
			갯수 :${country.file_cnt}<br>	
			다운로드 : <a href="${country.file_download_url}">${country.file_path}</a><br> 
		</fieldset>
	</fieldset>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="path" value='${pageContext.request.contextPath}'/>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<title>Insert title here</title>
<script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=7v323bqzdx"></script>
<script>
	var position = new naver.maps.LatLng(${nmap.y}, ${nmap.x});

	function mapView(){
		//nmap이 null일 경우 - 공백으로 들어옴
		if('${nmap}' == '') {
			document.getElementById('map').innerHTML = '지도 정보가 없습니다.';
			var x = 0; 
			var y = 0;
			return;
		}else{
			var x = '${nmap.x}';
			var y = '${nmap.y}';
		}
		
		var mapOptions = {
		    center: new naver.maps.LatLng(y, x), //경도, 위도
		    zoom: 10
		};
		//map 엘리먼트에 맵을 mapOtion을 이용하여 생성
		var map = new naver.maps.Map('map', mapOptions);
		
		var marker = new naver.maps.Marker({
		    position: position,
		    map: map
		});
		
		naver.maps.Event.addListener(map, 'click', function(e) {
		    marker.setPosition(e.coord);
		});
		
	}
</script>
</head>
<body onload="mapView()">
	<h2>서울시 문화위치정보</h2>
	<form action="${path}/addr.culture">
		명칭 <input type="type" name="name">
		<button>검색</button>
	</form>
	<hr>
	<fieldset>
		<h3>${cmap.FAC_NAME}</h3>
		문화공간코드 : ${cmap.FAC_CODE} <br>
		장르분류코드 : ${cmap.SUBJCODE} <br>
		장르분류명 : ${cmap.CODENAME} <br>
		주소 : ${cmap.ADDR}
	</fieldset>
	<!-- 지도 표시 -->
	<div id="map" style="width:600px;height:450px;"></div>
</body>
</html>
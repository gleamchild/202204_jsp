<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <title>간단한 지도 표시하기</title>
    <script type="text/javascript" src="https://openapi.map.naver.com/openapi/v3/maps.js?ncpClientId=7v323bqzdx"></script>
	<script>
		function mapView(){
			var mapOptions = {
			    center: new naver.maps.LatLng(37.484746663141095, 126.93006829643338), //경도, 위도
			    zoom: 10
			};
			//map 엘리먼트에 맵을 mapOtion을 이용하여 생성
			var map = new naver.maps.Map('map', mapOptions);
			
			var marker = new naver.maps.Marker({
			    position: new naver.maps.LatLng(37.484746663141095, 126.93006829643338),
			    map: map
			});
		}
	</script>
</head> 
<!-- 로딩완료시 함수호출 -->
<body onload="mapView()"> 
<div id="map" style="width:100%;height:400px;"></div>
</body>
</html>
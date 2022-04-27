package service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class CultureService {
	//서울시 문화위치정보 명칭 검색해서 -> 주소 받아오기
	//https://data.seoul.go.kr/dataList/OA-149/A/1/datasetView.do
	public Map<String, Object> cultureParsing(String name) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			//uri만들기
			String serviceKey = "46637968524a746837324241436853";
			StringBuilder sb = new StringBuilder();
			sb.append("http://openAPI.seoul.go.kr:8088/");
			sb.append(serviceKey);
			sb.append("/json/");
			sb.append("SearchCulturalFacilitiesNameService/1/5/");
			sb.append(name);
			
			//conn요청 + 응답 데이터 받기
			URL url = new URL(sb.toString());
			System.out.println(url);
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");
			System.out.println("Response code: " + conn.getResponseCode());
			BufferedReader rd;
			if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();
			conn.disconnect();
			System.out.println("응답데이터:" + sb.toString()); //응답 문자열 데이터
			
			//json파싱 : json-simple-1.1.1.jar
			JSONParser parser = new JSONParser(); //파싱기능이 있는 객체만들기
			
			//응답 문자열 데이터에서 원하는 값 가져오기
			JSONObject object = (JSONObject)parser.parse(sb.toString()); 
			object = (JSONObject)object.get("SearchCulturalFacilitiesNameService");
			JSONArray array = (JSONArray)object.get("row");
			object = (JSONObject)array.get(0); //배열이 하나만 있다(여러개면 반복문 사용)
			
			//object를 map에 넣기
			map.put("FAC_CODE",object.get("FAC_CODE"));
			map.put("SUBJCODE",object.get("SUBJCODE"));
			map.put("CODENAME",object.get("CODENAME"));
			map.put("FAC_NAME",object.get("FAC_NAME"));
			map.put("ADDR",object.get("ADDR"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	//geocoding - 주소를 이용하여 경도, 위도 알아내기
	public Map<String, Double> geocoding(String addr) {
		Map<String, Double> map = new HashMap<String, Double>();
		try {
			//uri만들기
			StringBuilder sb = new StringBuilder();
			sb.append("https://naveropenapi.apigw.ntruss.com/map-geocode/v2/geocode");
			sb.append("?query=" + URLEncoder.encode(addr, "utf-8"));
			
			//conn요청 + 응답 데이터 받기
			URL url = new URL(sb.toString());
			System.out.println("geocoding url :" + url);
			
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			//API명세서참고 헤더
			conn.setRequestProperty("Content-type", "application/json");
			conn.setRequestProperty("X-NCP-APIGW-API-KEY-ID", "7v323bqzdx"); //앱 등록 시 발급받은 Client ID
			conn.setRequestProperty("X-NCP-APIGW-API-KEY", "Qemg5dyz0OerWcx3sHr4kMZ5o5o5ovargbWlLsQp"); //앱 등록 시 발급 받은 Client Secret
			
			System.out.println("Response code: " + conn.getResponseCode());
			BufferedReader rd;
			if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}
			sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}
			rd.close();
			conn.disconnect();
			System.out.println("geocoding데이터:" + sb.toString()); //응답 문자열 데이터
			
			//json파싱
			JSONParser parser = new JSONParser();
			
			JSONObject object = (JSONObject)parser.parse(sb.toString());
			JSONObject metaobject = (JSONObject)object.get("meta");
			//만약 totalCount가 0이라면 null을 리턴
			long totcount = (long)metaobject.get("totalCount");
			if(totcount == 0) return null;
			
			JSONArray array = (JSONArray)object.get("addresses");
			object = (JSONObject)array.get(0);
			
			double x = Double.parseDouble((String)object.get("x")); //0bject타입 -> double타입으로 형변환
			double y = Double.parseDouble((String)object.get("y"));
			map.put("x", x);
			map.put("y", y);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	
}

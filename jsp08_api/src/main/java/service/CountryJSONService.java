package service;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import dao.CountryDAO;
import dto.Country;

import java.io.BufferedReader;
import java.io.IOException;

public class CountryJSONService {
	private CountryDAO countryDAO = new CountryDAO();
	
    public int countryParsing(String countrycode) throws IOException, ParseException {
    	//데이터포털: 외교부_국가.지역별 최신안전소식(코로나관련)
    	
    	//반환값(map占쏙옙占쏙옙 占쏙옙占쏙옙)
    	List<Map<String, String>> clist = new ArrayList<>();
    	
    	String serviceKey = "%2FUxF4yRMqxUszgnnEDNa5ipqsNEsIbK%2FPqEHY6v6tgPk4RQTd06q2sy7aWC6exvqdQA2kmf3F5RETxc9Zuypxg%3D%3D";
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1262000/CountryCovid19SafetyServiceNew/getCountrySafetyNewsListNew"); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*XML 또는 JSON*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지 번호*/
//        urlBuilder.append("&" + URLEncoder.encode("cond[country_nm::EQ]","UTF-8") + "=" + URLEncoder.encode("가나", "UTF-8")); /*한글 국가명*/
        urlBuilder.append("&" + URLEncoder.encode("cond[country_iso_alp2::EQ]","UTF-8") + "=" + URLEncoder.encode(countrycode, "UTF-8")); /*ISO 2자리코드*/
        
        URL url = new URL(urlBuilder.toString());
        System.out.println(url);
        
        // conn + 응답 데이터 문자열 생성
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
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
//        System.out.println(sb.toString()); //문자열 데이터
        
        //json파싱하기
        //파싱라이브러리 : json-simple-1.1.1.jar
        JSONParser parser = new JSONParser();
        //conn객체를 통해 응답받은 문자열을 데이터를 json객체로 변경
        JSONObject object = (JSONObject)parser.parse(sb.toString()); 
        
        JSONArray array = (JSONArray)object.get("data"); 
//        System.out.println(array);
//        System.out.println("-------------------------------");
        for(int i=0;i<array.size();i++) {
//        	System.out.println(i + "-----------------------------");
        	object = (JSONObject)array.get(i);
        	Map<String, String> map = new HashMap<String, String>();
        	
        	//직접 key 하드코딩 
//        	String continent_nm = (String)object.get("continent_nm");
//        	String wrt_dt = (String)object.get("wrt_dt");
        	
        	//key의 목록가져와서 반복문
        	//set특징 : 중복데이터x, 순서x
        	Set<String> kset = object.keySet();
        	for(String key:kset) {
//        		System.out.println(key + ":" + object.get(key));
        		
        		//문자열 인코딩 바꿔주기
//        		String value = (String)object.get(key);
//        		if(value != null) {
//        			byte[] bytes = value.getBytes("utf-8");
//        			String cvalue = new String(bytes,"utf-8");
//        		}	
        			map.put(key, (String)object.get(key));
        	}
        	clist.add(map);
        }
        int cnt = countryDAO.insert(clist);
        System.out.println(cnt);
        return cnt;
    }

	public List<Map<String, String>> selectList_iso() {
		return countryDAO.selectList_iso();
	}
	
	public List<Country> selectList(String iso){
		return countryDAO.selectList(iso);
	}

	public Country selectOne(String sfty_notice_id) {
		return countryDAO.selectOne(sfty_notice_id);
	}
	
}

package service;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import dao.AirDAO;
import dto.Air;

import java.io.BufferedReader;
import java.io.IOException;

public class AirService {
	AirDAO airDAO = new AirDAO();
	
    public int airParsing(String year, String itemCode) throws IOException, ParseException, java.text.ParseException{
    	String serviceKey = "%2FUxF4yRMqxUszgnnEDNa5ipqsNEsIbK%2FPqEHY6v6tgPk4RQTd06q2sy7aWC6exvqdQA2kmf3F5RETxc9Zuypxg%3D%3D";
    	
        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/B552584/UlfptcaAlarmInqireSvc/getUlfptcaAlarmInfo");
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey);
        urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("20", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("year","UTF-8") + "=" + URLEncoder.encode(year, "UTF-8"));
        urlBuilder.append("&" + URLEncoder.encode("itemCode","UTF-8") + "=" + URLEncoder.encode(itemCode, "UTF-8"));
        URL url = new URL(urlBuilder.toString());
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
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
//        System.out.println(sb.toString());
        
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject)parser.parse(sb.toString());
        object = (JSONObject)object.get("response");
        object = (JSONObject)object.get("body");
        JSONArray arr = (JSONArray)object.get("items");
        int cnt=0;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        
        for(int i=0;i<arr.size();i++) {
        	object = (JSONObject)arr.get(i);
        	Air airCheck = airDAO.selectOne((String)object.get("sn"));
        	if(airCheck == null) { //유니크 에러 처리
        		Air air = new Air();
        		air.setSn((String)object.get("sn"));
        		Date clearDate = formatter.parse((String)object.get("clearDate"));
        		Date dataDate = formatter.parse((String)object.get("dataDate"));
        		Date issueDate = formatter.parse((String)object.get("issueDate"));
        		air.setClearDate(clearDate);
        		air.setDataDate(dataDate);
        		air.setIssueDate(issueDate);
        		air.setClearTime((String)object.get("clearTime"));
        		air.setClearVal((String)object.get("clearVal"));
        		air.setDistrictName((String)object.get("districtName"));
        		air.setIssueGbn((String)object.get("issueGbn"));
        		air.setIssueTime((String)object.get("issueTime"));
        		air.setIssueVal((String)object.get("issueVal"));
        		air.setItemCode((String)object.get("itemCode"));
        		air.setMoveName((String)object.get("moveName"));
        		cnt += airDAO.insert(air);
        	}
        }
        return cnt;
    }

	public List<Air> selectList(String districtName) {
		return airDAO.selectList(districtName);
	}
    
}
package service;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import dao.CountryDAO;
import dao.CovidDAO;
import dto.Covid;

class JunitTest {
	CovidXMLService covidXmlService = new CovidXMLService();
	CovidDAO covidDAO = new CovidDAO();
	
	@Test
	void testCovidParsing() {
		String startCreateDt = "20220407";
		String endCreateDt = "20220411";
		covidXmlService.covidParsing(startCreateDt, endCreateDt);
		
	}
	
	@Test
	void testSelectList() {
		String startDt = "20220407";
		String endDt = "20220411";
		Map<String, String> map = new HashMap<String, String>();
		map.put("startDt", startDt);
		map.put("endDt", endDt);
		
		List<Covid> covidList = covidDAO.selectList(map);
		System.out.println(covidList);
	}
	
	//CountryJSONService Test
	CountryJSONService cService = new CountryJSONService();
	@Test
	void testCountryParsing() {
		try {
			int cnt = cService.countryParsing("GB");
			System.out.println(cnt);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testSelectiso() {
		CountryDAO countryDAO = new CountryDAO();
		List<Map<String, String>> mlist = countryDAO.selectList_iso();
		for(Map<String, String> map:mlist) {
			System.out.println(map);
			System.out.println(map.get("CODE"));
		}
	}
	
	CultureService cultureService = new CultureService();
	//문화위치정보
	@Test
	void testCulture() {
		String name = "서울연극센터";
		cultureService.cultureParsing(name);
	}
	
	@Test
	void testCultureGeocoding() {
		String addr = "서울 종로구 명륜4가";
		cultureService.geocoding(addr);
	}
	
	
	
	
	
	
	
}

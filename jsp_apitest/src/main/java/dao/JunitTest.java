package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;

import dto.Air;
import dto.Where;
import service.AirService;

class JunitTest {
	AirService aService = new AirService();
	AirDAO aDAO = new AirDAO();
	
	@Test
	void testAirParsing() {
		try {
			try {
				aService.airParsing("2021","PM25");
			} catch (java.text.ParseException e) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Test
	void testSelectList() {
		String districtName = "인천";
		List<Air> alist = aDAO.selectList(districtName);
		System.out.println(alist);
	}
	
	@Test
	void testSelectOne() {
		String sn = "208";
		Air air = aDAO.selectOne(sn);
		System.out.println(air);
	}
	
	@Test
	void testWhere() {
		
		
	}
	
}

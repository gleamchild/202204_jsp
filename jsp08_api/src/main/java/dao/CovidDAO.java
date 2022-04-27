package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.Covid;

public class CovidDAO {
	public int insert(List<Map<String, String>> covidList) {
		//covid의 리스트를 반복문을 이용해 여러건 넘겨주기
		int cnt=0;
		SqlSession session = MBConn.getSession();
		for(Map<String, String> covid:covidList) {
			try {
				cnt += session.insert("CovidMapper.insert", covid);
			} catch (Exception e) {
				System.out.println(covid.get("seq") + ":예외발생");
				//수정
				cnt += session.update("CovidMapper.update", covid);
			}
		}
		//반복문을 이용해 여러건 insert후 커밋하고 클로즈
		session.commit(); //mybatisConfig의 transactionManager -> JDBC
		session.close();
		return cnt;
	}
	
	public List<Covid> selectList(Map<String, String> map) {
		try(SqlSession session = MBConn.getSession()){
			return session.selectList("CovidMapper.selectList", map);
		}
	}
	
}

package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.Air;

public class AirDAO {
	public int insert(Air air) {
		try(SqlSession session = MBConn.getSession()){
			int cnt = session.insert("AirMapper.insert", air);
			session.commit();
			return cnt;
		}
	}
	
	public List<Air> selectList(String districtName) {
		try(SqlSession session = MBConn.getSession()){
			return session.selectList("AirMapper.selectList", districtName);
		}
	}
	
	public Air selectOne(String sn) {
		try(SqlSession session = MBConn.getSession()){
			return session.selectOne("AirMapper.selectOne", sn);
		}
	}
	
	
}

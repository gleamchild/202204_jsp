package dao;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.apache.ibatis.session.SqlSession;

import dto.Country;

public class CountryDAO {
	public List<Map<String, String>> selectList_iso(){
		try(SqlSession session = MBConn.getSession()){
			return session.selectList("CountryMapper.selectList_iso");
		}
	}

	public int insert(List<Map<String, String>> clist) {
		//占식쏙옙占쏙옙 占쏙옙占실몌옙占쏙옙트 db占쏙옙 占쏙옙占쏙옙
		int cnt=0;
		try(SqlSession session = MBConn.getSession()){
			for(Map<String, String> map:clist) {
				//占싼곤옙占쏙옙회
				Country country = session.selectOne("CountryMapper.selectOne", map.get("sfty_notice_id"));
				
				//유니크 에러처리?
				//占쏙옙占쏙옙 占쏙옙占쏙옙占싶곤옙 占쏙옙占쏙옙占싼다몌옙 update
				if(country != null) {
					cnt += session.update("CountryMapper.update", map);
				}else {
					//占쏙옙占쏙옙占쏙옙占쏙옙 占십는다몌옙 insert
					cnt += session.insert("CountryMapper.insert", map);
				}
			}
			//占쌥븝옙占쏙옙 占쏙옙占쏙옙占쏙옙 commit:JDBC占쏙옙 占쏙옙占쏙옙占싹몌옙 占쏙옙占쏙옙占쌘곤옙 占쏙옙占쏙옙 占쌀쏙옙占쏙옙占쏙옙 트占쏙옙占쏙옙占?占쏙옙占쏙옙占쌔억옙占쏙옙
			session.commit();
			return cnt;
		}
	}
	
	public List<Country> selectList(String iso){
		try(SqlSession session = MBConn.getSession()){
			return session.selectList("CountryMapper.selectList", iso);
		}
	}
	
	public Country selectOne(String sfty_notice_id) {
		try(SqlSession session = MBConn.getSession()){
			return session.selectOne("CountryMapper.selectOne", sfty_notice_id);
		}
	}
	
}

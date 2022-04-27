package dao;

import org.apache.ibatis.session.SqlSession;

import dto.CountryReply;

public class CountryReplyDAO {
	public int insert(CountryReply countryreply) {
		try(SqlSession session = MBConn.getSession()){
			return session.insert("CountryReplyMapper", countryreply);
		}
	}
}

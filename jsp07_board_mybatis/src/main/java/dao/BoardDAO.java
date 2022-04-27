package dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import dto.Board;

public class BoardDAO {
	public int insert(Board board) {
		/* try~with문 */
		//session을 자동으로 close해준다
		try(SqlSession session = MBConn.getSession()){
			// 매퍼의 namespace + isnert태그id결합, 매개변수
			return session.insert("BoardMapper.insert", board);
		}
	}
	
	public int update(Board board) {
		try(SqlSession session = MBConn.getSession()){
			return session.update("BoardMapper.update", board);
		}
	}
	
	public int delete(int bnum) {
		try(SqlSession session = MBConn.getSession()){
			return session.update("BoardMapper.delete", bnum);
		}
	}
	//두개이상의 데이터를 mapper에 넘겨줄때는 map또는 dto을 이용
	public List<Board> selectList(Map<String, Object> findmap) {
		try(SqlSession session = MBConn.getSession()){
			return session.selectList("BoardMapper.selectList", findmap);
		}
	}
	
	public Board selectOne(int bnum) {
		try(SqlSession session = MBConn.getSession()){
			return session.selectOne("BoardMapper.selectOne", bnum);
		}
	}
	
	public int update_readcnt(int bnum) {
		try(SqlSession session = MBConn.getSession()){
			return session.update("BoardMapper.update_readcnt", bnum);
		}
	}
	//조회된 게시물수
	public int select_totalcnt(Map<String, Object> findmap) {
		try(SqlSession session = MBConn.getSession()){
			return session.selectOne("BoardMapper.select_totalcnt", findmap);
		}
	}
	
}

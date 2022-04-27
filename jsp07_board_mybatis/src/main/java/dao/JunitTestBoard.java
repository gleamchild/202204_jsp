package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import dto.Board;

public class JunitTestBoard {
	private BoardDAO bdao = new BoardDAO();

	@Test
	void testSqlSession() {
		MBConn.getSession();
	}
	
	@Test
	void testBoardIsert() {
		Board board = new Board();
		board.setUserid("java");
		board.setSubject("testSubject2");
		board.setContent("testContent2");
		board.setIp("192.168.0.11");
		int cnt = bdao.insert(board);
		System.out.println(cnt);
	}
	
	@Test
	void testBoardUpdate() {
		Board board = new Board();
		board.setUserid("modi");
		board.setSubject("ModiSubject2");
		board.setContent("ModiContent2");
		board.setIp("192.168.0.199");
		board.setBnum(2);
		int cnt = bdao.update(board);
		System.out.println(cnt);
	}
	
	@Test
	void testDelete() {
		int bnum = 2;
		int cnt = bdao.delete(bnum);
		System.out.println(cnt);
	}
	
	@Test
	void testSelectList() {
		Map<String, Object> findmap = new HashMap<String, Object>();
		findmap.put("findkey", "subject");
		findmap.put("findvalue", "test");
		List<Board> blist = bdao.selectList(findmap);
		System.out.println(blist);
	}
	
	@Test
	void testSelectOne() {
		int bnum = 3;
		Board board = bdao.selectOne(bnum);
		System.out.println(board);
	}
}








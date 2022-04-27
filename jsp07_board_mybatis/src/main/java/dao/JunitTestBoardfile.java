package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import dto.Boardfile;


class JunitTestBoardfile {
	private BoardfileDAO bfdao = new BoardfileDAO();
	@Test
	void testInsert() {
		Boardfile boardfile = new Boardfile();
		boardfile.setBnum(4);
		boardfile.setFilename("testfile2");
		int cnt = bfdao.insert(boardfile);
		System.out.println(cnt);
	}
	
	@Test
	void testUpdate() {
		Boardfile boardfile = new Boardfile();
		boardfile.setBfnum(2);
		boardfile.setFilename("modifile");
		int cnt = bfdao.update(boardfile);
		System.out.println(cnt);
	}
	
	@Test
	void testDelete() {
		int cnt = bfdao.delete(2);
		System.out.println(cnt);
	}
	
	@Test
	void testSelectOne() {
		Boardfile boardfile = bfdao.selectOne(3);
		System.out.println(boardfile);
	}
	
	@Test
	void testSelectList() {
		List<Boardfile> bflist = bfdao.selectList(3);
		System.out.println(bflist);
	}

}

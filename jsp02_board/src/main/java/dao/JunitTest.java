package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import dto.Board;

class JunitTest {
	Scanner sc = new Scanner(System.in);
	BoardDAO bdao;
	
	@Test
	void testInsert() {
		Board board = new Board("java","제목테스트2","내용테스트2");
		bdao = new BoardDAO();
		int cnt = bdao.insert(board);
		System.out.println(cnt+"건 추가");
	}

	@Test
	void testUpdate() {
		Board board = new Board();
		board.setSeq(2);
		board.setWriter("수정자");
		board.setSubject("수정테스트");
		board.setContent("수정내용테스트");
		
		bdao = new BoardDAO();
		int cnt = bdao.update(board);
		System.out.println(cnt+"건 수정");
	}

	@Test
	void testDelete() {
		System.out.print("삭제할 순번:");
		int seq = sc.nextInt();
		bdao = new BoardDAO();
		int cnt = bdao.delete(2);
		System.out.println(cnt+"건 삭제");
	}

	@Test
	void testSelectAll() {
		String findkey = sc.nextLine();
		System.out.print("조회할 제목:");
		String subject = sc.nextLine();
		bdao = new BoardDAO();
		List<Board> blist = bdao.selectList(findkey, subject);
		System.out.println(blist);
	}

	@Test
	void testSelectOne() {
		System.out.print("조회할 순번:");
		int seq = sc.nextInt();
		bdao = new BoardDAO();
		Board board = bdao.selectOne(seq);
		System.out.println(board);
	}

}

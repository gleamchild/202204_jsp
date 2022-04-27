package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import dto.Reply;

class JunitTestReply {
	ReplyDAO rdao = new ReplyDAO();
	
	@Test
	void testInsert() {
		Reply reply = new Reply();
		reply.setBnum(4);
		reply.setContent("댓글insert test2");
		reply.setRestep(1);
		reply.setRelevel(1);
		int cnt = rdao.insert(reply);
		System.out.println(cnt);
	}
	
	@Test
	void testUpdate() {
		Reply reply = new Reply();
		reply.setRnum(2);
		reply.setContent("댓글Modi test");
		int cnt = rdao.update(reply);
		System.out.println(cnt);
	}

	@Test
	void testSelectList() {
		int bnum = 4;
		List<Reply> rlist = rdao.selectList(bnum);
		System.out.println(rlist);
	}
	
	@Test
	void testSelectOne() {
		int rnum = 5;
		Reply reply = rdao.selectOne(rnum);
		System.out.println(reply);
	}
	
	@Test
	void testDelete() {
		int rnum = 2;
		int cnt = rdao.delete(rnum);
		System.out.println(cnt);
	}
	

}

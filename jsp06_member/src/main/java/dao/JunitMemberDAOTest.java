package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import dto.Member;

public class JunitMemberDAOTest {

	@Test
	void testInsert() {
		Member member = new Member();
		member.setUserid("ahn");
		member.setPasswd("1111");
		member.setSalt("1111");
		member.setZipcode("00881");
		member.setAddrload("서울시 관악구");
		member.setAddrdetail("디테일 주소12");
		member.setFilename("file");
		MemberDAO mdao = new MemberDAO();
		int cnt = mdao.insert(member);
		System.out.println(cnt);
	}

	@Test
	void testUpdate() {
		Member member = new Member();
		member.setPasswd("1111");
		member.setSalt("1111");
		member.setZipcode("00881");
		member.setAddrload("수정 주소");
		member.setAddrdetail("디테일 주소");
		member.setFilename("file");
		member.setUserid("hong");
		MemberDAO mdao = new MemberDAO();
		int cnt = mdao.update(member);
		System.out.println(cnt);
	}

	@Test
	void testDelete() {
		String userid = "java";
		MemberDAO mdao = new MemberDAO();
		int cnt = mdao.delete(userid);
		System.out.println(cnt);
	}

	@Test
	void testSelectList() {
		Map<String, String> findmap = new HashMap<String, String>();
		findmap.put("findkey", "addrload");
		findmap.put("findvalue", "관악");
		MemberDAO mdao = new MemberDAO();
		List<Member> mlist = mdao.selectList(findmap);
		System.out.println(mlist);
	}

	@Test
	void testSelectOne() {
		String userid = "java";
		MemberDAO mdao = new MemberDAO();
		Member member = mdao.selectOne(userid);
		System.out.println(member);
	}

}

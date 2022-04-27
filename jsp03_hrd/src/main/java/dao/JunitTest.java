package dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import dto.Member;
import dto.Sales;

class JunitTest {
	MemberDAO mdao = new MemberDAO();

	@Test
	void testInsert() {
		Member member = new Member();
		member.setCustno(100001);
		member.setCustname("김행복");
		member.setPhone("010-1111-2222");
		member.setAddress("서울 동대문구 휘경1동");
		member.setJoindate("20151202");
		member.setGrade("A");
		member.setCity("01");
		int cnt = mdao.insert(member);
		System.out.println(cnt);
	}

	@Test
	void testselectList() {
		List<Member> mlist = mdao.selectList();
		System.out.println(mlist);
	}
	
	@Test
	void testselectSalesList() {
		List<Sales> slist = mdao.selectSalesList();
		System.out.println(slist);
	}
	
	@Test
	void update() {
		Member member = new Member();
		member.setCustno(100001);
		member.setCustname("김행복");
		member.setPhone("010-1111-2222");
		member.setAddress("서울 동대문구 휘경1동");
		member.setJoindate("20151202");
		member.setGrade("A");
		member.setCity("01");
		int cnt = mdao.update(member);
		System.out.println(cnt);
	}
	
	
}

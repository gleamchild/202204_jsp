package s02_member;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;

import org.junit.jupiter.api.Test;

class JunitTest {

	@Test
	void testDBConn() {
		//db커넥션테스트
		Connection con = DBConn.getConnection();
		System.out.println(con);
		//con이 null아닐때 성공
		assertNotNull(con);
	}
	
	
	@Test
	void testInsert() {
		MemberDAO mdao = new MemberDAO();
		Member member = new Member("java","1111","자바","java@gamil.com");
		int cnt = mdao.insert(member);
		System.out.println(cnt+"건 추가");
	}
	
	@Test
	void testUpdate() {
		MemberDAO mdao = new MemberDAO();
		Member member = new Member("java","1234","줘봐","jwubwa@gamil.com");
		int cnt = mdao.update(member);
		System.out.println(cnt+"건 변경");
	}
	
//	@Test
//	void testDelete() { //1건 삭제
//		MemberDAO mdao = new MemberDAO();
//		Member member = new Member();
//		int cnt = mdao.delete(member);
//		System.out.println(cnt+"건 삭제");
//	}
	
}

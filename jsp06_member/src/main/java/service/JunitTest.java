package service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class JunitTest {
	MemberService ms = new MemberService();
	
	@Test
	void testSha256() {
		String scpw = ms.sha256("123456789", "");
		System.out.println(scpw);
		
	}

	@Test
	void testSalt() {
		String salt = ms.saltmake();
		System.out.println(salt);
	}
}

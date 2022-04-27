package service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.MemberDAO;
import dto.Member;

public class MemberService {
	private MemberDAO mdao = new MemberDAO();
	
	public int insert(Member member) {
		//비밀번호 암호화
		String salt = saltmake();
		String secretpw = sha256(member.getPasswd(), salt);
		member.setPasswd(secretpw);
		member.setSalt(salt);
		
		return mdao.insert(member);
	}

	//sha256방식으로 평문을 암호문으로 변경해서 반환하는 메서드
	String sha256(String passwd,String salt) {
		//StringBuffer : String대신 사용, 속도(메모리) 효율적
		StringBuffer sb = new StringBuffer(); //암호저장 변수
		try {
			//SHA-256 : 단방향 암호기법, 복호화 불가능, 256bit(16진수 64byte필요)
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(passwd.getBytes()); //문자열을 바이트배열로 변경해서 전달
			md.update(salt.getBytes()); //salt를 바이트배열로 변경해서 전달
			byte[] data =  md.digest(); //암호화된 바이트 배열(32byte)
			
			System.out.println("암호화된 바이트 배열" + Arrays.toString(data));
			//10진수를 16진수로 변환해서 sb변수에 추가
			for(byte b:data) {
				sb.append(String.format("%02x", b));
			}
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}
	
	//salt를 랜덤하게 만들기
	String saltmake() {
		String salt = null;
		//난수생성 알고리즘
		try {
			SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
			byte[] bytes = new byte[16];
			sr.nextBytes(bytes); //빈배열을 넣어주면 랜덤한 값을 bytes에 만든다
			System.out.println(Arrays.toString(bytes));
			//정수byte를 String으로 변경
			//Base64인코딩 : 아스키중 제어문자,특수문자를 제외한 64개의 안전한 문자만 사용
			// 16byte -> 24byte
			salt = new String(Base64.getEncoder().encode(bytes));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return salt;
	}
	
	public Map<String, Object> loginCheck(String userid, String passwd) {
		//리턴값 code추가 : 한개만 리턴가능(map생성-msg와 code를 같이 리턴시키기 위해서)
		//code : 0(성공), 1(회원미존재), 2(비밀번호불일치)
		//msg : 성공, 회원미존재, 비밀번호 불일치
		Map<String, Object> rmap = new HashMap<String, Object>();
		int code = -1;
		String msg="";
		//dao호출
		//1)userid를 기준으로 한건조회(selectOne)
		Member member = mdao.selectOne(userid);
		
		//2)만약 리턴값이 null이면 회원이 존재하지 않는다
		if(member == null) {
			msg = "회원이 존재하지 않습니다";
			code = 1;
		}else if(member.getUserid().equals(userid)) {
			//암호화된 db의 passwd와 비교하기 위해
			//입력값도 db의 salt를 불러와 암호화 하여 비교
			String secretpw = sha256(passwd, member.getSalt());
			if(member.getPasswd().equals(secretpw)) {
				msg = "로그인 성공";
				code = 0;
			}else {
				msg = "비밀번호 불일치";
				code = 2;
			}
		}
		rmap.put("code", code);
		rmap.put("msg", msg);
		
		//3)null이 아니면 passwd일치 여부 확인
		//패스워드가 일치하면 로그인 성공
		return rmap;
	}

	public Member selectOne(String userid) {
		Member member = mdao.selectOne(userid);
		return member;
	}

	public List<Member> selectList(Map<String, String> findmap) {
		List<Member> mlist = mdao.selectList(findmap);
		return mlist;
	}

	public String delete(String userid) {
		int cnt = mdao.delete(userid);
		String msg="";
		if(cnt==1) {
			msg = "회원탈퇴가 완료되었습니다!";
		}else {
			msg = "회원탈퇴에 실패했습니다";
		}
		return msg;
	}

	public Map<String, Object> update(Member member) {
		//수정
		//입력 비밀번호 암호화해서 db비밀번호와 일치여부 판단
		//기존비밀번호 읽어오기
		Member dbmember = mdao.selectOne(member.getUserid());
		Map<String, Object> rmap = new HashMap<String, Object>();
		//평문과 salt를 이용해서 암호문 리턴
		String secretpw = sha256(member.getPasswd(), dbmember.getSalt());
		String msg="";
		int code=-1;
		if(!secretpw.equals(dbmember.getPasswd())) {
			rmap.put("code", 1);
			rmap.put("msg", "비밀번호가 일치하지 않습니다.");
			return rmap;
		}
		
		member.setSalt(dbmember.getSalt());
		//변경비밀번호가 있다면 비밀번호 
		if(!member.getNewpasswd().equals("")) {
			secretpw = sha256(member.getNewpasswd(), dbmember.getSalt());
			member.setPasswd(member.getNewpasswd());
		}
		member.setPasswd(secretpw);
		
		int cnt = mdao.update(member);
		if(cnt==1) {
			msg="회원정보가 수정 되었습니다";
			code=0;
		}else {
			msg="회원정보 수정 실패";
			code=2;
		}
		rmap.put("msg", msg);
		rmap.put("code", code);
		
		return rmap;
	}
	
}

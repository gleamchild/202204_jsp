package service;

import java.util.HashMap;
import java.util.Map;

import dao.MemberDAO;
import dto.Member;

//service - 컨트롤러와 dao를 연결
//			비지니스 로직 처리
public class LoginService {
	private MemberDAO mdao = new MemberDAO();
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
			if(member.getPasswd().equals(passwd)) {
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
	
}

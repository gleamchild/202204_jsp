package s02_member;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//가입버튼 누르면 실행될 서블릿

@WebServlet("/member/*")  // 
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); //인코딩
		String uri = request.getRequestURI();
		System.out.println(uri);
		
		if(uri.contains("insert")) {
			//가입(insert) 처리
			System.out.println("가입");
			String msg;
			//view에서 입력한 데이터 읽기
			String userid = request.getParameter("userid");
			String passwd = request.getParameter("passwd");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			
			//멤버객체생성
			Member member = new Member(userid, passwd, name, email);
			System.out.println(member);
			
			//DB에 접속후 DB에 데이터추가
			MemberDAO mdao = new MemberDAO();
			int cnt = mdao.insert(member);
			System.out.println(cnt+"건 추가");
			
			//회원가입 완료 메세지 뷰로 보내기
			if(cnt==1) {
				msg = name+"님 회원가입을 축하드립니다!";
			}else {
				msg = "회원가입 실패";
			}
			
//			request.setAttribute("msg", msg);
			//forward방식 - 프로젝트 이동 없으므로 프로젝트명 생략
//			request.getRequestDispatcher("/view/db/20220329_01_insert.jsp").forward(request, response);
			
			//redirect방식 - 프로젝트 이동 가능 프로젝트명 생략불가
			//url에 정보 넣을때 인코딩방식 하나씩 변경해야함
			msg = URLEncoder.encode(msg, "utf-8"); 
			response.sendRedirect("/jsp01/view/db/20220329_01_insert.jsp?msg="+msg);
		}else if(uri.contains("selectList")) {
			//조회(selectlist) 처리
			System.out.println("조회");
			MemberDAO mdao = new MemberDAO();
			List<Member> mlist = mdao.selectList();
			System.out.println(mlist);
			
			//대량의 데이터이므로 forward방식으로 이동
			request.setAttribute("mlist", mlist);
			request.getRequestDispatcher("/view/db/20220330_selectList.jsp").forward(request, response);
			
		}else if(uri.contains("modify")) {
			//수정폼으로 이동
			//한건조회 후 그 정보를 수정폼으로 전송
			String userid = request.getParameter("userid");
			System.out.println(userid);
			MemberDAO mdao = new MemberDAO();
			Member member = mdao.selectOne(userid);
			
			request.setAttribute("member", member);
			request.getRequestDispatcher("/view/db/20220330_update.jsp").forward(request, response);
		}else if(uri.contains("update")) {
			System.out.println("수정");
			MemberDAO mdao = new MemberDAO();
			Member member = new Member(request.getParameter("userid"),request.getParameter("passwd"),request.getParameter("name"),request.getParameter("email"));
			int cnt = mdao.update(member);
			System.out.println(cnt);
			
			String msg;
			if(cnt==1) {
				msg = request.getParameter("name") + "님 회원정보를 수정했습니다";
			}else {
				msg = "수정 실패";
			}
			
			response.sendRedirect("/jsp01/member/selectList");
		}else if(uri.contains("remove")) {
			System.out.println("삭제");
			String userid = request.getParameter("userid");
			MemberDAO mdao = new MemberDAO();
			int cnt = mdao.delete(userid);
			System.out.println(cnt+"건 삭제");
			response.sendRedirect("/jsp01/member/selectList");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

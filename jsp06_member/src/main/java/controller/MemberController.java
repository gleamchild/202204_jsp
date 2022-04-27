package controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dto.Member;
import service.MemberService;

@WebServlet("*.member")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private MemberService ms = new MemberService(); 
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		System.out.println(uri);
		String path = request.getContextPath();
		
		if(uri.contains("join")) {
			//saveDirectory : 파일저장경로(서버의 경로)
			//String saveDirectory = "D:/AHJ/savedir";
			
			//프로젝트의 web.xml의 context-param의 값을 읽어오는 방법
			String saveDirectory = getServletContext().getInitParameter("savedir");
			
			//size : 업로드파일 사이즈 제한
			int size = 1024 * 1024 * 10; //10mbyte
			//DefaultFileRenamePolicy() : 같은이름의 파일이 있을때 자동 파일이름변경
			MultipartRequest multi = new MultipartRequest(request, saveDirectory, size, "utf-8", new DefaultFileRenamePolicy());
			
			//MultipartRequest객체의 메서드를 이용해서 데이터 얻기
			String userid = multi.getParameter("userid");
			String passwd = multi.getParameter("passwd");
			String zipcode = multi.getParameter("zipcode");
			String addrload = multi.getParameter("addrload");
			String addrdetail = multi.getParameter("addrdetail");
			//실제 서버에 저장된 파일이름
			String filename = multi.getFilesystemName("photo");
			
			Member member = new Member();
			member.setUserid(userid);
			member.setPasswd(passwd);
			member.setZipcode(zipcode);
			member.setAddrload(addrload);
			member.setAddrdetail(addrdetail);
			member.setFilename(filename);
			System.out.println(member);
			
			//서비스 호출
			int cnt = ms.insert(member);
			System.out.println(cnt);
			
			//redirect이동
			response.sendRedirect(path+"/member/join.jsp?msg="+URLEncoder.encode(cnt+"건 추가", "utf-8"));
			
		}if(uri.contains("login")) {
			String userid = request.getParameter("userid");
			String passwd = request.getParameter("passwd");
			System.out.println(userid);
			System.out.println(passwd);
			//service호출
			Map<String, Object> rmap = ms.loginCheck(userid, passwd);
			
			//로그인 성공시 session을 생성 
			int code = (int)rmap.get("code");
			if(code==0) {
				//세션객체생성
				//request객체가 sessionid를 가지고있다 sessionid별로 session생성
				HttpSession session = request.getSession(); 
				session.setAttribute("userid", userid);
				session.setAttribute("passwd", passwd);
				//세션의 유효시간 설정
				session.setMaxInactiveInterval(60*60*3);//초단위 60*60 = 1시간
				
				String idsave =  request.getParameter("idsave");
				System.out.println(idsave);
				Cookie useridCookie = new Cookie("userid", userid);
//				useridCookie.setMaxAge(10); //초단위 0으로 설정하면 객체를 삭제
				if(idsave==null) { //기억하기 체크 안했을 경우 
					useridCookie.setMaxAge(0);
				}
				//쿠키에 userid저장
				response.addCookie(useridCookie);
				
				//성공시 메인으로 이동
				response.sendRedirect(path+"/main.jsp");
			}else {
				//로그인 실패시 이동
				String msg = (String)rmap.get("msg");
				response.sendRedirect(path+"/login.jsp?msg="+URLEncoder.encode(msg,"utf-8"));
			}
			
		}else if(uri.contains("logout")) {
			HttpSession session = request.getSession();
			session.invalidate(); //모든 세션변수 삭제
			response.sendRedirect(path+"/login.jsp?msg="+URLEncoder.encode("로그아웃 되었습니다","utf-8"));
			
		}else if(uri.contains("info")) {
//			HttpSession session = request.getSession();
			String userid = request.getParameter("userid");
//			String userid = (String)session.getAttribute("userid");
			Member member = ms.selectOne(userid);
			request.setAttribute("member", member);
			request.getRequestDispatcher("/member/info.jsp").forward(request, response);
			
		}else if(uri.contains("list")) {
			//조회정보
			String findkey = request.getParameter("findkey");
			if(findkey!=null) {
				String findvalue = request.getParameter("findvalue");
				Map<String, String> findmap = new HashMap<>();
				findmap.put("findkey", findkey);
				findmap.put("findvalue", findvalue);
				List<Member> mlist = ms.selectList(findmap);
				request.setAttribute("mlist", mlist);
			}
			request.getRequestDispatcher("/member/list.jsp").forward(request, response);
			
		}else if(uri.contains("modiform")) {
			if(request.getAttribute("member")!=null) {
				request.setAttribute("member", request.getAttribute("member"));
			}else {
				String userid = request.getParameter("userid");
				Member member = ms.selectOne(userid);
				request.setAttribute("member", member);
			}
			request.getRequestDispatcher("/member/modify.jsp").forward(request, response);
			
		}else if(uri.contains("remove")) {
			String msg = ms.delete(request.getParameter("userid"));
			HttpSession session = request.getSession(); 
			session.invalidate();
			response.sendRedirect(path+"/main.jsp?msg="+URLEncoder.encode(msg, "utf-8"));
			
		}else if(uri.contains("modify")) {
			String saveDirectory = getServletContext().getInitParameter("savedir");
			int size = 1024 * 1024 * 3; 
			MultipartRequest multi = new MultipartRequest(request, saveDirectory, size, "utf-8", new DefaultFileRenamePolicy());
			
			String userid = multi.getParameter("userid");
			String passwd = multi.getParameter("passwd");
			String newpasswd = multi.getParameter("newpasswd");
			String zipcode = multi.getParameter("zipcode");
			String addrload = multi.getParameter("addrload");
			String addrdetail = multi.getParameter("addrdetail");
			String sysfilename = multi.getFilesystemName("photo");

			if(sysfilename==null) { //파일을 변경하지 않았을 경우
				sysfilename = multi.getParameter("filename");
			}
			Member member = new Member();
			member.setUserid(userid);
			member.setPasswd(passwd);
			member.setNewpasswd(newpasswd);
			member.setZipcode(zipcode);
			member.setAddrload(addrload);
			member.setAddrdetail(addrdetail);
			member.setFilename(sysfilename);
			
			Map<String, Object> rmap = ms.update(member);
			if((int)rmap.get("code") == 0) {
				response.sendRedirect(path+"/info.member?msg="+URLEncoder.encode((String)rmap.get("msg"), "utf-8")+"&userid="+userid);
			}else{
//				response.sendRedirect(path+"/modiform.member?userid="+userid+"&msg="+URLEncoder.encode((String)rmap.get("msg"), "utf-8"));
				
				//수정이 안됐을때 입력한 정보 가지고 돌아가기
				request.setAttribute("member", member);
				request.setAttribute("msg", rmap.get("msg"));
				request.getRequestDispatcher("/modiform.member").forward(request, response);
			}
			
		}
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

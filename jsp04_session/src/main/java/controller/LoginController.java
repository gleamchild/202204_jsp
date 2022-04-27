package controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import service.LoginService;

@WebServlet("*.log")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//서비스 객체 생성
	private LoginService ls = new LoginService();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		System.out.println(uri);
		String contextpath = request.getContextPath(); // contextpath /jsp04_login
		
		if(uri.contains("login")) {
			String userid = request.getParameter("userid");
			String passwd = request.getParameter("passwd");
			System.out.println(userid);
			System.out.println(passwd);
			//service호출
			Map<String, Object> rmap = ls.loginCheck(userid, passwd);
			
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
				response.sendRedirect(contextpath+"/view/main.jsp");
			}else {
				//로그인 실패시 이동
				String msg = (String)rmap.get("msg");
				response.sendRedirect(contextpath+"/view/login.jsp?msg="+URLEncoder.encode(msg,"utf-8"));
			}
		}else if(uri.contains("logout")) {
			HttpSession session = request.getSession();
			session.invalidate(); //모든 세션변수 삭제
			response.sendRedirect(contextpath+"/view/login.jsp?msg="+URLEncoder.encode("로그아웃 되었습니다","utf-8"));
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

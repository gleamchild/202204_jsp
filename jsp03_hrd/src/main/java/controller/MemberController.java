package controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.MemberDAO;
import dto.Member;

@WebServlet("/Member/*")
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		System.out.println(uri);
		MemberDAO mdao = new MemberDAO();
		
		if(uri.contains("add")) {
//			int custno = Integer.parseInt(request.getParameter("custno"));	//자동발번
			String custname = request.getParameter("custname");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			String joindate = request.getParameter("joindate");
			String grade = request.getParameter("grade");
			String city =  request.getParameter("city");
			Member member = new Member(custname, phone, address, joindate, grade, city);
			int cnt = mdao.insert(member);
			String msg="";
			if(cnt==1) {
				msg = "회원등록이 완료 되었습니다!";
			}else {
				msg = "회원등록이 실패 했습니다!";
			}
//			request.setAttribute("msg", msg);
//			request.getRequestDispatcher("/view/memberAdd.jsp").forward(request, response);
			
			String contextPath = request.getContextPath();
			msg = URLEncoder.encode(msg, "utf-8");
			response.sendRedirect(contextPath+"/Member/memberList.jsp?msg="+msg);
		}else if(uri.contains("memberList")) {
			List<Member> mlist = mdao.selectList();
			String msg = request.getParameter("msg");
			request.setAttribute("msg", msg);
			request.setAttribute("mlist", mlist);
			request.getRequestDispatcher("/view/memberList.jsp").forward(request, response);
		}else if(uri.contains("modify")) {
			int custno = Integer.parseInt(request.getParameter("custno"));
			Member member = mdao.selectOne(custno);
			String msg = request.getParameter("msg");
			request.setAttribute("msg", msg);
			request.setAttribute("member", member);
			request.getRequestDispatcher("/view/memberModify.jsp").forward(request, response);
		}else if(uri.contains("update")) {
			int custno = Integer.parseInt(request.getParameter("custno"));
			String custname = request.getParameter("custname");
			String phone = request.getParameter("phone");
			String address = request.getParameter("address");
			String joindate = request.getParameter("joindate");
			String grade = request.getParameter("grade");
			String city =  request.getParameter("city");
			Member member = new Member(custno, custname, phone, address, joindate, grade, city);
			int cnt = mdao.update(member);
			String msg="";
			if(cnt == 1) {
				msg = "회원정보가 수정되었습니다!";
			}else {
				msg = "회원정보 수정 실패 했습니다!";
			}
			msg = URLEncoder.encode(msg, "utf-8");
			response.sendRedirect("/jsp03_hrd/Member/modify?custno="+custno+"&msg="+msg);
		}else {
			System.out.println("잘못된 url");
		}
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

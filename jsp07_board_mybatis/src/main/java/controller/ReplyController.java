package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dto.Reply;
import service.ReplyService;

@WebServlet("/reply/*")
public class ReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ReplyService replyService = new ReplyService();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		System.out.println(uri);
		String path = request.getContextPath();
		
		if(uri.contains("selectList")) {
			String bnum_s = request.getParameter("bnum");
			if(bnum_s == null) return;
			int	bnum = Integer.parseInt(request.getParameter("bnum"));
			List<Reply> rlist =  replyService.selectList(bnum);
			
			request.setAttribute("rslist", rlist);
			request.getRequestDispatcher("/view/board/reply.jsp").forward(request, response);
		}else if(uri.contains("add")) {
			int bnum = Integer.parseInt(request.getParameter("bnum"));
			int restep = Integer.parseInt(request.getParameter("restep"));
			int relevel = Integer.parseInt(request.getParameter("relevel"));
			String content = request.getParameter("content");
			
			Reply reply = new Reply();
			reply.setBnum(bnum);
			reply.setContent(content);
			reply.setRestep(restep);
			reply.setRelevel(relevel);
			
			replyService.insert(reply);
			//게시물의 상세조회로 이동
			response.sendRedirect(path+"/board/detail?bnum="+bnum);
		}else if(uri.contains("remove")) {
			int rnum = Integer.parseInt(request.getParameter("rnum"));
			replyService.delete(rnum);
			
			int bnum = Integer.parseInt(request.getParameter("bnum"));
			response.sendRedirect(path+"/board/detail?bnum="+bnum);
		}else if(uri.contains("modiform")) {
			int rnum = Integer.parseInt(request.getParameter("rnum"));
			Reply reply = replyService.selectOne(rnum);
			
			request.setAttribute("reply", reply);
			request.getRequestDispatcher("/view/board/replymodify.jsp").forward(request, response);
		}else if(uri.contains("modify")) {
			int rnum = Integer.parseInt(request.getParameter("rnum"));
			String content = request.getParameter("content");
			
			Reply reply = new Reply();
			reply.setRnum(rnum);
			reply.setContent(content);
			replyService.update(reply);
			//modiform에서 받은 reply의 bnum을 뷰에서 히든으로 받아놓고 가져오기
			int bnum = Integer.parseInt(request.getParameter("bnum"));
			
			response.sendRedirect(path+"/board/detail?bnum="+bnum);
		}else {
			System.out.println("잘못된 uri");
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

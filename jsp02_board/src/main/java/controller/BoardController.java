package controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BoardDAO;
import dto.Board;

@WebServlet("*.board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		System.out.println(uri);
		BoardDAO bdao = new BoardDAO();
		
		if(uri.contains("add")) { // (uri.indexOf("add") != -1) add가 있는지 확인하는 방법
			String writer = request.getParameter("writer");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");		
			Board board = new Board(writer,subject,content);
			int cnt = bdao.insert(board);
			String msg="";		
			System.out.println(cnt+"건 추가");
			if(cnt==1) {
				msg = "게시물 저장완료.";
			}else {
				msg= "저장실패";
			}
			msg = URLEncoder.encode(msg, "utf-8");
			response.sendRedirect("/jsp02_board/selectList.board?msg="+msg);
			
		}else if(uri.contains("selectList")) {
			String findvalue = request.getParameter("findvalue");
			String findkey = request.getParameter("findkey");	
			if (findkey == null) findkey="writer";
			if (findvalue == null) findvalue="";
			List<Board> blist = bdao.selectList(findkey, findvalue);			
			request.setAttribute("blist", blist);
			request.getRequestDispatcher("/board/list.jsp").forward(request, response);
			
		}else if(uri.contains("modify")) {
			int seq = Integer.parseInt(request.getParameter("seq"));
			Board board = bdao.selectOne(seq);
			request.setAttribute("board", board);
			request.getRequestDispatcher("/board/modify.jsp").forward(request, response);
			
		}else if(uri.contains("delete")) {
			//parameterValues배열을 반환
			String[] delList = (request.getParameterValues("delList"));
			int cnt=0;
			String msg="";
			
			for(String seq:delList) {
				bdao.delete(Integer.parseInt(seq));
				cnt++;
			}
			if(cnt != 0) {
				msg = cnt+"건 삭제완료";
			}else {
				msg = "삭제 실패";
			}
			msg = URLEncoder.encode(msg, "utf-8");		
			response.sendRedirect("/jsp02_board/selectList.board");
			
		}else if(uri.contains("update")) {
			String writer = request.getParameter("writer");
			String subject = request.getParameter("subject");
			String content =  request.getParameter("content");
			int seq = Integer.parseInt(request.getParameter("seq"));
			Board board = new Board(writer,subject,content);
			board.setSeq(seq);
			int cnt = bdao.update(board);
			String msg="";
			if(cnt == 1) {
				msg = "수정완료";
			}else {
				msg = "수정실패";
			}
			msg = URLEncoder.encode(msg, "utf-8");
			response.sendRedirect("/jsp02_board/selectList.board?msg="+msg);	
		}
		
		
		
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

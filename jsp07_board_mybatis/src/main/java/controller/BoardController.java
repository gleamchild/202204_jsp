package controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import dto.Board;
import dto.Boardfile;
import dto.Reply;
import service.BoardService;
import service.BoardfileService;
import service.ReplyService;

@WebServlet("/board/*")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String uri =request.getRequestURI();
		System.out.println(uri);
		String path = request.getContextPath();
		BoardService boardService = new BoardService(); 
		BoardfileService boardfileService = new BoardfileService();
		
		if(uri.contains("add")) {
			//게시물등록
			/* 파일저장경로 : web.xml에서 읽어오기 */
			String saveDirectory = getServletContext().getInitParameter("savedir");
			int size = 1024*1024*20; // 20mbyte
			MultipartRequest multi = new MultipartRequest(request, saveDirectory, size, "utf-8", 
										new DefaultFileRenamePolicy()); 
			//new DefaultFileRenamePolicy() savedir에 같은 이름의 파일 있을시 자동리네임 
			//클라이언트 접속 ip정보
			String ip = request.getRemoteAddr();
			
			//board dto만들기
			Board board = new Board();
			String userid = multi.getParameter("userid");
			String subject = multi.getParameter("subject"); 
			String content = multi.getParameter("content");
			board.setUserid(userid);
			board.setSubject(subject);
			board.setContent(content);
			board.setIp(ip);
			
			//실제올라간 파일의 이름 리스트
			List<String> filenames = new ArrayList<String>();
			//파일의 이름의 모음
			Enumeration<String> files = multi.getFileNames();
			while (files.hasMoreElements()) { // 다음 엘리먼트 있다면true
				String name = files.nextElement(); // file엘리먼트의 name
				String filename = multi.getFilesystemName(name); // 실제 저장된 파일이름
				if(filename != null) filenames.add(filename);
			}
			
			//service 호출
			String msg = boardService.insert(board, filenames);
			
			//list로 이동
			response.sendRedirect(path+"/board/list?msg="+URLEncoder.encode(msg, "utf-8"));
		}else if(uri.contains("list")) {
			String findkey = request.getParameter("findkey");
			String findvalue = request.getParameter("findvalue");
			
			//현재페이지(curPage)가 null일때 처리
			String curPage_s = request.getParameter("curPage");
			int curPage = 1;
			if(curPage_s != null) curPage = Integer.parseInt(request.getParameter("curPage"));
			
			//조회조건 map만들기
			Map<String, Object> findmap = new HashMap<String, Object>();
			//null과 공백을 같이 조건넣어줄때는 null부터 체크해야한다 공백조건이 먼저 있을때 null이면 nullpointException발생
			if(findkey==null || findkey.equals("")) findkey = "userid"; 
			if(findvalue==null) findvalue = "";
			findmap.put("findkey", findkey);
			findmap.put("findvalue", findvalue);
			findmap.put("curPage", curPage);
		
			List<Board> blist = boardService.selectList(findmap);
			request.setAttribute("blist", blist);
			request.setAttribute("findmap", findmap); //페이징처리값 뷰로 보내기(서비스에서 findmap에 저장한 정보도 들어있음)
			request.getRequestDispatcher("/view/board/list.jsp").forward(request, response);
			
		}else if(uri.contains("detail")) {
			//상세페이지
			int bnum = Integer.parseInt(request.getParameter("bnum"));
			//조회수+1
			boardService.update_readcnt(bnum);
			
			//1)게시물조회
			Board board = boardService.seleclOne(bnum);
			//2)게시물파일 조회
			List<Boardfile> bflist = boardfileService.selectList(bnum);
			//3)댓글조회
			ReplyService replyService = new ReplyService();
			List<Reply> rlist = replyService.selectList(bnum);
			
			request.setAttribute("rlist", rlist);
			request.setAttribute("board", board);
			request.setAttribute("bflist", bflist);
			request.getRequestDispatcher("/view/board/detail.jsp").forward(request, response);
		}else if(uri.contains("remove")) {
			int bnum = Integer.parseInt(request.getParameter("bnum"));
			String msg = boardService.delete(bnum);
			
			response.sendRedirect(path+"/board/list.jsp?msg="+URLEncoder.encode(msg, "utf-8"));
		}else if(uri.contains("modiform")) {
			//수정폼으로 이동
			int bnum = Integer.parseInt(request.getParameter("bnum"));
			//board조회
			Board board = boardService.seleclOne(bnum);
			//boardfile조회
			List<Boardfile> bflist = boardfileService.selectList(bnum);
			
			request.setAttribute("board", board);
			request.setAttribute("bflist", bflist);
			request.getRequestDispatcher("/view/board/modify.jsp").forward(request, response);
		}else if(uri.contains("modify")) {
			String saveDirectory = getServletContext().getInitParameter("savedir");
			int size = 1024*1024*20; // 20mbyte
			MultipartRequest multi = new MultipartRequest(request, saveDirectory, size, "utf-8", 
										new DefaultFileRenamePolicy());
			//클라이언트 접속 ip정보
			String ip = request.getRemoteAddr();
			
			int bnum = Integer.parseInt(multi.getParameter("bnum"));
			String userid = multi.getParameter("userid");
			String subject = multi.getParameter("subject"); 
			String content = multi.getParameter("content");
			//board dto만들기
			Board board = new Board();
			board.setBnum(bnum);
			board.setUserid(userid);
			board.setSubject(subject);
			board.setContent(content);
			board.setIp(ip);
			
			//실제올라간 파일의 이름 리스트
			List<String> filenames = new ArrayList<String>();
			//파일의 이름의 모음
			Enumeration<String> files = multi.getFileNames();
			while (files.hasMoreElements()) { // 다음 엘리먼트 있다면true
				String name = files.nextElement(); // file엘리먼트의 name
				String filename = multi.getFilesystemName(name); // 실제 저장된 파일이름
				if(filename != null) filenames.add(filename);
			}
			
//			System.out.println("추가 파일리스트:"+filenames);
			//삭제할 파일리스트
			String[] removefiles = multi.getParameterValues("removefile");
			//저장할board, 저장할 파일 리스트, 삭제할 파일리스트 한번에 매개변수로 서비스에 넘기기
			boardService.update(board, filenames, removefiles);
			
			response.sendRedirect(path+"/board/detail?msg="+URLEncoder.encode("수정완료", "utf-8")+"&bnum="+bnum);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

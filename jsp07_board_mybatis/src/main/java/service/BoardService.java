package service;

import java.util.List;
import java.util.Map;

import dao.BoardDAO;
import dao.BoardfileDAO;
import dao.ReplyDAO;
import dto.Board;
import dto.Boardfile;

public class BoardService {
	private BoardDAO boardDAO = new BoardDAO();
	private BoardfileDAO boardfileDAO = new BoardfileDAO();
	private ReplyDAO replydao = new ReplyDAO();
	
	public String insert(Board board, List<String> filenames) {
		//게시물 저장
		int cnt = boardDAO.insert(board);
		
		//게시물 파일저장
		//bnum은 boardDAO.insert실행시 생성(boardMapper에서 selectkey로 세팅)
		for(String filename:filenames) {
			Boardfile boardfile = new Boardfile();
			boardfile.setBnum(board.getBnum());
			boardfile.setFilename(filename);
			cnt += boardfileDAO.insert(boardfile);
		}
		System.out.println(cnt+"건 boardfile 추가");
		String msg;
		if(cnt != 0) {
			msg = "게시물 추가 완료";
		}else {
			msg = "추가 실패";
		}
		return msg;
	}

	public List<Board> selectList(Map<String, Object> findmap) {
		//페이징처리
		//필요한 값 - (한페이지의 게시물수, 현재페이지)
		int perPage = 10; //한페이지의 게시물 수
		int curPage = (int)findmap.get("curPage"); // 현재페이지
		int startnum = (curPage - 1) * perPage + 1; //시작번호 
		int endnum = startnum + perPage - 1; //끝번호
		findmap.put("startnum", startnum);
		findmap.put("endnum", endnum);
		
		//조회게시물수
		int totCnt = boardDAO.select_totalcnt(findmap);
		int totPage = totCnt / perPage; // 전체 페이지수
		if(totCnt % perPage > 0) totPage++; // 나눈 값이 나머지가 있다면 1페이지 추가
		findmap.put("totPage", totPage);
		
		//페이징블럭 처리
		int perBlock = 10;
		int startPage = curPage - ((curPage - 1) % perBlock); //블럭의 시작페이지
		int endPage = startPage + perBlock - 1; //블럭의 끝페이지
		//endPage수정
		if(endPage>totPage) endPage = totPage;
		
		findmap.put("startPage", startPage); 
		findmap.put("endPage", endPage);
	
		System.out.println("findmap:"+findmap);
		return boardDAO.selectList(findmap);
	}

	public Board seleclOne(int bnum) {
		return boardDAO.selectOne(bnum);
	}

	public String delete(int bnum) {
		//댓글(자식테이블 - fk로 연결된 테이블) 삭제
		replydao.delete_bnum(bnum);
		
		//게시물파일(자식테이블) 삭제
		boardfileDAO.delete_bnum(bnum);
		
		int cnt = boardDAO.delete(bnum);
		String msg;
		if(cnt != 0) {
			msg = "삭제완료";
		}else {
			msg = "삭제실패";
		}
		return msg;
	}

	public void update(Board board, List<String> filenames, String[] removefiles) {
		//게시물수정
		boardDAO.update(board);
		
		//추가할파일들 추가
		for(String filename:filenames) {
			Boardfile boardfile = new Boardfile();
			boardfile.setBnum(board.getBnum());
			boardfile.setFilename(filename);
			boardfileDAO.insert(boardfile);
		}
		
		//파일들 삭제
		if(removefiles != null) {
			for(String bfnum:removefiles) {
				boardfileDAO.delete(Integer.parseInt(bfnum));
			}
		}
		
	}
	
	public void update_readcnt(int bnum) {
		boardDAO.update_readcnt(bnum);
	}
	
	
}

package service;

import java.util.List;

import dao.ReplyDAO;
import dto.Reply;

public class ReplyService {
	private ReplyDAO replyDAO = new ReplyDAO();
	
	public void insert(Reply reply){
		//댓글의순서(restep)
		reply.setRestep(reply.getRestep()+1);
		//댓글의레벨(relevel)
		reply.setRelevel(reply.getRelevel()+1);
		
		replyDAO.update_restep(reply);
		//기존댓글의 순서를 수정후 댓글추가
		
		int cnt = replyDAO.insert(reply);
		System.out.println(cnt);
	}

	public List<Reply> selectList(int bnum) {
		return replyDAO.selectList(bnum);
	}

	public void delete(int rnum) {
		replyDAO.delete(rnum);
	}

	public Reply selectOne(int rnum) {
		return replyDAO.selectOne(rnum);
	}

	public void update(Reply reply) {
		replyDAO.update(reply);
	}
	
	

	
}

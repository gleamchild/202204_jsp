package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Board;

public class BoardDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private String sql;
	
	
	public int insert(Board board) {
		int cnt=0;
		con = DBConn.getConnection();
		sql = "INSERT INTO board(seq, writer, subject, content) \r\n"
				+ "VALUES(b_seq.nextval, ?, ?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, board.getWriter());
			pstmt.setString(2, board.getSubject());
			pstmt.setString(3, board.getContent());
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("예외발생");
			e.printStackTrace();
		}
		return cnt;
	}
	
	public int update(Board board) {
		int cnt=0;
		con = DBConn.getConnection();
		StringBuffer sb = new StringBuffer();
		sb.append("UPDATE board\r\n");
		sb.append("SET writer = ?,\r\n");
		sb.append("    subject = ?,\r\n");
		sb.append("    content = ?,\r\n");
		sb.append("    modidate = sysdate\r\n");
		sb.append("WHERE seq= ?");
		
		try {
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, board.getWriter());
			pstmt.setString(2, board.getSubject());
			pstmt.setString(3, board.getContent());
			pstmt.setInt(4, board.getSeq());
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("예외발생");
			e.printStackTrace();
		}
		return cnt;
	}
	
	public int delete(int seq) {
		int cnt=0;
		con = DBConn.getConnection();
		sql = "DELETE FROM board\r\n"
				+ "WHERE seq= ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, seq);
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("예외발생");
			e.printStackTrace();
		}
		return cnt;
	}
	
	public List<Board> selectList(String findkey,String findvalue){
		List<Board> blist = new ArrayList();
		con = DBConn.getConnection();
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT * FROM board\r\n");
		sb.append("WHERE " + findkey + " like '%' || ? ||'%'\r\n");
		sb.append("ORDER BY " + findkey + " asc, seq desc");
		
		try {
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setString(1, findvalue);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Board board = new Board();
				board.setSeq(rs.getInt("seq"));
				board.setWriter(rs.getString("writer"));
				board.setSubject(rs.getString("subject"));
				board.setContent(rs.getString("content"));
				board.setRegidate(rs.getTimestamp("regidate"));
				board.setModidate(rs.getTimestamp("modidate"));
				blist.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("예외발생");
			e.printStackTrace();
		}
		return blist;
	}
	
	public Board selectOne(int seq){
		Board board = null;
		con = DBConn.getConnection();
		sql = "SELECT * FROM board\r\n"
				+ "WHERE seq = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, seq);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				board = new Board(rs.getInt("seq"),rs.getString("writer"),rs.getString("subject"),rs.getString("content"),rs.getTimestamp("regidate"),rs.getTimestamp("modidate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("예외발생");
			e.printStackTrace();
		}
		return board;
	}
	
}

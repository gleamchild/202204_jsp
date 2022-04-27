package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dto.Member;

public class MemberDAO {
	private Connection con;
	private PreparedStatement pstmt;
	private String sql;
	private int cnt;
	
	public int insert(Member member) {
		//db접속		
		con = DBConn.getConnetion();
		sql = "insert into member(userid, passwd, salt, zipcode, addrload, addrdetail, filename)\r\n"
				+ "values(?, ?, ?, ?, ?, ?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getUserid());
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getSalt());
			pstmt.setString(4, member.getZipcode());
			pstmt.setString(5, member.getAddrload());
			pstmt.setString(6, member.getAddrdetail());
			pstmt.setString(7, member.getFilename());
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	public int update(Member member) {
		con = DBConn.getConnetion();
		sql = "update member\r\n"
				+ "set passwd = ?,\r\n"
				+ "    salt = ?,\r\n"
				+ "    zipcode = ?,\r\n"
				+ "    addrload = ?,\r\n"
				+ "    addrdetail = ?,\r\n"
				+ "    filename = ?\r\n"
				+ "where userid = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getPasswd());
			pstmt.setString(2, member.getSalt());
			pstmt.setString(3, member.getZipcode());
			pstmt.setString(4, member.getAddrload());
			pstmt.setString(5, member.getAddrdetail());
			pstmt.setString(6, member.getFilename());
			pstmt.setString(7, member.getUserid());
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	//삭제
	public int delete(String userid) {
		con = DBConn.getConnetion();
		sql = "DELETE FROM member\r\n"
				+ "WHERE userid=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	//db에서정보 조회리스트
	public List<Member> selectList(Map<String, String> findmap){
		List<Member> mlist = new ArrayList<>();
		con = DBConn.getConnetion();
		sql = "select * from member\r\n";
		//공백으로 검색하면 데이터가 null인 값은 불러오지 못하는 문제 
		if(!findmap.get("findvalue").equals("")) {
			sql += "where "+ findmap.get("findkey") + " like '%'|| ? ||'%'\r\n";
		}
		sql += "order by "+ findmap.get("findkey");
		try {
			pstmt = con.prepareStatement(sql);
			if(!findmap.get("findvalue").equals("")) {
				pstmt.setString(1, findmap.get("findvalue"));
			}
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Member member = new Member(rs.getString("userid"),rs.getString("passwd"),rs.getString("salt"),rs.getString("zipcode"),rs.getString("addrload"),rs.getString("addrdetail"),rs.getString("filename"),rs.getTimestamp("regidate"));
				mlist.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return mlist;
	}	
	
	//한건 조회
	public Member selectOne(String userid) {
		Member member=null;
		con = DBConn.getConnetion();
		sql = "select * from member\r\n"
				+ "where userid = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				member = new Member(userid,rs.getString("passwd"),rs.getString("salt"),rs.getString("zipcode"),rs.getString("addrload"),rs.getString("addrdetail"),rs.getString("filename"),rs.getTimestamp("regidate"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return member;
	}
	
}

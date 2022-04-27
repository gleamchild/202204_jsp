package s02_member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//db를 접속 클래스
public class MemberDAO {
	Connection con;
	int cnt;
	String sql;
	PreparedStatement pstmt;
	
	//db에 insert를 실행
	int insert(Member member) {
		//db접속		
		con = DBConn.getConnection();
		sql = "INSERT INTO member(userid,passwd,name,email) VALUES(?,?,?,?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getUserid());
			pstmt.setString(2, member.getPasswd());
			pstmt.setString(3, member.getName());
			pstmt.setString(4, member.getEmail());
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	int update(Member member) {
		con = DBConn.getConnection();
		sql = "UPDATE member \r\n"
				+ "SET passwd = ?,\r\n"
				+ "    name = ?,\r\n"
				+ "    email = ?\r\n"
				+ "WHERE userid=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getPasswd());
			pstmt.setString(2, member.getName());
			pstmt.setString(3, member.getEmail());
			pstmt.setString(4, member.getUserid());
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	//삭제
	int delete(String userid) {
		con = DBConn.getConnection();
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
	List<Member> selectList(){
		List<Member> mlist = new ArrayList<>();
		con = DBConn.getConnection();
		sql = "SELECT * FROM member";
		try {
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				String userid = rs.getString("userid");
				String passwd = rs.getString("passwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				Member member = new Member(userid,passwd,name,email);
				mlist.add(member);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return mlist;
	}	
	
	//한건 조회
	Member selectOne(String userid) {
		Member member=null;
		con = DBConn.getConnection();
		sql = "SELECT * FROM member\r\n"
				+ "WHERE userid = ?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				member = new Member(userid,rs.getString("passwd"),rs.getString("name"),rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		return member;
	}
}

package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Member;

public class MemberDAO {
	public Member selectOne(String userid){
		Member member = null;
		Connection con = DBConn.getConnetion();
		String sql = "select * from member_03\r\n"
				+ "where userid = ?";
		try {
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				String id = rs.getString("userid");
				String passwd = rs.getString("passwd");
				String name = rs.getString("name");
				String email = rs.getString("email");
				member = new Member(id, passwd, name, email);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return member;
	}
}

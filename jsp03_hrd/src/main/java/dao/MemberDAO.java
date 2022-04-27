package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Member;
import dto.Sales;

public class MemberDAO {
	private Connection con;
	private String sql;
	private PreparedStatement pstmt;
	
	public int insert(Member member){
		try {
			con = DBConn.getConnection();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		int cnt=0;
		sql = "insert into member_tbl_02 \r\n"
				+ "values ((select nvl(max(custno) +1,100007) from member_tbl_02), \r\n"
				+ "    ?, ?, ?, ?, ?, ?)";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getCustname());
			pstmt.setString(2, member.getPhone());
			pstmt.setString(3, member.getAddress());
			pstmt.setString(4, member.getJoindate());
			pstmt.setString(5, member.getGrade());
			pstmt.setString(6, member.getCity());
			cnt = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	public List<Member> selectList(){
		List<Member> mlist = new ArrayList<Member>();
		try {
			con = DBConn.getConnection();
			sql = "SELECT custno, custname, phone, address, to_char(joindate, 'YYYY-MM-DD') joindate,\r\n"
					+ "    decode(grade, 'A', 'VIP', 'B', '일반', 'C', '직원') grade, city\r\n"
					+ "FROM member_tbl_02\r\n"
					+ "order by custno";
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int custno = rs.getInt("custno");
				String custname = rs.getString("custname");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				String joindate = rs.getString("joindate");
				String grade =  rs.getString("grade");
				String city = rs.getString("city");
				Member member = new Member(custno, custname, phone, address, joindate, grade, city);
				mlist.add(member);
			}
		} catch (Exception e) {		
			e.printStackTrace();
		}
		return mlist;
	}
	
	public List<Sales> selectSalesList(){
		List<Sales> slist = new ArrayList<Sales>();
		try {
			con = DBConn.getConnection();
			sql="SELECT mo.custno, me.custname, me.grade, mo.price\r\n"
					+ "FROM  money_tbl_02 mo INNER JOIN member_tbl_02 me ON (mo.custno = me.custno)\r\n"
					+ "ORDER BY price DESC";
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				if(rs.getInt("price")!=0) {
					int custno = rs.getInt("custno");
					String custname = rs.getString("custname");
					String garde = rs.getString("grade");
					int price = rs.getInt("price");
					Sales sales = new Sales(custno, custname, garde, price);
					slist.add(sales);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return slist;
	}
	
	public int update(Member member) {
		int cnt=0;
		try {
			con = DBConn.getConnection();
			StringBuffer sb = new StringBuffer();
			sb.append("UPDATE member_tbl_02\r\n");
			sb.append("SET custno = ?,\r\n");
			sb.append("custname = ?,\r\n");
			sb.append("phone = ?,\r\n");
			sb.append("address = ?,\r\n");
			sb.append("joindate =  ?,\r\n");
			sb.append("grade = ?,\r\n");
			sb.append("city =  ?\r\n");
			sb.append("WHERE custno= ?");
			pstmt = con.prepareStatement(sb.toString());
			pstmt.setInt(1, member.getCustno());
			pstmt.setString(2, member.getCustname());
			pstmt.setString(3, member.getPhone());
			pstmt.setString(4, member.getAddress());
			pstmt.setString(5, member.getJoindate());
			pstmt.setString(6, member.getGrade());
			pstmt.setString(7, member.getCity());
			pstmt.setInt(8, member.getCustno());
			cnt = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cnt;
	}
	
	public Member selectOne(int custno) {
		Member member = new Member();
		try {
			con = DBConn.getConnection();
			sql = "select custno, custname, phone, address, to_char(joindate, 'YYYY-MM-DD') joindate, grade, city\r\n"
					+ "from member_tbl_02\r\n"
					+ "where custno= ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, custno);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()) {
				int rcustno = rs.getInt("custno");
				String custname = rs.getString("custname");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				String joindate = rs.getString("joindate");
				String grade =  rs.getString("grade");
				String city = rs.getString("city");
				member = new Member(rcustno, custname, phone, address, joindate, grade, city);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return member;
	}
}

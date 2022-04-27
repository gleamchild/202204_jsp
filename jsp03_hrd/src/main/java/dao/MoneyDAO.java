package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dto.Money;
import dto.Sales;

public class MoneyDAO {
	private Connection con;
	private String sql;
	private PreparedStatement pstmt;
	
	public List<Map<String, Object>> salesList() {
		List<Map<String, Object>> slist = new ArrayList<Map<String, Object>>();
		sql = "select mn.custno, mb.custname, \r\n"
				+ "    decode(mb.grade,'A','VIP','B', '일반', 'C', '직원') grade,\r\n"
				+ "    sum(mn.price) price\r\n"
				+ "from money_tbl_02 mn join member_tbl_02 mb on(mn.custno = mb.custno)\r\n"
				+ "group by mn.custno, mb.custname, mb.grade\r\n"
				+ "order by price desc";
		try {
			pstmt = con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int custno = rs.getInt("custno");
				String custname = rs.getString("custname");
				String grade = rs.getString("grade");
				int price = rs.getInt("price");
				
				//Map을 이용한 방법
				Map<String, Object> map = new HashMap<>();
				map.put("custno", custno);
				map.put("custname", custname);
				map.put("grade", grade);
				map.put("price", price);
				slist.add(map);
			
				//dto를 이용한 방법
//				Sales sales = new Sales(custno, custname, grade, price);
//				slist.add(sales);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return slist;
	}
}

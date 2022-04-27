package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
	private static Connection con;
	public static Connection getConnetion() {
		try {
			if(con == null || con.isClosed()) {
				try {
					Class.forName("oracle.jdbc.OracleDriver");
					con = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/xe","hr","hr");
				} catch (ClassNotFoundException e) {
					System.out.println("드라이버 로딩 실패");
					e.printStackTrace();
				} catch (SQLException e) {
					System.out.println("접속 실패");
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch(Exception e){
			e.printStackTrace();
		}
		return con;
	}
}

package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
	private static Connection con;
	public static Connection getConnection() throws Exception{
		if(con == null || con.isClosed()) {
			Class.forName("oracle.jdbc.OracleDriver");
			con = DriverManager.getConnection
					("jdbc:oracle:thin:@//localhost:1521/xe","hr","hr");
		}
		return con;
	}
	
}

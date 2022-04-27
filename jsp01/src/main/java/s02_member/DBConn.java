package s02_member;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//db커넥션 클래스
public class DBConn {
	static Connection getConnection() {
		Connection con=null;
		//커넥션 객체를 얻는 방법
		
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String password = "hr";
		
		try {
			//오라클 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("로딩 완료");
			//커넥션 객체 생성
			con = DriverManager.getConnection(url, user, password);
			System.out.println("커넥션 완료");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("접속 실패");
			e.printStackTrace();
		}
		return con;
	}
}

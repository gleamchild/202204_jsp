package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//db커넥션 클래스
public class DBConn {
		//con객체를 싱글톤패턴으로 만든것
		//인스턴스를 하나만 생성하고 인스턴스가 있다면 재사용
	
	private static Connection con; //static메서드에서 사용할 변수 이므로 static붙여줘야 함
	
	static Connection getConnection() {	
		//Connection con=null; //지역변수로 해놓으면 메서드 호출될 때마다 null로 초기화 됨
		
		//커넥션 객체를 얻는 방법
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "hr";
		String password = "hr";
		
		try {
			//싱글톤패턴
			//con이 null이면 아직 드라이버로딩과 객체를 만들지 않은 상태
			//또는 con이 닫혔을때 연결 시켜줌
			if(con == null || con.isClosed()) {
				//오라클 드라이버 로딩
				Class.forName("oracle.jdbc.driver.OracleDriver");
				System.out.println("로딩 완료");
				//커넥션 객체 생성 getConnection메서드가 객체를 생성
				con = DriverManager.getConnection(url, user, password);
				System.out.println("커넥션 완료");		
			}
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

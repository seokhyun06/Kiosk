package application;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnect3 {
	
	public Connection conn;
	
	public Connection getConnection() {
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String id = "cafe3";
		String password = "cafe3";
		
		
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, password);
			System.out.println("DB 접속 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB 접속 실패");
		}
		return conn;
	
	
	}
}


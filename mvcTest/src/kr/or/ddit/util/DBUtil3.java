package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

// JDBC드라이버를 로딩하고 DB에 접속하여 Connection객체를 
// 반환하는 메서드로 구성된 class 만들기
// (ResourceBundle객체 이용하기)
public class DBUtil3 {
	private static ResourceBundle bundle;
	
	// 정적(static) 초기화 블럭
	static {
		bundle =  ResourceBundle.getBundle("kr.or.ddit.config.dbinfo");
		try {
			Class.forName(bundle.getString("driver"));
			
		} catch (Exception e) {
			System.out.println("드라이버 로딩 실패~~~");
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		Connection conn =null;
		try {
			conn = DriverManager.getConnection(
					bundle.getString("url"),
					bundle.getString("user"),
					bundle.getString("pass"));
		} catch (SQLException e) {
			System.out.println("DB 연결 실패~~~");
			e.printStackTrace();
		}
		return conn;
	}
}

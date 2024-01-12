package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// JDBC드라이버를 로딩하고 DB에 접속하여 Connection객체를 
// 반환하는 메서드로 구성된 class 만들기
public class DBUtil {
	// 정적(static) 초기화 블럭
	static {
		b =300;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
		} catch (Exception e) {
			System.out.println("드라이버 로딩 실패~~~");
			e.printStackTrace();
		}
	}
	
	//클래스로딩 > static변수 생성 > static 변수가 기본값으로 초기화 >static 변수의 명시적 초기화 > static 초기화 블럭실행
	// 객체 생성시 ==> 인스턴스변수 생성 ==> 인스턴스변수의 기본값 초기화 > 인스턴스변수의 명시적 초기화> 인스턴스 초기화블럭 실행
	// 생성자 실행 
	
	//실행순서 클래스 > 클래스변수 b > b=200 > 정적 초기화 블럭 b=300 : 명시적 초기화는 한줄만쓸수있어서
	// 결과를 도출해내야되는 경우 정적 초기화 블럭을 사용해놓는편
	int a = 100;
	static int b = 200;
	// 인스턴스 초기화 블럭 : 객체가 생성될떄마다 실행되는 블럭 (인스턴스 초기화블럭 먼저 실행되고 생성자가 실행됨)
	// 생성자 여러개일때 공통된 코드가 중복될 때 인스턴스 초기화블럭에다가 쓰면 먼저 실행되니 사용
	{
		
	}
	
	public static Connection getConnection() {
		Connection conn =null;
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "YHN","java");
		} catch (SQLException e) {
			System.out.println("DB 연결 실패~~~");
			e.printStackTrace();
		}
		return conn;
	}
}

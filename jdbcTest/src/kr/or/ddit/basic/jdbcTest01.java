package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
	JDBC(Java DataBase Connectivity) ==> java에서 DB자료를 처리하는 라이브러리
	
	- DB자료 처리 순서
	1. 드라이버 로딩 ==> JDBC라이브러리를 사용할 수 있도록 메모리로 읽어들이는 작업
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
	2. DB에 접속하기 ==> 접속이 완료되면 Connection객체가 생성되어 반환된다.
		DriverManager.getConnection() 메서드를 이용한다.
		
	3. 질의 ==> SQL문장을 DB서버로 보내서 결과를 얻어온다.
		(Statement객체나 PreparedStatement객체를 이용하여 작업한다.)
	
	4. 결과 처리 ==> 질의 결과를 받아서 원하는 작업을 수행한다.
		1) SQL문장이 'select'문일 경우에는
		   select한 결과가 ResultSet객체에 저장되어 반환한다.
		2) SQL문장이 'select'문이 아닐 경우(insert,update,delete 등)에는
		      정수값을 반환한다.(이 정수값은 보통 실행에서 성공한 레코드 수를 말한다.)
	5. 사용했던 자원 반납하기 ==> 각 객체의 close()메서드를 이용한다.
*/
public class jdbcTest01 {

	public static void main(String[] args) {
		// DB작업에 필요한 객체 변수 선언
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. DB연결 ==> Connevtion객체 생성
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/xe", "YHN", "java");
			
			// 3. 질의
			
			// 3-1. SQL문 작성
			String sql = "select lprod_id, lprod_gu, lprod_nm nm from lprod";
			// 3-2. Statement 객체 생성 ==> 질의하는 객체 생성
			//		(Connection객체를 이용하여 생성한다.)
			stmt = conn.createStatement();
			// 3-3. SQL문장을 DB서버로 보내서 결과를 얻어온다.
			//		(실행할 SQL문장이 'select'문이기 때문에 결과가 ResultSet객체에 저장되어 반환된다.)
			rs = stmt.executeQuery(sql);
			
			// 4. 결과 처리하기 ==> 한 레코드씩 화면에 출력하기
			// 		ResultSet객체에 저장된 데이터를 차례로 꺼내오려면 
			//		반복문과 next() 메서드를 이용하여 처리한다.
			System.out.println(" == 쿼리문 처리 결과 ==");
			
			// ResultSet객체의 next()메서드
			// ==> ResultSet객체의 데이터를 가리키는 포인터를 다음번째 레코드로 이동시키고
			//		그곳에 데이터가 있으면 true,없으면 false를 반환한다.
			while(rs.next()) {
				// 포인터가 가리키는 곳의 자료를 가져오는 방법
				// 형식1) ResultSet객체.get자료형이름("컬럼명" 또는 "aliasa명")
				// 형식2) ResultSet객체.get자료형이름(컬럼번호) ==> 컬럼번호는 1투서 시작
				System.out.println("LPROD_ID : " + rs.getInt("lprod_Id"));
				System.out.println("LPROD_GU : " + rs.getString(2));
				System.out.println("LPROD_nm : " + rs.getString("nm"));
			}
			
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null )try {rs.close();} catch(SQLException e) {};
			if(stmt!=null) try {stmt.close();} catch(SQLException e) {};
			if(conn!= null) try {conn.close();} catch(SQLException e) {};
		}

	}

}

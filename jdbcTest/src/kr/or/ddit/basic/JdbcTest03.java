package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

// 문제) lprod_id값을 2개 입력 받아서 두 값 중 작은 값 부터 큰 값 사이의 자료들을 출력하시오.




public class JdbcTest03 {

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		Scanner sc=  new Scanner(System.in);
		
		System.out.println("아이디 값 2개 입력하세요");
		int fId= sc.nextInt();
		int sId= sc.nextInt();
		int box = 0;
		if(fId>sId) {
			box = fId;
			fId = sId;
			sId = box;
		}
//		int min=fId;
//		int max=sId;
//		if(fId>sId) {
//			max=fId;
//			min=sId;
//		}
//		int min = Math.min(fId, sId);
//		int max = Math.max(fId, sId);
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe","YHN","java");
			
			String sql = "select lprod_id,lprod_gu, lprod_nm from lprod where lprod_id >="
			+fId+ "and lprod_id<="+sId;
			
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			boolean chk = false;
			System.out.println("=== 검 색 결 과 ===");
			while(rs.next()) {
				chk= true;
				System.out.println("ID : " + rs.getInt("lprod_id"));
				System.out.println("GU : " + rs.getString("lprod_gu"));
				System.out.println("NM : " + rs.getString("lprod_nm"));
				System.out.println("------------------------------------");
			}
			if(chk==false) {
				System.out.println("자료가 없습니다...");
			}
		} catch (SQLException e) {
			// TODO: handle exception
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(rs!=null) try {rs.close();}catch(SQLException e){};
			if(stmt!=null) try {stmt.close();}catch(SQLException e){};
			if(conn!=null) try {conn.close();}catch(SQLException e){};
		}

	}

}

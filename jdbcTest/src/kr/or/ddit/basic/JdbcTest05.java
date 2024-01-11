package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/*
  Lprod 테이블에 새로운 데이터 추가 하기
  
  Lprod_gu와 Lprod_nm은 직접 입력 받아서 처리하고,
  Lprod_id는 현재의 Lprod_id들 중에서 제일 큰 값보다 1 크게 한다.
  
     입력받은 Lprod_gu가 이미 등록되어 있으면 다시 입력 받아서 처리한다. 
*/
public class JdbcTest05 {

	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Scanner sc = new Scanner(System.in);
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "YHN", "java");
			
			String chk = "";
			String gu="";
			String nm ="";
			boolean c = true;
			while(c) {
					
				System.out.print("Lprod_gu 입력 >> ");
				gu = sc.next();
				
				System.out.print("Lprod_nm 입력 >> ");
				nm = sc.next();
				
				// gu 중복확인 쿼리
				chk = "select count(*) from lprod where lprod_gu=?";
				pstmt = conn.prepareStatement(chk);
				pstmt.setString(1, gu);
				rs = pstmt.executeQuery();
				
				rs.next();
				if(rs.getInt(1)==1) {
					System.out.println("중복 다시");
				}else {
					c= false;
				}
			}
			
			// 정보 저장 쿼리
			String insert = "insert into lprod(LPROD_ID,LPROD_GU,LPROD_NM) "
					+ " values ((select max(lprod_id) from lprod)+1,?,?)";
//			System.out.println(insert);
			
			pstmt= conn.prepareStatement(insert);
			
			pstmt.setString(1, gu);
			pstmt.setString(2, nm);
			
			int res = pstmt.executeUpdate();
//			System.out.println("반환값  : " + res);
			System.out.println("저장완료...");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			if(conn!=null) try {conn.close();}catch(SQLException e) {};
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {};
			if(rs!=null) try {rs.close();}catch(SQLException e) {};
		}
			
	}

}

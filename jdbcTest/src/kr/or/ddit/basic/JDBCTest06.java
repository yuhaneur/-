package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

// Statement객체를 사용하면 SQL injection 해킹에 노출될 수 있는 예제

// 계좌번호를 검색하는 프로그램 
public class JDBCTest06 {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt= null;
		ResultSet rs = null;
		
		try {
			conn = DBUtil.getConnection();
			
			System.out.println("=== 계좌 번호 검색하기===");
			System.out.print("검색할 계좌 번호 입력 >>");
			String bankNo = scan.nextLine();
			
			// Statement객체 이용하기
//			String sql = "select * from bankinfo"
//					+ " where bank_no = '"+ bankNo + "'";
//			// ' or 1=1 -- 
//			//' union select mem_id, mem_pass, mem_name, null from member --
//			stmt= conn.createStatement();
//			rs= stmt.executeQuery(sql);
			
			// PreparedStatement객체 이용하기
			String sql = "select * from bankinfo"
					+ " where bank_no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bankNo);
			rs = pstmt.executeQuery();
			
			
			System.out.println(" == 검 색 결 과 ==");
			System.out.println("계좌번호\t 은행이름\t 예금주명\t 개설날짜");
			System.out.println("--------------------------------");
			while(rs.next()) {
				String bNo = rs.getString("bank_No");
				String bName = rs.getString("bank_name");
				String uName = rs.getString("bank_user_name");
				String bDate = rs.getString("bank_date");
				System.out.println(bNo + "\t" + bName + "\t" + uName+ "\t" +bDate);
			}
			System.out.println("--------------------------------");
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(rs!=null) try {rs.close();}catch(SQLException e ) {}
			if(stmt!=null) try {stmt.close();}catch(SQLException e ) {}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e ) {}
			if(conn!=null) try {conn.close();}catch(SQLException e ) {}
		}

	}

}

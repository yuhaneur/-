package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

/*
	회원을 관리하는 프로그램을 작성하시오. (MYMEMBER테이블 이용)
	
	아래 메뉴의 기능을 모두 구현하시오. (CRUD 기능 구현 연습)
	메뉴 예시)
	  1. 자료 추가		==> insert(C)
	  2. 자료 삭제		==> delete(D)
	  3. 자료 수정		==> update(U)
	  4. 전체 자료 출력	==> select(R)
	  0. 작업 끝.
	  ----------------------------
	  
	  조건)
	 1) 자료 추가에서 '회원ID'는 중복되지 않는다. (중복되면 다시 입력 받는다.)
	 2) 자료 삭제는 '회원ID'를 입력 받아서 처리한다.
	 3) 자료 수정에서 '회원ID'는 변경되지 않는다.
*/
public class JDBCTest07 {
	Scanner sc = new Scanner(System.in);
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

//	 JDBCTest07 (){
//		 
//	 }

	public static void main(String[] args) {
		new JDBCTest07().start();

	}

	public void start() {
		conn = DBUtil.getConnection();

		while (true) {
			int num = display();

			switch (num) {
			case 1:
				insert();
				break;
			case 2:
				delete();
				break;
			case 3:
				update();
				break;
			case 4:
				infoMember();
				break;
			case 0:
				System.out.println("프로그램 종료...");
				return;
			default:
				System.out.println("다시입력해주세요...");
				break;
			}
		}
	}

	private void infoMember() {
		System.out.println("==회원정보 전체보기==");

		String sql = "select MEM_ID,MEM_PASS,MEM_NAME,MEM_TEL,MEM_ADDR"
				+ " from mymember "
				+ "order by 1";

		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			System.out.println("아이디\t 비밀번호\t 이름\t    전화번호\t\t    주소\t");
			System.out.println("----------------------------------------------------------------------");

			while (rs.next()) {
				String id = rs.getString("MEM_ID");
				String pass = rs.getString("MEM_PASS");
				String name = rs.getString("MEM_NAME");
				String tel = rs.getString("MEM_TEL");
				String addr = rs.getString("MEM_ADDR");

				System.out.print(id + "\t " + pass + "\t " + name + "\t " + tel + "\t    " + addr);
				System.out.println();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void update() {
		System.out.println("==회원정보 수정==");

		System.out.print("변경하고싶은 ID >>");
		String updateId = sc.next();
		

		String chk = "select count(*) from mymember where mem_id = ?";
		
		String sql = "update MYMEMBER SET " + " MEM_NAME=?,MEM_TEL=?,MEM_ADDR=? where MEM_ID=?";
		try {
			
			// 아이디 존재여부
			pstmt = conn.prepareStatement(chk);
			pstmt.setString(1, updateId);
			rs= pstmt.executeQuery();
			
			rs.next();
			if(rs.getInt(1)==0) {
				System.out.println("아이디가 존재하지 않습니다.");
				return;
			}
			
			System.out.print("변경할 이름>>");
			String updateName = sc.next();
			System.out.print("변경할 전화번호>>");
			String updateTel = sc.next();
			sc.nextLine();
			System.out.print("변경할 주소>>");
			String updateAddr = sc.nextLine();
			
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, updateName);
			pstmt.setString(2, updateTel);
			pstmt.setString(3, updateAddr);
			pstmt.setString(4, updateId);

			int result = pstmt.executeUpdate();
			System.out.println(result);
			System.out.println("수정완료!!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void delete() {
		System.out.println("==회원정보 삭제==");
		System.out.print("삭제할 ID >> ");
		String deleteId = sc.next();
		
		String chk = "select count(*) from mymember where mem_id = ?";
		
		String sql = "delete from mymember where mem_id= ? ";

		try {
			
			// 아이디 존재여부
			pstmt = conn.prepareStatement(chk);
			pstmt.setString(1, deleteId);
			rs=pstmt.executeQuery();
			
			rs.next();
			if(rs.getInt(1)==0) {
				System.out.println("아이디가 존재하지 않습니다.");
				return;
			}
			
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, deleteId);
			int result = pstmt.executeUpdate();
//			System.out.println(result);
			System.out.println("삭제완료!!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int display() {
		System.out.println(" 메뉴를 선택하세요");
		System.out.println(" 1. 자료 추가		");
		System.out.println(" 2. 자료 삭제		");
		System.out.println(" 3. 자료 수정		");
		System.out.println(" 4. 전체 자료 출력	");
		System.out.println(" 0. 작업 끝.");
		int select = sc.nextInt();
		sc.nextLine();
		return select;
	}

	public void insert() {
		System.out.println("==회원정보 등록==");

		boolean idChk = true;
		String id = null;
		try {
			String chk = "select count(MEM_ID) from mymember where mem_id = ?";
			pstmt = conn.prepareStatement(chk);

			while (idChk) {
				System.out.print("ID >>");
				id = sc.next();

				pstmt.setString(1, id);
				rs = pstmt.executeQuery();

				rs.next();
				if (rs.getInt(1) == 1) {
					System.out.println("ID중복 다시입력!!!");
				} else {
					idChk = false;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.print("비밀번호 >>");
		String pass = sc.next();
		System.out.print("이름 >>");
		String name = sc.next();
		System.out.print("전화번호 >>");
		String tel = sc.next();
		sc.nextLine();
		System.out.print("주소 >>");
		String addr = sc.nextLine();

		String sql = "insert into mymember values(?,?,?,?,?)";

		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setString(1, id);
			pstmt.setString(2, pass);
			pstmt.setString(3, name);
			pstmt.setString(4, tel);
			pstmt.setString(5, addr);

			int result = pstmt.executeUpdate();

//			System.out.println(result);
			System.out.println("저장 완료!!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

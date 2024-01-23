package kr.or.ddit.mvc.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.mvc.service.IMemberService;
import kr.or.ddit.mvc.service.MemberServiceImpl;
import kr.or.ddit.mvc.vo.MemberVO;

public class MemberController {
	// Service객체의 참조값이 저장될 변수 선언
	private IMemberService service;
	private Scanner sc;
	
	public MemberController() {
		sc = new Scanner(System.in);
		service = MemberServiceImpl.getInstance();
	}
	
	
	public static void main(String[] args) {
		new MemberController().startMember();
	}
	
	// 시작 메서드
	public void startMember() {
		System.out.println();
		System.out.println("=========================");
		System.out.println("   MyBatis용 회원관리 프로그램");
		System.out.println("=========================");
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
			case 5:
				detailUpdate();
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
	
	
	private void detailUpdate() {

		System.out.println("==회원정보 원하는거 수정하기==");
		System.out.print("수정할 ID>>");
		String id = sc.next();
		
			String updateField = null;	// 수정할 컬럼명이 저장될 변수
			String updateTitle = null;	// 수정할 내용의 제목이 저장될 변수
			int num ;
			do {
				System.out.println("1.비밀번호 수정");
				System.out.println("2.이름 수정");
				System.out.println("3.전화번호 수정");
				System.out.println("4.주소 수정");
				System.out.print("메뉴선택 >>");
				num = sc.nextInt();
				
				switch(num) {
					case 1 : updateField = "mem_pass";
							 updateTitle = "비밀번호";
						break;
					case 2 :updateField = "MEM_NAME";
							updateTitle = "회원이름";
						break;
					case 3 : updateField = "MEM_TEL";
							 updateTitle = "전화번호";
						break;
					case 4 :updateField = "MEM_ADDR";
							updateTitle = "회원주소";
						break;
					
					default : 
						System.out.println("수정항목을 잘못 선택했습니다. 다시 선택하세요...");
				}
				
			}while(num < 1 || num > 4);
			
			HashMap<String, String> memMap = new HashMap<String, String>();
			
			sc.nextLine(); 
			System.out.println();
			System.out.print("새로운 " + updateTitle + " >> ");
			String updateDate = sc.nextLine();
			memMap.put("field", updateField);
			memMap.put("data", updateDate);
			memMap.put("id", id);
			
			int cnt = service.updateMember2(memMap);
			
			if(cnt > 0) {
				System.out.println("수정 작업 성공!!!");
			}else {
				System.out.println("수정 작업 실패~~~");
			}
		
	}


	private void infoMember() {
		System.out.println("==회원정보 전체보기==");
		System.out.println("아이디\t 비밀번호\t 이름\t    전화번호\t\t    주소\t");
		System.out.println("----------------------------------------------------------------------");
		
		List<MemberVO> list = new ArrayList<MemberVO>();
		
		list = service.getAllMember();
		
		for (MemberVO memberVO : list) {
			String id = memberVO.getMem_id();
			String pass = memberVO.getMem_pass();
			String name = memberVO.getMem_name();
			String tel = memberVO.getMem_tel();
			String addr = memberVO.getMem_addr();
			
			System.out.print(id + "\t " + pass + "\t " + name + "\t " + tel + "\t    " + addr);
			System.out.println();
		}
		
		
	}


	private void update() {
		System.out.println("==회원정보 수정==");

		System.out.print("변경하고싶은 ID >>");
		String updateId = sc.next();
		
		int sel = service.getMemberCount(updateId);
		if(sel==0) {
			System.out.println("그런 회원 아이디는 없습니다.");
			return;
		}
		System.out.print("변경할 이름>>");
		String updateName = sc.next();
		System.out.print("변경할 전화번호>>");
		String updateTel = sc.next();
		sc.nextLine();
		System.out.print("변경할 주소>>");
		String updateAddr = sc.nextLine();
		
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(updateId);
		memVo.setMem_name(updateName);
		memVo.setMem_tel(updateTel);
		memVo.setMem_addr(updateAddr);
		
		int cnt ;
		cnt = service.updateMember(memVo);
		
	}


	private void delete() {
		System.out.println("==회원정보 삭제==");
		System.out.print("삭제할 ID >> ");
		String deleteId = sc.next();
		
		int sel = service.getMemberCount(deleteId);
		if(sel==0) {
			System.out.println("그런 회원 아이디는 없습니다.");
			return;
		}
		
		int count ;
		count = service.deleteMember(deleteId);
		if(count >0) {
			System.out.println("삭제 성공!!!");				
		}else {
			System.out.println("삭제 실패~~~");
		}
		
	}


	private void insert() {
		String id = null;
		int count = 1;
		while (count==1) {
			System.out.print("ID >>");
			id = sc.next();
			count = service.getMemberCount(id);
			if(count ==1) {
				System.out.println("중복 다시!");				
			}
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
		
		// 입력이 완료되면 입력한 자료들을 VO객체에 저장한다
		MemberVO memVo = new MemberVO();
		memVo.setMem_id(id);
		memVo.setMem_pass(pass);
		memVo.setMem_name(name);
		memVo.setMem_tel(tel);
		memVo.setMem_addr(addr);
		
		//service의 insert메서드를 호출해서 처리한다.
		int cnt = service.insertMember(memVo);
		
		if(cnt>0) {
			System.out.println("저장 성공!!!");
		}else {
			System.out.println("저장 실패~~~");
		}
	}
	


	public int display() {
		System.out.println(" 메뉴를 선택하세요");
		System.out.println(" 1. 자료 추가		");
		System.out.println(" 2. 자료 삭제		");
		System.out.println(" 3. 자료 수정		");
		System.out.println(" 4. 전체 자료 출력	");
		System.out.println(" 5. 자료 수정2		"); // ==> 원하는 항목만 수정하기
		System.out.println(" 0. 작업 끝.");
		int select = sc.nextInt();
		sc.nextLine();
		return select;
	}
	
	
}

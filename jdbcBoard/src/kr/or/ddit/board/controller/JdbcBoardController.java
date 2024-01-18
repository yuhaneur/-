package kr.or.ddit.board.controller;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.board.service.IJdbcBoardService;
import kr.or.ddit.board.service.JdbcBoardServiceImple;
import kr.or.ddit.board.vo.BoardVO1;

public class JdbcBoardController {
	private Scanner scan;
	private IJdbcBoardService service;
	
	public JdbcBoardController() {
		scan = new Scanner(System.in);
		service = JdbcBoardServiceImple.getInstance();
	}
	
	public static void main(String[] args) {
		new JdbcBoardController().boardStart();

	}
	
	// 시작 메서드
	public void boardStart() {
		String title = null;
		while(true) {
			int choice = displayMenu(title);
			switch(choice) {
				case 1:	insertBoard(); title=null;	// 새글작성
					break;
				case 2:	viewBoard(); title=null; 	// 게시글 보기
					break;
				case 3:	title = searchBoard();	// 검색
					break;
				case 0:		// 종료
					System.out.println("게시판 프로그램 종료");
					return;
				default :
					System.out.println("작업 번호를 잘못 입력했습니다.");
					System.out.println("다시 입력하세요...");
					break;
			}
		}
	}
	
	// 게시글을 검색할 제목을 입력 받아서 반환하는 메서드
	private String searchBoard() {
		scan.nextLine(); // 버퍼 비우기
		System.out.println();
		System.out.println("검색 작업");
		System.out.println("------------------------------------");
		System.out.print("- 검색할 제목 입력 : ");
		String title = scan.nextLine();
		return title;
	}
	
	// 게시글 내용을 보여주는 메서드
	private void viewBoard() {
		System.out.println();
		System.out.print("보기를 원하는 게시물 번호 입력 >>");
		int num = scan.nextInt();
		
		BoardVO1 bvo = service.getBoard(num);
		
		if(bvo==null) {
			System.out.println(num +"번의 게시글이 존재하지 않습니다....");
			return;
		}
		
		System.out.println();
		System.out.println(num + "번 글 내용");
		System.out.println("------------------------------------------");
		System.out.println("-  제 목 : " + bvo.getBoard_title());
		System.out.println("-  작성자 : " + bvo.getBoard_writer());
		System.out.println("-  내 용 : " + bvo.getBoard_content());
		System.out.println("-  작성일 : " + bvo.getBoard_date());
		System.out.println("-  조회수 : " + bvo.getBoard_cnt());
		System.out.println("------------------------------------------");
		System.out.println("메뉴 : 1. 수정   2. 삭제  3. 리스트로 가기");
		System.out.print("작업 선택>> ");
		int choiceNum = scan.nextInt();
		
		switch(choiceNum) {
			case 1 :	// 수정
				updateBoard(num); break;
			case 2 :	// 삭제
				deleteBoard(num); break;
			case 3 :	// 리스트로 가기
				return;
		}
		
		
	}
	
	// 게시글을 삭제하는 메서드
	private void deleteBoard(int boardNo) {
		int cnt = service.deleteBoard(boardNo);
		if(cnt > 0) {
			System.out.println(boardNo + "번 글이 삭제되었습니다...");
		}else {
			System.out.println(boardNo + "번 글이 삭제 작업 실패 !!!");
		}
	}
	
	// 게시글을 수정하는 메서드
	private void updateBoard(int boardNo) {
		System.out.println();
		System.out.println("수정 작업");
		System.out.println("----------------------------------");
		scan.nextLine(); // 입력 버퍼 비우기
		System.out.print("- 제 목 : ");
		String title = scan.nextLine();
		
		System.out.print("- 내 용 : ");
		String content = scan.nextLine();
		
		// 수정할 자료를 VO에 저장한다.
		BoardVO1 bvo = new BoardVO1();
		bvo.setBoard_no(boardNo);
		bvo.setBoard_title(title);
		bvo.setBoard_content(content);
		
		int cnt = service.updateBoard(bvo);
		
		if(cnt > 0) {
			System.out.println(boardNo + "번 글이 수정되었습니다...");
		}else {
			System.out.println(boardNo + "번 글이 수정 작업 실패!!! ");			
		}
	}
	
	// 새 글을 작성하는 메서드
	private void insertBoard() {
		scan.nextLine();	// 입력 버퍼 비우기
		System.out.println();
		System.out.println("새 글 작성하기");
		System.out.println("----------------------");
		System.out.print("제 목 >>");
		String title = scan.nextLine();

		System.out.print("작성자 >>");
		String writer = scan.nextLine();

		System.out.print("내 용 >>");
		String content = scan.nextLine();
		
		// 입력값을 VO에 저장하기
		BoardVO1 bvo = new BoardVO1();
		bvo.setBoard_title(title);
		bvo.setBoard_writer(writer);
		bvo.setBoard_content(content);
		
		int cnt = service.insertBoard(bvo);
		if(cnt>0) {
			System.out.println("새 글이 추가 되었습니다...");
		}else {
			System.out.println("새 글 추가에 실패했습니다...");
		}
	}
	
	
	
	
	// 게시판 목록을 보여주고 메뉴를 나타내며 사용자가 입력한 작업 번호를 입력 받아 반환하는 메서드
	private int displayMenu(String title) {
		List<BoardVO1> boardList = null;
		System.out.println();
		System.out.println("-------------------------------------------------------------");
		System.out.println(" No	        제 목            작성자 	조회수   ");
		System.out.println("-------------------------------------------------------------");
		
		if(title==null || title.isEmpty()) {
			 boardList = service.getAllBoardList();			
		}else {			
			 boardList = service.getSearchBoardList(title);
		}
		
		if(boardList ==null || boardList.size()==0) {
			System.out.println(" 출력할 게시글이 하나도 없습니다.");
		}else {
			for(BoardVO1 bvo : boardList) {
				System.out.println(bvo.getBoard_no() + "\t"
						+ bvo.getBoard_title() + "\t"
						+ bvo.getBoard_writer() +"\t"
						+ bvo.getBoard_cnt());
			}
		}
		System.out.println("-------------------------------------------------------------");
		
		System.out.println("메뉴 : 1. 새글작성     2. 게시글보기    3. 검색    0. 작업끝");
		System.out.print("작업선택 >>");
		return scan.nextInt();
	}

}

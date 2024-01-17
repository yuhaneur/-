package kr.or.ddit.board.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import kr.or.ddit.board.service.BoardService;
import kr.or.ddit.board.service.BoardServiceImpl;
import kr.or.ddit.board.vo.BoardVO;

public class BoardController {

	private BoardService service;
	private Scanner sc;
	Map<String , Integer> map = new HashMap<String, Integer>();
	public BoardController() {
		sc = new Scanner(System.in);
		service = BoardServiceImpl.getInstance();
	}
	public static void main(String[] args) {
		new BoardController().start();
	}

	public void start() {
		
		int num = displayMenu();
		while(true) {
			switch(num) {
				case 1 : insertBoard();
					break;
				case 2 : selectBoard();
					break;
				case 3 : num =selectTitle();// 이름 검색 이름안치고 엔터치면 모든게시글 나오게
					break;
				case 0 :
					System.out.println("작업 종료...");
					return;
				default : 
					System.out.println("잘못 입력했습니다.");
					break;
			}
		}
		
	}
	private int selectTitle() {
		System.out.println("검색 작업");
		System.out.println("--------------------------------------------");
		System.out.print("- 검색할 제목 입력 :");
		sc.nextLine();
		String surch = sc.nextLine();
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		list = service.selectTitle(surch);
		for (BoardVO boardVO : list) {
			int no = boardVO.getBoard_no();
			String title = boardVO.getBoard_title();
			String writer = boardVO.getBoard_writer();
			int cnt = boardVO.getBoard_cnt();
			
			System.out.println("  "+no +"\t" + title + "       " + writer + "\t\t" +cnt);
		}
		System.out.println("-------------------------------------------------------------");
		System.out.println("메뉴 : 1. 새글작성     2. 게시글보기    3. 검색    0. 작업끝");
		System.out.print("작업선택 >>");
		int sel = sc.nextInt();
		return sel;
	}
	private void selectBoard() {
		System.out.print("보기를 원하는 게시물 번호 입력 >>");
		int sel = sc.nextInt();
		map.put("boardNo", sel);
		List<BoardVO> list = new ArrayList<BoardVO>();
		list = service.selectBoard(sel);
		if(list.size()==0) {
			System.out.println("해당게시글은 없습니다 다시입력해주세요 .");
			return;
		}
		service.updateCnt(sel);
		for (BoardVO boardVO : list) {
			int boardNo = boardVO.getBoard_no();
			String title = boardVO.getBoard_title();
			String writer = boardVO.getBoard_writer();
			String content = boardVO.getBoard_content();
			String date = boardVO.getBoard_date();
			int cnt = boardVO.getBoard_cnt();
			
			System.out.println(boardNo + "번글 내용");
			System.out.println("------------------------------------------------------------");
			System.out.println("- 제  목 : "+ title);
			System.out.println("- 작성자 : "+ writer);
			System.out.println("- 내  용 : " + content);
			System.out.println("- 작성일 : " + date);
			System.out.println("- 조회수 : " + cnt);
			System.out.println("-------------------------------------------------------------");
			System.out.println("메뉴 : 1. 수정    2. 삭제    3. 리스트로 가기");
		}
		int num = sc.nextInt();
		
		
		switch(num) {
			case 1: updateBoard();
				break;
			case 2: deleteBoard();
				break;
			case 3: start();
				break;
		}
	}
	private void deleteBoard() {
		int board_no = map.get("boardNo");
		int cnt = service.deleteBoard(board_no);
		if(cnt>0) {
			System.out.println(board_no + "번글이 삭제되었습니다.");			
		}else {
			System.out.println("삭제실패!");
		}
		start();
		
	}
	private void updateBoard() {
		System.out.println("수정 작업하기");
		System.out.println("-----------------------------------");
		sc.nextLine();
		BoardVO boardVO = new BoardVO();
		int cnt ;
		System.out.print("제 목 :");
		String title = sc.nextLine();
		
		System.out.print("내 용 :");
		String content = sc.nextLine();
		
		boardVO.setBoard_title(title);
		boardVO.setBoard_content(content);
		boardVO.setBoard_no(map.get("boardNo"));
		
		cnt = service.updateBoard(boardVO);
		if(cnt>0) {
			System.out.println(map.get("boardNo")+"번글이 수정되었습니다.");
		}else {
			System.out.println("수정실패");
		}
	}
	private void insertBoard() {
		System.out.println("새글 작성하기");
		System.out.println("-----------------------------------");
		sc.nextLine();
		System.out.print("제 목 :");
		String title = sc.nextLine();
		
		System.out.print("작성자:");
		String writer = sc.nextLine();
		
		System.out.print("내 용 :");
		String content = sc.nextLine();
		
		int cnt ;
		
		BoardVO boardVO = new BoardVO();
		boardVO.setBoard_title(title);
		boardVO.setBoard_writer(writer);
		boardVO.setBoard_content(content);
		cnt = service.insertBoard(boardVO);
		
		if(cnt>0) {
			System.out.println("새글이 추가되었습니다.");
		}else {
			System.out.println("새글 추가 실패...");
		}
		start();
	}
	private int displayMenu() {
		System.out.println("-------------------------------------------------------------");
		System.out.println(" No	        제 목            작성자 	조회수   ");
		System.out.println("-------------------------------------------------------------");
		
		List<BoardVO> list = new ArrayList<BoardVO>();
		
		list = service.boardList();
		
		for (BoardVO boardVO : list) {
			int no = boardVO.getBoard_no();
			String title = boardVO.getBoard_title();
			String writer = boardVO.getBoard_writer();
			int cnt = boardVO.getBoard_cnt();
			
			System.out.println("  "+no +"\t" + title + "       " + writer + "\t\t" +cnt);
		}
		System.out.println("-------------------------------------------------------------");
		System.out.println("메뉴 : 1. 새글작성     2. 게시글보기    3. 검색    0. 작업끝");
		System.out.print("작업선택 >>");
		int num = sc.nextInt();
		
		return num;
	}
}

package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class HotelTest {

	private Scanner sc;
	private HashMap<Integer, Room> roomMap;
	
	public HotelTest() {
		sc = new Scanner(System.in);
		roomMap = new HashMap<Integer, Room>();
		
	}

	public static void main(String[] args) {
		HotelTest hotelTest = new HotelTest();
		hotelTest.roomInsert();
		hotelTest.startRoom();
	}

	public void startRoom() {
		System.out.println();
		System.out.println("*********************************************");
		System.out.println(" 호텔문을 열었습니다. 어서오십시요.");
		System.out.println("*********************************************");
		System.out.println();

		while (true) {
			int choice = displayMenu();

			switch (choice) {
			case 1: // 체크인
				checkIn();
				break;
			case 2: // 체크아웃
				checkOut();
				break;
			case 3: // 객실상태
				roomList();
				break;
			case 4: // 종료
				close();
				return;
			default:
				System.out.println("잘못 입력 하셨습니다.");
				System.out.println("다시 입력 해주세요.");
				break;
			}

		}

	}

	public void close() {
		System.out.println("*********************************************");
		System.out.println("호텔문을 닫았습니다.");
		System.out.println("*********************************************");
	}

	public void checkOut() {
		System.out.println("----------------------------------------------");
		System.out.println("   체크아웃 작업");
		System.out.println("----------------------------------------------");
		System.out.println("체크아웃 할 방 번호를 입력하세요.");
		int rno = sc.nextInt();
		
		if(!roomMap.containsKey(rno)) {
			System.out.println(rno+"호 객실은 존재하지 않습니다.");
			return;
		}
		if(roomMap.get(rno).getName().equals("-")) {
			System.out.println(rno+"호 객실에는 체크인 한 사람이 없습니다.");
		}else {
			System.out.println(rno+"호 객실의 "+roomMap.get(rno).getName()+"님 체크아웃을 완료하였습니다.");
			Room r = new Room(rno, roomMap.get(rno).getRname());
			r.setName("-");
			roomMap.put(rno, r);
		}
		
		
	}

	public void checkIn() {
		System.out.println("----------------------------------------------");
		System.out.println("체크인 작업");
		System.out.println("----------------------------------------------");
		System.out.println(" * 201~209 : 싱글룸\r\n" + 
				           " * 301~309 : 더블룸\r\n" + 
			               " * 401~409 : 스위트룸" );
		System.out.println("----------------------------------------------");
		System.out.println("방 번호 입력 >> ");
		int rno = sc.nextInt();
		
		if(!roomMap.containsKey(rno)) {
			System.out.println(rno+"호 객실은 존재하지 않습니다.");
			return;
		}
	
		if(!roomMap.get(rno).getName().equals("-")) {
			System.out.println(rno+"호 객실은 이미 손님이 있습니다.");
		}else {
			System.out.println("누구를 체크인 하시겠습니까?");
			System.out.println("이름 입력 >>");
			String newname = sc.next();
			Room r = new Room();
			r.setRno(rno);
			r.setRname(roomMap.get(rno).getRname());
			r.setName(newname);
			
			roomMap.put(rno, r);
			System.out.println("체크인이 완료되었습니다.");
		}
	

	}

	public void roomList() {
		System.out.println();
		Set<Integer> roomKeySet = roomMap.keySet();

		System.out.println("----------------------------------------------");
		System.out.println("		현재 객실 상태");
		System.out.println("----------------------------------------------");
		System.out.println("방 번호	 방 종류	 투숙객 이름");
		System.out.println("----------------------------------------------");
		
		for (Integer rno : roomKeySet) {
			Room r = (Room) roomMap.get(rno);
			System.out.println(r.getRno() + "\t" + r.getRname() + "\t" + r.getName());
		}

		System.out.println("----------------------------------------------");
	}

	public void roomInsert() {
	
		for (int i = 0; i < 9; i++) {
			Room r = new Room(201+i,"싱글룸");
			r.setName("-");
			roomMap.put(201+i,r);
		}
		for (int i = 0; i < 9; i++) {
			Room r = new Room(301+i,"더블룸");
			r.setName("-");
			roomMap.put(301+i,r);
		}
		for (int i = 0; i < 9; i++) {
			Room r = new Room(401+i,"스위트룸");
			r.setName("-");
			roomMap.put(401+i,r);
		}

	}

	// 메뉴
	public int displayMenu() {
		System.out.println("-----------------------------------------------------------");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1. 체크인    2. 체크아웃    3. 객실상태    4. 업무종료");
		System.out.println("-----------------------------------------------------------");
		System.out.println("선택>> ");
		return sc.nextInt();

	}

}

class Room {
	private int rno;
	private String rname;
	private String name;

	public Room() {

	}

	public Room(int rno, String rname) {
		super();
		this.rno = rno;
		this.rname = rname;
	}

	public int getRno() {
		return rno;
	}

	public void setRno(int rno) {
		this.rno = rno;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
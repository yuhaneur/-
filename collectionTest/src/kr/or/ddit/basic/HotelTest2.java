package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class HotelTest2 {

	private HashMap<Integer, Room2> hotelMap;
	private Scanner scan;
	
	public HotelTest2() {
		hotelMap = new HashMap<Integer, Room2>();
		scan = new Scanner(System.in);
		
		//HotelTset 클래스의 생성자에서는 방번호와 종류를 초기화한다.
		for(int i=2; i<=4; i++) {
			String type = null;
			switch(i) {
			case 2 : type = "싱글룸"; break;
			case 3 : type = "더블룸"; break;
			case 4 : type = "스위트룸"; break;
			}
			
			for(int j=1; j<=9; j++) {
				int num=i*100+j;
				hotelMap.put(num, new Room2(num, type));
			}
			
		}
		
	} //생성자 끝...
	
	public static void main(String[] args) {
		new HotelTest2().hotelStart();
	}
	
	//시작 메서드
	public void hotelStart() {
		System.out.println();
		System.out.println("*********************************************");
		System.out.println("	 호텔문을 열었습니다. 어서오십시요.");
		System.out.println("*********************************************");
		System.out.println();
		
		while(true) {
			int choice = displayMenu1();
			
			switch(choice) {
			case 1 :		//체크인
				checkIn();
				break;  
			case 2 :		//체크아웃
				checkOut();
				break;
			case 3 :		//객실상태
				displayRoomState();
				break;
			case 4 :		//종료
					System.out.println("*********************************************");
					System.out.println("		호텔문을 닫았습니다.");
					System.out.println("*********************************************");
				return;
			default :
				System.out.println("작업 번호를 잘못 입력 하셨습니다.");
				System.out.println("다시 입력하세요.");
			}
		}
	}
	
	//체크아웃 메서드
	private void checkOut() {
		System.out.println("----------------------------------------------");
		System.out.println("   체크아웃 작업");
		System.out.println("----------------------------------------------");
		System.out.println("체크아웃 할 방 번호를 입력하세요.");
		int num = scan.nextInt();
		
		//입력한 방번호가 있는지 검사
		if(!hotelMap.containsKey(num)) {
				System.out.println(num + "호 객실은 존재하지 않습니다...");
			return;
		}
		
		//해당 객실에 손님이 없는지 검사
		if(hotelMap.get(num).getGuestName()==null) {
			System.out.println(num+"호 객싱에는 체크인 한 손님이 업습니다...");
			return;
		}
		
		//체크 아웃 작업은 해당 객실의 투숙객 이름을 null로 변경한다.
		String name = hotelMap.get(num).getGuestName(); //현재 투숙객 이름 구하기
		hotelMap.get(num).setGuestName(null); //투숙객 이름을 null로 변경하기
		System.out.println(num + "호 객실의 " + name + "님이 체크아웃을 완료했습니다...");
		
	}
	
	//객실 상태를 출력하는 메서드
	private void displayRoomState() {
		System.out.println();
		
		//방번호를 순서대로 나오게 하기 위해서 방번호(Map의 key값)만
		//List에 저장한 후 정렬하여 사용한다.
		ArrayList<Integer> roomNumList = new ArrayList<Integer>(hotelMap.keySet());
		
		Collections.sort(roomNumList); //방번호 오름차순 정렬
		
		
		System.out.println("----------------------------------------------");
		System.out.println("		현재 객실 상태");
		System.out.println("----------------------------------------------");
		System.out.println("방 번호	 방 종류              투숙객 이름");
		System.out.println("----------------------------------------------");
		
		// List에서 방번호를 하나씩 차례로 꺼내와 Map에서 해당 방번호와 같이 저장된
		// Room객체를 구해서 출력한다.
		
		for(int num : roomNumList) {
			Room2 r = hotelMap.get(num);
			System.out.println(r.getRoomNumber() + "\t"+ 
							   r.getRoomType()+ "\t"+"\t"+
							   (r.getGuestName()==null ? "-" : r.getGuestName()));
		}
		System.out.println("----------------------------------------------");
		System.out.println();
		
		
	}
	
	
	
	//체크인
	private void checkIn() {
		System.out.println("----------------------------------------------");
		System.out.println("	체크인 작업");
		System.out.println("----------------------------------------------");
		System.out.println(" * 201~209 : 싱글룸\r\n" + 
				           " * 301~309 : 더블룸\r\n" + 
			               " * 401~409 : 스위트룸" );
		System.out.println("----------------------------------------------");
		System.out.println("방 번호 입력 >> ");
		int num = scan.nextInt();
		
		//입력한 방번호가 있는지 검사
		if(!hotelMap.containsKey(num)) {
			System.out.println(num + "호 객실은 존재하지 않습니다...");
			return;
		}
		
		//입력한 방번호의 방에 손님이 있는지 검사
		if(hotelMap.get(num).getGuestName()!=null) {
			System.out.println(num+"호 객실에 손님이 이미 있습니다...");
			return;
		}
		System.out.println();
		System.out.println("누구를 체크인 하시겠습니까?");
		System.out.print("이름 입력 >> ");
		String name = scan.next();
		
		//입력받은 투숙객이름을 해당 객실의 투숙객 명단에 저장한다.
		hotelMap.get(num).setGuestName(name);
		
		System.out.println(num+"호 객실에 체크인을 완료했습니다...");
	}
	
	
	//메뉴를 출력하고 작업 번호를 입력받아 반환하는 메서드
	private int displayMenu1() {
		System.out.println("----------------------------------------------");
		System.out.println("어떤 업무를 하시겠습니까?");
		System.out.println("1. 체크인    2. 체크아웃    3. 객실상태    4. 업무종료");
		System.out.println("----------------------------------------------");
		System.out.println("선택>> ");
		return scan.nextInt();
		
	}
	
	
	

}

class Room2 {
	private int roomNumber;
	private String roomType;
	private String guestName;
	
	public Room2() {
		
	}
	
	public Room2(int roomNumber, String roomType) {
		super();
		this.roomNumber = roomNumber;
		this.roomType = roomType;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public String getGuestName() {
		return guestName;
	}

	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	
	
	
}
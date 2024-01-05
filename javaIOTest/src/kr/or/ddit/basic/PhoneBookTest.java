package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;


public class PhoneBookTest {
	// 1) 6.전화번호 저장 메뉴를 추가하고 구현한다.
	//  저장 파일명은 phoneBook.data 로 한다.
	// 2) 프로그램이 시작될 때 저장된 파일이 있으면 그 데이터를 읽어와 Map에 저장한다.
	// 3) 프로그램을 종료할 때 Map의 데이터가 변경(추가, 수정, 삭제)되었으면 파일로 저장 후 종료 
	private HashMap<String,Phone> phoneBookMap;
	private Scanner scan;
	private String fileName = "d:/d_other/phoneBook.data";
	
	// 데이터가 변경되었는지 여부를 나타내는 변수
	// (데이터가 변경되면 true, 그렇지 않으면 false가 저장된다.)
	private boolean dataChk = false;
	
	public PhoneBookTest() {
		scan = new Scanner(System.in);
//		phoneBookMap = new HashMap<String,Phone>();
		
		// 파일 내용을 읽어와 Map에 저장하기
		phoneBookMap=load();
		
		if(phoneBookMap==null) { // 파일이 없거나 잘못되었을 때...
			phoneBookMap = new HashMap<String,Phone>();
		}
	}
		
		
	public static void main(String[] args) {
		
		new PhoneBookTest().startPhoneBook();
	}
	
	//시작 메서드
	public void startPhoneBook() {
		try {
			ObjectInputStream oin = new ObjectInputStream(
					new BufferedInputStream(
							new FileInputStream(fileName)
							)
					);
			Object obj = null ; // 읽어온 객체가 저장될 변수
			while( (obj= oin.readObject()) != null) {
				Phone p = (Phone) obj;
				phoneBookMap.put(p.getName(), p);
			}
			oin.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		System.out.println();
		System.out.println("++++++++++++++++++++++++");
		System.out.println("");
		System.out.println("++++++++++++++++++++++++");
		while(true) {
			int choice=displayMenu();
			
			switch(choice) {
			case 1:	//등록
				insert();
				break;
			case 2: //수정
				update();
				break;
			case 3:  //삭제
				delete();
				break;
			case 4:  //검색
				search();
				break;
			case 5:  //전체조회
				displayAll();
				break;
			case 6:  //전체조회
				save();
				break;
			case 0:  //종료
				if(dataChk==true) {
					save();
				}
				System.out.println("종료");
				return;
			default :
				System.out.println("잘못 입력");
				System.out.println("다시입력");
			}
			
			
		}
		
	}
	
	// 전화번호 정보가 저장된 파일을 읽어오는 메서드
	private HashMap<String, Phone> load(){
		HashMap<String, Phone> pMap = null; // 읽어온 데이터가 저장될 변수
		
		File file = new File(fileName); // 파일이있는지 검사
		if(!file.exists()) { // 저장된 파일이 없으면..
			return null;
		}
		
		ObjectInputStream oin = null;
		try {
			// 입력용 스트림 객체 생성
			oin = new ObjectInputStream(new BufferedInputStream(new FileInputStream(file)));
			
			// 방법1로 저장했을 때
			// pMap = (HashMap<String, Phone>) oin.readObject();
			// -----------------------------------------------
			
			// 방법2로 저장했을 때
			Object obj;
			
			// 파일에서 읽어온 데이터가 저장될 Map객체 생성
			pMap = new HashMap<String, Phone>();
			while((obj=oin.readObject())!=null) {
				Phone p = (Phone) obj;
				pMap.put(p.getName(),p);
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			// 스트림 닫기
			if(oin!=null) try {oin.close();}catch(IOException e) {}
		}
		
		return pMap;
	}
	
	// 저장
	private void save() {
		System.out.println("저장을 시작합니다.");
		ObjectOutputStream oout = null;
		try {
			// 객체 출력(저장)용 스트림 객체 생성
			oout = new ObjectOutputStream(
				 new BufferedOutputStream(
						 new FileOutputStream(fileName)
						)
					);
			// 방법1)Map객체를 파일로 저장한다.
//			oout.writeObject(phoneBookMap);
			
			// 방법 2) Map의 데이터를 하나씩 꺼내서 저장한다.
//			for(String name : phoneBookMap.keySet()) {
//				Phone p = phoneBookMap.get(name);
//				oout.writeObject(p);
//			}
//			oout.writeObject(null);
			
			// 방법 2-1)
			Set<String> set = phoneBookMap.keySet();
			
			Iterator<String > it = set.iterator();
			while(it.hasNext()) {
				String key = it.next();
				Phone p = (Phone) phoneBookMap.get(key);
				oout.writeObject(p);
			}

			oout.writeObject(null);
			
			dataChk=false;
			
			System.out.println("저장이 완료되었습니다.");
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 사용 했던 스트림 닫기
			if(oout!=null) try {oout.close();}catch(IOException e){}
		}
			
		
	}
	

	//검색
	private void search() {
		System.out.println();
		System.out.println("검색할 전화번호 정보를 입력하세요...");
		System.out.println("이름 >>");
		String name = scan.next();
		if(!phoneBookMap.containsKey(name)) {
			System.out.println(name+"씨의 전화번호 정보가 없습니다.");
			return;
		}
		
		Phone p = phoneBookMap.get(name);
		System.out.println(name+"씨의 전화번호 정보");
		System.out.println("-----------------------");
		System.out.println("이 름 : "+p.getName());
		System.out.println("전 화 : "+p.getTel());
		System.out.println("주 소 : "+p.getAddr());
		System.out.println("-----------------------");
		System.out.println();
		
	}
	
	
	//삭제
	private void delete() {
		System.out.println();
		System.out.println("삭제할 전화번호 정보를 입력하세요...");
		System.out.println("이름 >>");
		String name = scan.next();
		
		if(!phoneBookMap.containsKey(name)) {
			System.out.println(name+"씨의 전화번호 정보가 없습니다.");
			return;
		}
		phoneBookMap.remove(name);
		
		dataChk=true;
		
		System.out.println(name+"씨의 전화번호 정보 삭제 성공!!!");
	}
	
	
	//전화번호 정보를 수정하는 메서드
	private void update() {
		System.out.println();
		System.out.println("수정할 전화번호 정보를 입력하세요...");
		System.out.println("이름>> ");
		String name = scan.next();
		
		// 해당 이름이 등록되어 있지 않으면 수정 작업을 못한다.
		if(!phoneBookMap.containsKey(name)) {
			System.out.println(name +"씨의 전화번호 정보가 없습니다...");
			return;
		}
		System.out.println("새로운 전화번호 >> ");
		String newTel = scan.next();
		scan.nextLine(); //입력 버퍼 비우기
		System.out.println("새로운 주소 >> ");
		String newAddr = scan.nextLine();
		
		//같은 key값에 새로운 전화번호 정보를 저장한다. ==>수정 작업
		phoneBookMap.put(name, new Phone(name, newTel, newAddr));
		
		dataChk=true;
		
		System.out.println(name + "씨의 전화번호 정보 수정 완료!!!");
	}
	
	
	//전체 자료를 출력하는 메서드
	private void displayAll() {
		System.out.println();
		
		//Map의 전체 key값 정보 구하기...
		Set<String> phoneKeySet = phoneBookMap.keySet();
		
		System.out.println("-----------------------------------");
		System.out.println("번호		이름		전화번호		주소");
		System.out.println("-----------------------------------");
		
		if(phoneKeySet.size()==0) {
			System.out.println(" 등록된 전화번호 정보가 하나도 없습니다...");
		}else {
			int num = 0;
			//key값 개수만큼 반복
			for(String name : phoneKeySet) {
				num++; //번호증가
				
				//key값(name)을 이용하여 value값(Phone 객체)을 구한다.
				Phone p = (Phone) phoneBookMap.get(name);
				System.out.println(num+"\t"+p.getName()+"\t"+p.getTel()+"\t"+p.getAddr());
			}
		}
		System.out.println("---------------------------------------");
		System.out.println("출력끝");
	}
	
	
	//등록
	//이미 등록된사람 등록안됨
	private void insert() {
		System.out.println();
		System.out.println("새롭게 등록할 전화번호를 입력하세요...");
		System.out.println("이름>> ");
		String name=scan.next();
		
		//검사
		if(phoneBookMap.containsKey(name)) {
			System.out.println(name + "씨의 전화번호 정보가 이미 있습니다.");
			System.out.println("등록작업을 마칩니다...");
			return;
		}
		
		System.out.print("전화번호>> ");
		String tel = scan.next();
		
		//입력 버퍼 비우기
		scan.nextLine();   //next가 저정안한 enter를 버려준다 <-next랑 nextLine를 같이 사용할때 주의하기!
		System.out.print("주소>> ");
		String addr = scan.nextLine();
		
		
		
		// 입력 받은 자료를 이용하여 Phone객체를 생성하고
		// 생성된 Phone객체를 Map에 추가한다.
//		Phone p = new Phone(name, tel, addr);
//		phoneBookMap.put(name, p);
		
		phoneBookMap.put(name, new Phone(name, tel, addr));
		
		dataChk=true;
		
		System.out.println("'"+name+"' 전화번호 등록 완료!!!");
	}
	
	/*
	 * Scanner의 메서드들의 특징
	 * - next(), nextInt(), nextDouble()....
	 * ==> 사이띄기,  Tab키, Enter키를 구분문자로 분리해서 분리된 자료만 읽어간다.
	 * 
	 * - nextLine()
	 * ==> 한 줄 단위로 읽어간다. 
	 * 즉, 자료를 입력하고 Enter키를 누르면 Enter키까지 읽어가서
	 * 	  Enter키를  뺀 나머지 데이터를 반환한다.
	 */
	
	
	
	
	//메뉴를 출력하고 작업 번호를 
	private int displayMenu() {
		System.out.println();
		System.out.println("== 메 뉴 ==");
		System.out.println("1. 전화번호 등록");
		System.out.println("2. 전화번호 수정");
		System.out.println("3. 전화번호 삭제");
		System.out.println("4. 전화번호 검색");
		System.out.println("5. 전화번호 전체 출력");
		System.out.println("6. 전화번호 정보 저장");
		System.out.println("0. 프로그램 종료");
		System.out.println("----------------------");
		System.out.println("번호 입력 >> ");
		return scan.nextInt();
	}
	
	
}

class Phone implements Serializable{
	private String name;
	private String tel;
	private String addr;
	
	 
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	
	public Phone(String name, String tel, String addr) {
		super();
		this.name = name;
		this.tel = tel;
		this.addr = addr;
	}
	
	public Phone() {
	
	}
}







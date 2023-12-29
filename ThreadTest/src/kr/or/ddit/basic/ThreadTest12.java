package kr.or.ddit.basic;
/*
 	3개의 쓰레드가 각각 알파벳을 A~Z 까지 출력하느데
 	출력을 끝낸 순서대로 결과를 나타내는 프로그램을 작성하시오.
 */

public class ThreadTest12 {
	public static void main(String[] args) {
		DisplayCharacter[] charArr = new DisplayCharacter[] {
			new DisplayCharacter("홍길동"),	
			new DisplayCharacter("이순신"),	
			new DisplayCharacter("강감찬")	
		};
		
		for(DisplayCharacter dc : charArr) {
			dc.start();
		}
		
		for(DisplayCharacter dc : charArr) {
			try {
				dc.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		System.out.println();
		System.out.println("     경기 결과");
		System.out.println("순위 : " + DisplayCharacter.strRank);
	}
}

//A ~ Z 까지 출력하는 쓰레드
class DisplayCharacter extends Thread{
	// 출력한 순서대로 '쓰레드 이름'이 저장될 변수
	public static String strRank ="";
	private String name;
	
	// 생성자
	public DisplayCharacter(String name) {
		this.name=name;
	}
	
	@Override
	public void run() {
		for(char c = 'A'; c<='Z'; c ++) {
			try {
				Thread.sleep((int)Math.random()* 400);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
			System.out.println(name + "의 출력 문자 : " + c );
		}
		// 출력이 끝낸 순서대로 이름을 추가한다.
		strRank += name + "  ";
		System.out.println(name + " 출력 끝...");
		
	}
}
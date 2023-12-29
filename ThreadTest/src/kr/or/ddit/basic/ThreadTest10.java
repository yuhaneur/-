package kr.or.ddit.basic;

// yield() 메서드 연습

public class ThreadTest10 {

	public static void main(String[] args) {
		YieldTest th1 = new YieldTest("1번 쓰레드");
		YieldTest th2 = new YieldTest("2번 쓰레드");
		
		th1.start();
		th2.start();
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("1111111111111111111-------------------------");
		th1.work=false;
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("2222222222222222-----------------------------");
		th1.work=true;
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("3333333333333333333@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		th1.stop = true;
		th2.stop = true;
	}
}

// yield()메서드 연습용 쓰레드
class YieldTest extends Thread{
	public boolean stop = false;
	public boolean work = true;
	
	// 생성자
	public YieldTest(String name) {
		// this.a ==> 자기 자신 참조값이 저장된 변수
		// this() ==> 생성자에서 다른 생성자를 호출할 때 사용한다. (먼저 초기화된값을 보존시키면서 따로 초기화 시키고 싶을떄사용)
	
		// super ==> 부모의 참조값이 저장된 변수
		// super(); ==> 부모의 생성자를 호출할 때 사용한다.
		super(name); // 쓰레드의 이름을 설정하는 생성자 호출
	}
	
	@Override
	public void run() {
		while(!stop) { // stop이 true 이면 반복문이 종료된다.
			if(work) {
				// getName()메서드 ==> 현재 쓰레드의 name값을 반환
				System.out.println(getName() + " 작업 중 ...");
			}else {
				System.out.println(getName() + " 양보...");
				Thread.yield();
			}
		}
	}
}

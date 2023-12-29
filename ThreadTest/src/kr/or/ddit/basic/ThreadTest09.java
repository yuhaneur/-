package kr.or.ddit.basic;

// 쓰레드의 상태를 출력하는 예제
public class ThreadTest09 {

	public static void main(String[] args) {
		TargetThread target = new TargetThread();
		StatePrintThread th = new StatePrintThread(target);
		th.start();
	}
}

// 검사 대상이 되는 쓰레드(TargetThread)의 상태를 출력하는 쓰레드
class StatePrintThread extends Thread{
	private TargetThread target;
	
	// 생성자
	public StatePrintThread(TargetThread target) {
		this.target= target;
	}
	
	@Override
	public void run() {
		while(true) {
			// 쓰레드의 상태값 구하기 ==> getState()메서드 이용
			Thread.State state = target.getState();
			System.out.println("TargetThread의 상태값 : " + state);
			
			// TargetThread의 상태가 NEW상태(만들어졌는데 시작전) 이면...
			if(state == Thread.State.NEW) {
				target.start();	// TargetThread를 
			}
			
			//TargetThread의 상태가 종료(TERMINATED) 상태이면
			if(state ==Thread.State.TERMINATED) {
				break; // 반복문을 빠져나간다.
			}
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}
}

// 쓰레드 상태의 검사 대상이 되는 쓰레드
class TargetThread extends Thread{
	@Override
	public void run() {
		for(long i=1L;i<=20_000_000_000L;i++) {
			// 쓰레드가 처리하는 영역
			long sum = i + 1;
		}
		
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
		for(long i=1L;i<=20_000_000_000L;i++) {
			// 쓰레드가 처리하는 영역
			long sum = i + 1;
		}
	}
	
}
package kr.or.ddit.basic;
/*
	1~20억까지의 합계를 구하는 프로그램을 
	하나의 쓰레드가 단독으로 처리할 때와
	여러개의 쓰레드가 협력해서 처리할 때의 경과 시간을 비교해 보자.
	
 */
public class ThreadTest04 {

	public static void main(String[] args) {
		//단독으로 처리하는 쓰레드 객체 생성
		SumThread sm= new SumThread(1L, 2_000_000_000L);
		
		//여럿이 협력해서 처리하는 쓰레드 객체 생성
		SumThread[] smArr = new SumThread[] {
			new SumThread(            1L,   500_000_000L),
			new SumThread(  500_000_001L, 1_000_000_000L),
			new SumThread(1_000_000_001L, 1_500_000_000L),
			new SumThread(1_500_000_001L, 2_000_000_000L)
		};
		
		
		// 단독으로 처리하는 경우
		long startTime = System.currentTimeMillis();

		sm.start();
		try {
			sm.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		long endTime = System.currentTimeMillis();
		System.out.println("단독으로 처리할 떄의 경과 시간 : " + (endTime-startTime));
		System.out.println();
		
		// 여러 쓰레드가 협력해서 처리할 경우
		startTime = System.currentTimeMillis();
		for(SumThread sm1 : smArr) {
			sm1.start();
		}
		for(SumThread sm1 : smArr) {
			try {
				sm1.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
			
		}
		endTime = System.currentTimeMillis();
		System.out.println("여러 쓰레드가 처리할 떄의 경과 시간 : " + (endTime-startTime));
	}
}

class SumThread extends Thread{
	// 합계를 구할 영역의 시작값과 종료값이 저장될 변수 선언
	private long start;
	private long end;
	
	// 생성자에서 시작값과 종료값을 초기화한다.
	public SumThread(long start, long end){
		this.start=start;
		this.end=end;
	}
	@Override
	public void run() {
		long sum =0L;
		for(long i=start;i<=end;i++) {
			sum+=i;
		}
		System.out.println(start + " 부터" + end +" 까지의 합계 : " + sum);
	}
}

package kr.or.ddit.basic;

// 데몬 쓰레드 연습 ==> 자동 저장하기


public class ThreadTest08 {

	public static void main(String[] args) {
		AutoSaveThread autoSave = new AutoSaveThread();
		
		// 데몬 쓰레드 설정하기 ==> 반드시start()메서드 호출 전에 설정해야 한다.
		autoSave.setDaemon(true);
		System.out.println("데몬 여부  : " + autoSave.isDaemon());
		autoSave.start();
		try {
			for(int i=1;i<=20;i++) {
				System.out.println(i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("main 쓰레드 종료");
	}
}


// 자동 저장하는 쓰레드 작성 ==> 3초에 한번씩 저장하기
class AutoSaveThread extends Thread{
	private void save() {
		System.out.println("작업 내용을 저장합니다...");
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(3000);
				save();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}
	
}
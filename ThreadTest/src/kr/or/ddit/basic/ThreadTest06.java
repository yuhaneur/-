package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class ThreadTest06 {
	
	public static void main(String[] args) {
//		DataInput di = new DataInput();
//		di.start();
//		DataCountDown dc = new DataCountDown();
//		dc.start();
		Thread th1 = new DataInput();
		Thread th2 = new DataCountDown();
		
		th1.start();
		th2.start();
		
	}
}

// 데이터를 입력하는 쓰레드 클래스
class DataInput extends Thread{
	// 입력 여부를 확인하기 위한 변수 선언
	public static boolean inputCheck = false;
	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("아무거나 입력하세요...");
		inputCheck = true; // 입력이 완료되면 inputCheck를 true로 변경한다.
		System.out.println("입력한 값 :" + str);
	}
}

// 카운트 다운을 진행하는 쓰레드 클래스
class DataCountDown extends Thread{
	
	@Override
	public void run() {
		for(int i=10; i>=1;i--) {
			
			// 입력이 완료되었는지 여부를 검사해서 입력이 완료되었으면
			// 카운드 다운 쓰레드를 종료시킨다.
			if(DataInput.inputCheck == true) {				
				return; // run()메서드가 종료되면 쓰레드도 종료된다.
			}
			System.out.println(i+"초");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		System.out.println("땡");
		System.exit(0);
	}
}
package kr.or.ddit.basic;

import javax.swing.JOptionPane;

public class ThreadTest05 {

	public static void main(String[] args) {
		// 사용자로부터 데이터 입력 받기
		Runnable r = new Runnable() {	
			@Override
			public void run() {
				for(int i=10;i>=1; i--) {
					System.out.println(i+"초");
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				System.out.println("땡");
				
			}
		};
		Thread t = new Thread(r);
		t.start();
		String str = JOptionPane.showInputDialog("아무거나 입력하세요...");
		System.out.println("입력한 값 : " + str);
		
		
		
	}
}

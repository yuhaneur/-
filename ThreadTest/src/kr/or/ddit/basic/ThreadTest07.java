package kr.or.ddit.basic;

import javax.swing.JOptionPane;

/*
	컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.
	
	컴퓨터의 가위 바위 보는 난수를 이용해서 구하고
	사용자의 입력은 showInputDialog()메서드를 이용해서 입력 받는다.
	
	입력 시간은 5초로 제한하고 카운트 다운을 한다.
	5초 안에 입력이 없으면 게임에 진것으로 처리한다.
	5초 안에 입력이 완료되면 승패를 구해서 출력한다.
	
	
*/
public class ThreadTest07 {

	public static void main(String[] args) {
		Thread th = new User();
		Thread th1 = new Game();
		Thread th2 = new RCP();
		
//		String[] data= {"가위","바위","보"};
//		int index = (int)(Math.random() * 3); // 0~2 사이의 난수만들기
//		String com = data[index]; // 컴퓨터의 가위,바위,보 정하기
		
		th2.start();
		th.start();
		th1.start();
		int gugu = 5;
		
	}
}
class User extends Thread{
	public static boolean check=false;
	@Override
		public void run() {
			String user = null;
			do {
				user = JOptionPane.showInputDialog("가위 바위 보 중에 하나를 내세요");
			}while(!("가위".equals(user) || "바위".equals(user)|| "보".equals(user)));
			
			check = true;
			int sel=3;
			if(user.equals("바위")) {
				sel = 0;
			}else if(user.equals("보")) {
				sel = 1;
			}else if(user.equals("가위")) {
				sel = 2;
			}
//			System.out.println(user);
//			System.out.println(sel);
			
			if((sel==0 && RCP.num==1) ||(sel==1 && RCP.num==2) || ((sel==2 && RCP.num==0))) {
				System.out.println("-- 결  과  --");
				System.out.println("컴퓨터 : " + RCP.str);
				System.out.println("사용자 : " + user);
				System.out.println("결   과 : 당신이 졌습니다." );
				System.exit(0);
			}else if((sel==0 && RCP.num==2) ||(sel==1 && RCP.num==0) || (sel==2 && RCP.num==0)) {
				System.out.println("-- 결  과  --");
				System.out.println("컴퓨터 : " + RCP.str);
				System.out.println("사용자 : " + user);
				System.out.println("결   과 : 당신이 이겼습니다." );
				System.exit(0);
			}else if(sel==RCP.num) {
					System.out.println("-- 결  과  --");
					System.out.println("컴퓨터 : " + RCP.str);
					System.out.println("사용자 : " + user);
					System.out.println("결   과 : 비겼습니다." );
					System.exit(0);
			}
		}
}
class RCP extends Thread{
	public static String str = "";
	public static int num = (int) (Math.random()*3);
	@Override
		public void run() {
			if(num == 0) {
				str="바위";
			}else if(num == 1) {
				str="보";
			}else if(num==2){
				str="가위";
			}
//			System.out.println(str);
		}
}

class Game extends Thread{
	@Override
	public void run() {
		for(int i=15; i>=1;i--) {
			if(User.check==true) {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO: handle exception
				}
				return;
			}
			System.out.println(i+"초");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		System.out.println("   -- 결과 --      ");
		System.out.println("시간 초과로 당신이 졌습니다");
		
		System.exit(0);
	}
}

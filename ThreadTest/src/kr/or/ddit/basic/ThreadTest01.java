package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.List;

public class ThreadTest01 {

	public static void main(String[] args) {
		// '*' 문자 200개 출력, '$'문자 200개 출력
		
//		// 싱글 쓰레드		
//		for(int i=1;i<=200;i++) {
//			System.out.print("*");
//		}
//		System.out.println();
//		System.out.println();
//		
//		for(int j=1; j<=200;j++) {
//			System.out.print("$");
//		}
		List<String> list = new ArrayList<String>();
		for(int i=1;i<=50;i++) {
			list.add("-");
		}
		for(int i=0;i<50;i++) {
			try {
				Thread.sleep((int)(Math.random()*400));
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
			list.set(i, ">");
			if(i>0) {
				list.set(i-1, "-");				
			}
			for (String string : list) {
				System.out.print(string);
			}
			System.out.println();
		}

		
	}
}

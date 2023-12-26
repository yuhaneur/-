package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

/*
 *   문제3) '문제2'에서 입력하는 별명의 길이가 같은 것이 있을 수 있을 때
 *  	 결과를 출력하시오.
 */
public class ArrayListTest04 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		ArrayList<String> nick = new ArrayList();
		ArrayList nick1 = new ArrayList();

		for (int i = 0; i < 5; i++) {
			System.out.println("별명을 입력해 주세요.");
			String str = sc.nextLine();
			nick.add(str);
		}

		int bb = 0;
		String bbb = "";

		for (int i = 0; i < nick.size(); i++) {
			String aa = (String) nick.get(i);

			if (aa.length() > bb) {
				bb = aa.length();
				bbb = aa;
			}
		}
		
		for(int i=0; i < nick.size(); i++) {
			if(bbb.length()<=nick.get(i).length()) {
				nick1.add(nick.get(i));
			}
		}
		System.out.println(nick1);
		
		
//선생님풀이!		
		ArrayList<String> aliasList = new ArrayList<String>();
		
		System.out.println("별명을 5번 입력하세요");
		for (int i=1; i<=5; i++) {
			System.out.println(i + "번째 별명 : ");
			String alias = sc.nextLine();
			aliasList.add(alias);
		}
		
		// 제일 긴 별명의 길이가 저장될 변수를 선언하고, 첫번째 데이터릐 길이로 초기화 한다.
		int maxLength = aliasList.get(0).length();
		
		for (int i = 1; i < aliasList.size(); i++) {
			if(maxLength<aliasList.get(i).length()) {
				maxLength=aliasList.get(i).length();
			}
		}
		
		System.out.println("제일 긴 별명들");
		for(String alias : aliasList) {
			if(alias.length()==maxLength) {
				System.out.println(alias);
			}
		}
		
		
		
		
//탐구생활
//		int x = 0;
//		for (int i = 0; i < 4; i++) {
//			for (int j = i + 1; j < 5; j++) {
//				String str1 = (String) nick.get(i);
//				String str2 = (String) nick.get(j);
//				x = str1.length();
//				int y = str2.length();
//				if (x == y) {
//					aa.add(x);
//				}
//			}
//		}
//		ArrayList bb = new ArrayList();
//		for (int i : aa) {
//			if (!bb.contains(i)) {
//				bb.add(i);
//			}
//		}
//		System.out.println(bb);
//
//		for (int i = 0; i < nick.size(); i++) {
//			String str1 = (String) nick.get(i);
//			for (int j = 0; j < bb.size(); j++) {
//				if (str1.length() == (int) bb.get(j)) {
//					System.out.println(str1);
//				}
//			}
//		}
	}
}

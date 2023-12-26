package kr.or.ddit.basic;
/*
 * (문제) Set을 이용하여 숫자 야구 게임 프로그램을 잣성하시오.
 * 		컴퓨터의 숫자는 난수를 이용하여 구한다. (1~9사이의 값으로 한다.)
 *  	(스트라이크는 S, 볼은 B로 출력한다.
 *  
 *  예) 컴퓨터의 난수 ==> 9 5 7
 *      
 *      실헹 예시)
 *      숫자입력>> 3 5 6
 *      3 5 6==> 1S 0B
 *      
 *      숫자 입력>> 7 8 9
 *      7 8 9 ==> 0S 2B
 *      
 *      숫자 입력 >> 9 7 5
 *      9 7 5 ==> 1S 2B
 *      
 *      숫자 입력 ==> 9 5 7
 *      9 5 7 ==> 3S 0B
 *      
 *      4번째 만에 맞췄습니다...
 *      
 *      (1) 1~9 사이의 난수 3개를 만들어서 List에 저장한다.
 *      (2) 사용자로부터 숫자 3개를 입력 받아 List에 저장한다.
 *      (3) (1)번 값과 (2)번 값을 비교하여 스트라이크와 볼의 개수를 구한다.
 *      (4) 스트라이크의 개수가 3인지 비교해서 3이 아니면 (2)번부터 다시 처리한다.
 *      (5) 스트라이크의 개수가 3이면 몇번만에 맞췄는지를 출력한다.
 *
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class BaseBallTest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		ArrayList ranlist = new ArrayList();

		HashSet<Integer> ran = new HashSet<Integer>();
		while (ran.size() < 3) {
			ran.add((int) (Math.random() * (9 - 1 + 1) + 1));
		}
		
		for (int num : ran) {
			ranlist.add(num);
		}
		
		System.out.println(ranlist);
		System.out.println("야구 게임 시작");

		int cnt = 0;
		while (true) {
			cnt++;
			ArrayList gmem = new ArrayList();
			gmem.add(sc.nextInt());
			gmem.add(sc.nextInt());
			gmem.add(sc.nextInt());

			int s = 0;
			int b = 0;

			for (int i = 0; i < ranlist.size(); i++) {
				if (ranlist.get(i) == gmem.get(i)) {
					s++;
				} else if (ranlist.contains(gmem.get(i))) {
					b++;
				}
			}
			System.out.println("스트라이크 " + s + " 개  " + "볼 " + b + " 개");
			System.out.println("다시 맞춰 보세요");

			if (s == 3) {
				System.out.println(cnt + "번 만에 성공했습니다.");
				break;
			}

		}

	}
}

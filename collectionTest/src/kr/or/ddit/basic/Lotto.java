package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class Lotto {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		while (true) {
			
			System.out.println("---------------------------");
			System.out.println("Lotto 프로그램");
			System.out.println("---------------------------");
			System.out.println(" 1. Lotto 구입\r\n" + " 2. 프로그램 종료");
			System.out.println("===========================");
			System.out.print("메뉴 선택 : ");
			int num = sc.nextInt();
			if (num == 1) {

				System.out.println("1000원에 로또번호 하나입니다.");
				System.out.print("금액 입력 : ");
				int price = sc.nextInt(); // 금액 입력받고

				// 1000원 미만이면 구입안됨
				if (price < 1000) {
					System.out.println("입력 금액이 너무 적습니다. 로또번호 구입 실패!!!");
				}

				// 100000원 초과면 구입안됨
				else if (price > 100000) {
					System.out.println("입력 금액이 너무 많습니다. 로또번호 구입 실패!!!");
				}
				// 금액 만큼 로또 뽑기
				else {
					System.out.println("행운의 로또번호는 아래와 같습니다.");
					int p = price / 1000;
					for (int i = 1; i <=p ; i++) {
						HashSet<Integer> lotto = new HashSet<Integer>();
						while (lotto.size() < 6) {
							lotto.add((int) (Math.random() * (45 - 1 + 1) + 1));
						}
						ArrayList lottoP = new ArrayList(lotto);
						Collections.sort(lottoP);
						System.out.println(i+"."+lottoP);
					}
					int pp = price-(p*1000) ;
					System.out.println("받은 금액은 "+price+"원이고 거스름돈은 "+pp+"원입니다.");
				}continue;
			}
			if (num == 2) {
				System.out.println("감사합니다");
			}break;
		}

	}
}

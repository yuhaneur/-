package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class LottoStore {
	Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		new LottoStore().lottoStart();
			
	}
	
	// 시작 메서드
	public void lottoStart() {
		while(true) {
			int choice = displayMent();
			
			switch(choice) {
			case 1 : 
				buyLotto();
				break;
			case 2 : //종료
				System.out.println();
				System.out.println("감사합니다.");
				return;
			default :
				System.out.println("작업 번호를 잘못 입력했습니다.");
				System.out.println("다시 입력하세요...");
			}
		}
		
	}
	
	// 로또 
	private void buyLotto() {
		System.out.println();
		System.out.println("Lotto 구입 시작");
		System.out.println();
		System.out.println("1000원에 로또번호 하나입니다.");
		System.out.println("금액 입력 : ");
		int money = scan.nextInt();
		
		if(money<1000) {
			System.out.println("입력한 금액이 너무 적습니다. 로또번호 구매 실패!!");
			return;
		}
		if(money>=101000) {
			System.out.println("입력한 금액이 너무 많습니다. 로또번호 구매 실패!!");
			return;
		}
		
		Set<Integer> lottoSet = new HashSet<Integer>();
		
		int count = money / 1000; //구매할 매수
		System.out.println();
		
		System.out.println("행운의 번호는 아래와 같습니다.");
		 for(int i=1; i<=count; i++) {
			 //난수로 로또번호 만들기
			 while(lottoSet.size() <6) {
				 lottoSet.add((int)(Math.random()*45+1));
				 
			 }
			 ArrayList<Integer> lottoList = new ArrayList<Integer>(lottoSet);
			 Collections.sort(lottoList); //만들어진 번호 정렬하기
			 
			 System.out.println("로또번호"+ i+" : ");
			 for(int num : lottoList) {
				 System.out.print(num+" ");
			 }
			 System.out.println(); //줄바꿈
			 lottoSet.clear();  //이전 데이터 모두 삭제
		 }
		 System.out.println();
		 System.out.println("받은 금액은"+money+"이고 거스름돈은"+
				 			(money %1000)+"원 입니다.");
	}
	
	
	//메뉴를 출력하고 작업 번호를 입력 받아서 반환하는 메서드
	private int displayMent() {
		System.out.println();
		System.out.println("==========================");
		System.out.println("         	Lotto 프로그램");
		System.out.println("--------------------------");
		System.out.println("1. Lotto 구입");
		System.out.println("2. 프로그램 종료");
		System.out.println("========================== ");
		System.out.println("메뉴선택 : ");
		return scan.nextInt();
	}
}


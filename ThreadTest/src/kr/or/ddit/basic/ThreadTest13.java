package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 10마리의 말들이 경주하는 경마 프로그램 작성하기

말은 Horse라는 이름의 클래스로 구성하고,
이 클래스는 말이름(String), 말의 위치(int), 등수(int)를 멤버변수로 갖는다.
그리고, 이 클래스에는 등수를 오름차순으로 처리할 수 있는
기능이 있다.( Comparable 인터페이스 구현)

경기 구간은 1~50구간으로 되어 있다.

경기 중 중간 중간에 각 말들의 위치를 나타내시오.
예)
01번말 --->------------------------------------
02번말 ----->----------------------------------
...
10번말 ---------->-----------------------------
경기가 끝나면 등수 순으로 출력한다.
*/
public class ThreadTest13 {

	public static void main(String[] args) {
		Horse[] horseArr = new Horse[] { 
				new Horse("01번말"), new Horse("02번말"), new Horse("03번말"), 
				new Horse("04번말"),
				new Horse("05번말"), new Horse("06번말"), new Horse("07번말"), new Horse("08번말"), new Horse("09번말"),
				new Horse("10번말") };

		GameState gs = new GameState(horseArr);

		for (Horse h : horseArr) {
			h.start();
		}

		gs.start();

		for (Horse h : horseArr) {
			try {
				h.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		try {
			gs.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println();
		System.err.println("경기 끝...");
		System.out.println();

		// 등수의 오름차순으로 정렬하기
		// 배열 정렬하기 ==> Arrays.sort()메서를 사용한다.
		Arrays.sort(horseArr);

		for (Horse h : horseArr) {
			System.out.println(h);
		}
	}
}
	class Horse extends Thread implements Comparable<Horse> {
		public static int currentRank = 0; // 말의 현재 등수를 구하는 변수

		private String hname;
		private int location;
		private int rank;

		// 생성자
		public Horse(String name) {
			this.hname = name;
		}

		
		
		public String gethName() {
			return hname;
		}



		public void sethName(String name) {
			this.hname = name;
		}



		public int getLocation() {
			return location;
		}

		public void setLocation(int location) {
			this.location = location;
		}

		public int getRank() {
			return rank;
		}

		public void setRank(int rank) {
			this.rank = rank;
		}

		@Override
		public void run() {
			for (int i = 1; i <= 50; i++) {
				location = i; // 말의 현재위치 저장하기

				try {
					Thread.sleep((int) (Math.random() * 500));
				} catch (InterruptedException e) {
					// TODO: handle exception
				}
			}

			currentRank++;
			rank = currentRank;

		}

		@Override
		public int compareTo(Horse o) {
			return Integer.compare(this.rank, o.getRank());
		}

		@Override
		public String toString() {
			return "경주마" + hname + "은" + rank + "등 입니다.";
		}
		
	}

// 경기 중간 중간에 각 말들의 위치
	class GameState extends Thread {
		private Horse[] horseArr; // 경주에 참가하는 말들이 저장될 배열

		public GameState(Horse[] horseArr) {
			this.horseArr = horseArr;
		}

		@Override
		public void run() {
			while (true) {

				// 모든 말들의 경주가 끝났는지 여부 검사 (말 하나가 들어올떄마다 랭크가 1씩늘어나기떄문)
				if (Horse.currentRank == horseArr.length) {
					break;
				}

				// 배열 개수만큼 반복
				for (int i = 0; i < horseArr.length; i++) {
					System.out.print(horseArr[i].gethName() + " : ");
					for (int j = 1; j <= 50; j++) {
						if (j == horseArr[i].getLocation()) {
							System.out.print(">");
						} else {
							System.out.print("-");
						}
					}
					System.out.println();
				}

				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO: handle exception
				}
			}
		}
	}

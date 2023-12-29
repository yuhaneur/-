package kr.or.ddit.basic;

import java.util.ArrayList;
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
		List<Horse> horse = new ArrayList<Horse>();
		horse.add(new Horse("01번마"));
		horse.add(new Horse("02번마"));
		horse.add(new Horse("03번마"));
		horse.add(new Horse("04번마"));
		horse.add(new Horse("05번마"));
		horse.add(new Horse("06번마"));
		horse.add(new Horse("07번마"));
		horse.add(new Horse("08번마"));
		horse.add(new Horse("09번마"));
		horse.add(new Horse("10번마"));
		
		Print print = new Print(horse);
		for (Horse horse2 : horse) {
			horse2.start();
		}
		print.start();
		for (Horse horse2 : horse) {
			try {
				horse2.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
			}
		}
		
		try {
			print.join();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
	}
}

class Horse extends Thread implements Comparable<Horse>{
	private String name;
	private int location;
	private int rank;
	
	// 생성자
	public Horse(String name) {
		this.name= name;
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
		List<String> list = new ArrayList<String>();
		for(int i=1;i<=50;i++) {
			list.add("-");
		}
		for(int i=0;i<50;i++) {
			try {
				Thread.sleep((int)(Math.random()*200));
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
			list.set(i, ">");
			if(i>0) {
				list.set(i-1, "-");				
			}
			
			location = list.indexOf(">");
		}
	}
	
	@Override
	public int compareTo(Horse o) {
		if(this.getRank()> o.getRank()) {
			return 1;
		}else {
			return -1;
		}
	}
	
}

class Print extends Thread{
	List<Horse> list;
	
	public Print(List<Horse> list) {
		this.list=list;
	}
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep((int)(Math.random()*1000));
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
			for(int i=0;i<list.size();i++) {
				System.out.print(list.get(i).getName() + " : ");
				int position = list.get(i).getLocation();
				for(int j=0;j<position;j++) {
					System.out.print("-");
				}			
				System.out.print(">");
				for(int k=position;k<49;k++) {
					System.out.print("-");
				}
				System.out.println();
			}
			
		}
	}
}




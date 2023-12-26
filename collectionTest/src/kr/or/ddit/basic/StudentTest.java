package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/*
 * 문제) 학번, 이름, 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는 Student클래스를 만든다.
        이 Student클래스의 생성자에서는 학번, 이름, 국어점수, 영어점수, 수학점수만 매개변수로 받아서
        초기화 처리를 한다. (총점은 3과목 점수의 합계로 초기화 한다.)

        이 Student객체는 List에 저장하여 관리한다.

        List에 저장된 데이터들을 학번의 오름차순으로 정렬할 수 있는 내부 정렬 기준을 구현하고,
        총점의 내림차순으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬되는 외부 정렬 기준 클래스를
        작성하여 정렬된 결과를 출력하시오.

        (단, 등수는 List에 전체 데이터가 추가된 후에 저장되도록 한다.)
 * 
 */

public class StudentTest {
	
		//등수를 구하는 메서드
	public void createRanking(ArrayList<Student1> stdList) {
		for(Student1 std1 : stdList) {  //기준 데이터를 구하기 위한 반복문
			int rank =1;  //처음에는 1등으로 초기화해 놓고 시작한다.
			for(Student1 std2 : stdList) { //비교 대상을 나타내는 반복문
			
				// 기준보다 비교 대상이 크면 등수를 증가시킨다.
				if(std1.getTot() < std2.getTot()){
					rank++;
			}
		}
			//구해진 등수를 Student 객체의 rank변수에 저장한다.
			std1.setRank(rank);
			
	}
}

	public static void main(String[] args) {
		ArrayList<Student1> stdList = new ArrayList<Student1>();
		stdList.add(new Student1(1, "홍길동", 90, 85, 80));
		stdList.add(new Student1(3, "성춘향", 90, 15, 70));
		stdList.add(new Student1(7, "강감찬", 95, 95, 80));
		stdList.add(new Student1(5, "변학도", 80, 95, 90));
		stdList.add(new Student1(4, "일지매", 100, 85, 80));
		stdList.add(new Student1(2, "이순신", 60, 95, 70));
		stdList.add(new Student1(6, "이몽룡", 90, 100, 90));

		
		// List에 데이터가 모두 추가된 후에 등수 구하는 메서드를 호출한다.
		StudentTest test = new StudentTest();
		test.createRanking(stdList);
		
		
		System.out.println("정렬 전...");
		for (Student1 std : stdList) {
			System.out.println(std);
		}
		System.out.println("------------------------------------------");
		System.out.println();
		
		// 내부 정렬 기준 (학번의 오름차순)으로 정렬하기
		Collections.sort(stdList);
		
		System.out.println("학번의 오름차순 정렬 후...");
		for (Student1 std : stdList) {
			System.out.println(std);
		}
		System.out.println("------------------------------------------");
		System.out.println();
		
		// 외부 정렬 기준(총점의 내림차순)으로 정렬하기
		Collections.sort(stdList, new SortByTotal());
		System.out.println("총점의 내림차순 정렬 후...");
		for (Student1 std : stdList) {
			System.out.println(std);
		}
		System.out.println("------------------------------------------");
		System.out.println();
	}
}


class Student1 implements Comparable<Student1>{
	private int num;
	private String name;
	private int kor;
	private int eng;
	private int mat;
	private int tot;
	private int rank;
	
	//생성자
	public Student1(int num, String name, int kor, int eng, int mat) {
		super();
		this.num = num;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.mat = mat;
		this.tot = kor+eng+mat;
		
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getKor() {
		return kor;
	}

	public void setKor(int kor) {
		this.kor = kor;
	}

	public int getEng() {
		return eng;
	}

	public void setEng(int eng) {
		this.eng = eng;
	}

	public int getMat() {
		return mat;
	}

	public void setMat(int mat) {
		this.mat = mat;
	}

	public int getTot() {
		return tot;
	}

	public void setTot(int tot) {
		this.tot = tot;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "Student1 [num=" + num + ", name=" + name + ", kor=" + kor + ", eng=" + eng + ", mat=" + mat + ", tot="
				+ tot + ", rank=" + rank + "]";
	}

	// 학번의 오름차순으로 정렬할 수 있는 내부 정렬 기준을 구현
	@Override
	public int compareTo(Student1 std) {
		return Integer.compare(this.getNum(), std.getNum());
	}
	
}

// 총점의 내림차순으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬되는 외부 정렬 기준 클래스
class SortByTotal implements Comparator<Student1>{

	@Override
	public int compare(Student1 std1, Student1 std2) {
		if(std1.getTot()==std2.getTot()) {
			return std1.getName().compareTo(std2.getName());
			
		}
		return 0;
	}
	
}





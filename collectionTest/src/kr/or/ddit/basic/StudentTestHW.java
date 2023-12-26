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

public class StudentTestHW {
	public static void main(String[] args) {
		ArrayList<Student> stuList = new ArrayList<Student>();
		stuList.add(new Student(1, "사과", 100, 100, 100));
		stuList.add(new Student(3, "바나나", 70, 70, 70));
		stuList.add(new Student(5, "딸기", 95, 95, 95));
		stuList.add(new Student(4, "레몬", 95, 95, 95));
		stuList.add(new Student(2, "포도", 80, 80, 80));
		
		for (Student stu : stuList) {
			int rank=1;
			for (Student stu1 : stuList) {
				if(stu.getSum() < stu1.getSum()) {
					rank++;
				}
				stu.setRank(rank);
			}
		}
		
		Collections.sort(stuList);
		for (Student stu : stuList) {
			System.out.println(stu);
		}
		System.out.println();
		
		Collections.sort(stuList, new NameDesc());
		for (Student stu : stuList) {
			System.out.println(stu);
		}
		
		
	}
}

class Student implements Comparable<Student> {
	private int num;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int sum;
	private int rank;

	public Student(int num, String name, int kor, int eng, int math) {
		super();
		this.num = num;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		sum = kor + eng + math;
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

	public int getMath() {
		return math;
	}

	public void setMath(int math) {
		this.math = math;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	@Override
	public String toString() {
		return "Student [num=" + num + ", name=" + name + ", kor=" + kor + ", eng=" + eng + ", math=" + math + ", sum="
				+ sum + ", rank=" + rank + "]";
	}

	@Override
	public int compareTo(Student stunum) {
		if (this.getNum() < stunum.getNum()) {
			return -1;
		} else if (this.getNum() > stunum.getNum()) {
			return 1;
		} else
			return 0;
	}
}

//class SumDesc implements Comparator<Student> {
//
//	@Override
//	public int compare(Student sum1, Student sum2) {
//		if (sum1.getSum() < sum2.getSum()) {
//			return 1;
//		} else if (sum1.getSum() > sum2.getSum()) {
//			return -1;
//		} else
//			return 0;
//	}
//}

class NameDesc implements Comparator<Student> {

	@Override
	public int compare(Student sum1, Student sum2) {
		if (sum1.getSum() < sum2.getSum()) {
			return 1;
		} else if (sum1.getSum() > sum2.getSum()) {
			return -1;
		} else if (sum1.getSum() == sum2.getSum()) {
			if (sum1.getName().compareTo(sum2.getName()) < 0) {
				return -1;
			} else if (sum1.getName().compareTo(sum2.getName()) > 0) {
				return 1;
			}else {
				return 0;
			}
		}else {
			return 0;
		}
	}

}

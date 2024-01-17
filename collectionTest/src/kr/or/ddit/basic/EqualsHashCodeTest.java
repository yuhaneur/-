package kr.or.ddit.basic;

import java.util.HashSet;
import java.util.Objects;

public class EqualsHashCodeTest {
	public static void main(String[] args) {
		Person p1 = new Person();
		p1.setNum(1);
		p1.setName("홍길동");
	
		Person p2 = new Person();
//		p2.setNum(2);
//		p2.setName("이순신");
		p2.setNum(1);
		p2.setName("홍길동");
		
		Person p3 = p1;
		
		System.out.println(p1==p2);  //주소값을 비교한다
		System.out.println(p1==p3);
		System.out.println();
		
		System.out.println(p1.equals(p2));  // equals 재정의해서 값비교 가능함
		System.out.println(p1.equals(p3));
		System.out.println("--------------------------------------");
		
		HashSet<Person> testSet = new HashSet<Person>();
		
		testSet.add(p1);
		testSet.add(p2);
		System.out.println("set의 개수 : " + testSet.size());
		
		System.out.println("p1=> " + p1.hashCode());
		System.out.println("p2=> " + p2.hashCode());
		System.out.println("p3=> " + p3.hashCode());
	}
}

/*
 * - equals()메서드 ==> 두 객체의 내용이 같은지를 비교하는 메서드
 * - hashCode()메서드 ==> 두 객체가 같은 객체인지를 비교하는데 사용되는 메서드
 * 
 * - HashMap, Hashtable, HashSet과 같이 Hash로 시작하는 컬렉션 객체들은
 *   객체의 의미상 같은지 비교를 위해 hashCode()메서드를 호출하여 비교한다.
 *   그래서 객체가 같은지 여부를 결정하려면 equals()메서드와 hashCode()메서드를
 *   같이 재정의 해야 한다.
 *   
 * - hashCode()메서드에서 사용하는 '해싱 알고리즘'은 서로 다른 객체들에 대해
 *   같은 hashCode 값을 만들어 낼 수 있다.
 *     
 *   
 *   
 */



class Person{ //extends Object 오브젝트 상속은 이미 적용되어있어서 따로 안써도된다. 
	private int num;
	private String name;
	
	
	public Person() {
		
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

	
	
	//이클립스에는 equals, hashCode 재정의를 자동으로 만들어준다
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + num;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (num != other.num)
			return false;
		return true;
	}

	//equals, hashCode 재정의
//	@Override
//	public boolean equals(Object obj) {
//		if(this == obj) return true;
//		if(obj == null) return false;
//		
//		// 같은 유형의 클래스인지 검사
//		if(this.getClass() !=obj.getClass()) return false;
//		
//		// 매개변수의 객체를 현재의 객체 유형으로 형변환 한다.
//		Person that = (Person)obj;
//		
//		// 멤버 변수의 값들을 비교한다.
//		return this.num == that.num && Objects.equals(this.name, that.name); 
//		
//	}
//
//	@Override
//	public int hashCode() {
//		
//		return Objects.hash(num, name);
//		
//	}
	
	
	
}
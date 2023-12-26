package kr.or.ddit.basic.args;
/*
 * 가변인자==>메서드의 인자의 갯수가 실행될 때마다 다를 때 사용한다.
 * - 가변인자는 메서드 안에서 배열로 처리된다.
 * - 가변인자는 메서드에서 한 개만 사용할 수 있다.
 * 
 */

public class ArgsTest {
	//매개변수로 받은 정수들의 합계를 구하는 메서드 만들기
	// (이 정수의 갯수는 상황에 따라 달라질 수 있다.)

	
	//배열을 이용한 메서드
	public int sumArr(int[] data) {
		int sum = 0;
		for(int num : data) {
			sum += num;
		}
		return sum;
	}
	
	
	// 가변인자를 이용한 메서드
	public int sumArg(int... data) {
		int sum = 0;
		for(int num : data) {
			sum += num;
		}
		return sum;
	}
	
	
	// 가변인자와 일반 매개변수를 같이 사용할 경우에는 
	// 가변 인자를 제일 뒤쪽에 배치해야 한다.
	
	// sumArg2(1,2,3,4,5,6);
//	public String sumArg2(int... data, int name) { //불가
//	public String sumArg2(int name, int... data) {
	
	
	public String sumArg2(String name, int... data) {
		int sum = 0;
		for(int num : data) {
			sum += num;
		}
		return name + "씨 점수 : " + sum;
	}
	
	
	
	public static void main(String[] args) {
		ArgsTest test = new ArgsTest();
		
		int[] numArr = {100,200,300};
		//이건 안됨, 저 아래에있는것 처럼 해야함
//		int[] numArr = null;
//		numArr = {100,200,300};
		
		
//		int[] numArr2 = new int[] {100,200,300};
		int[] numArr2 = null;
		numArr2 = new int[] {100,200,300};
		
		
		System.out.println(test.sumArr(numArr));
		System.out.println(test.sumArr(new int[] {1,2,3,4,5})); //1,2,3,4,5
		System.out.println();
		
		
		System.out.println(test.sumArg(100,200,300));
		System.out.println(test.sumArg(1,2,3,4,5));
		System.out.println();

		
		System.out.println(test.sumArg2("홍길동", 1,2,3,4,5,6,7,8,9));
		
		
		
	}
}










package kr.or.ddit.basic.genericTest;

/*
 * 제네릭 클래스를 만드는 방법
 * 형식)
 * class 클래스명<제네릭타입글자>{
 * 		제네릭타입글자 변수명; //변수 선언에 제네릭을 사용할 경우
 * 		...
 * 
 * 		제네릭타입글자 메서드명(매개변수들...){  //메서드의 반환값에 제네릭을 사용할때
 * 			...
 * 			return 값;
 * 		}
 * 	
 * 		//매개변수를 지정할 때 제네릭을 사용할 경우
 * 		반환값자료형 메서드명(제네릭타입글자 변수명, ...){
 * 			...
 * 		}
 * }
 * 
 * 
 *	--제네릭타입글자(영어대문자 1글자를 사용...)
 *	T 	==> Type
 *  K	==> Key
 *  V	==> Value
 *  E	==> Element
 *
 *  
 */



//제네릭을 사용하지 않을 경우
class NonGeneric{
	private Object value;

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}
	
}

//제네릭을 사용하는 클래스
class MyGeneric<T>{
	private T value;  //변수에 사용
	
	public void setValue(T value) {  //매개변수에 사용
		this.value = value;
	}
	
	public T getValue() {	//반환값에 사용
		return value;
	}
}
	

public class GenericTest {
	public static void main(String[] args) {
		NonGeneric non1 = new NonGeneric();
		non1.setValue("안녕하세요.");
		
		NonGeneric non2 = new NonGeneric();
		non2.setValue(123);
		
		String rtn1 = (String)non1.getValue();
		System.out.println("문자열 반환값 : " + rtn1);
		
		int rtn2 = (int)non2.getValue();
		System.out.println("정수형 반환값 : " + rtn2);
		
//		int rtn3 = (int)non1.getValue();
//		System.out.println("rtn3=> "+ rtn3);
		//-------------------------------------------------
		System.out.println();
		System.out.println("-------------------------------------");
		System.out.println();
		
		MyGeneric<String> my1 = new MyGeneric<String>();
		my1.setValue("우리나라");
		
		MyGeneric<Integer> my2 = new MyGeneric<Integer>();
		my2.setValue(500);
		
//		my2.setValue("대한민국");  //다른 종류의 데이터를 사용할 수 없게 한다.
		
		
		//데이터를 꺼내올 때 형 변환 없이 꺼내올 수 있다.
		String myRtn1 = my1.getValue(); 
		System.out.println("제네릭 문자열 반환값 : "+myRtn1);
		
		int myRtn2 = my2.getValue();
		System.out.println("제네릭 정수형 반환값 : "+myRtn2);
		
//		String myRtn3 = my2.getValue();   //다른 종류의 데이터를 사용할 수 없게 한다.
		
		
		
	}
}






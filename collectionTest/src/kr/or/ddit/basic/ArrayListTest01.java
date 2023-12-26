package kr.or.ddit.basic;

import java.util.ArrayList;

public class ArrayListTest01 {
	public static void main(String[] args) {
		// ArrayList의 기본적인 사용법은 Vector와 같다.
		
		ArrayList list1 = new ArrayList();
		
		//add()메서드를 이용해서 데이터를 추가한다.
		list1.add("aaa");
		list1.add("bbb");
		list1.add(123);
		list1.add('k');
		list1.add(true);
		list1.add(123.45);
		
		System.out.println("list1 => "+ list1);
		System.out.println("size => " + list1.size());
		System.out.println();
		
		// get()메서드를 이용해서 데이터를 꺼낸다.
		System.out.println("1번째 자료 : " + list1.get(1));
		
		// 데이터 끼워 넣기도 같다.
		list1.add(3, "ZZZ");
		System.out.println("list1 => "+ list1);
		System.out.println();
		
		// 데이터 변경하기
		String sTemp = (String)list1.set(3, "yyy");
		System.out.println("list1 ==> "+ list1);
		System.out.println("sTemp==>" + sTemp);
		System.out.println();
		
		// 삭제도 같다.
		list1.remove(3);	//index로 삭제하기
		list1.remove("bbb");		//데이터를 찾아서 삭제하기
		System.out.println("삭제 후 list1 ==> " +list1);
		System.out.println();
		
		// 제네릭을 사용할 수 있다.
		ArrayList<String> list2 = new ArrayList<String>();
		list2.add("AAAA");
		list2.add("BBBB");
		list2.add("CCCC");
		list2.add("DDDD");
		list2.add("EEEE");
//		list2.add(1234); //오류 String으로 만들어서 숫자는 안됨
		
		 for(int i=0; i<list2.size(); i++) {
			 System.out.println(i + " ==> " +list2.get(i));
		 }
		 System.out.println();
		 
		 for(String str : list2) {
			 System.out.println(str);
		 }
		System.out.println("---------------------------------------");
		
		// contains(비교객체) ==> List에 저장된 데이터 중에서 '비교객체'가 있으면 true
		// 						없으면 false를 반환한다.
		
		System.out.println("DDDD값의 존재 여부 : " +list2.contains("DDDD"));
		System.out.println("ZZZZ값의 존재 여부 : " +list2.contains("ZZZZ"));
		
		// indexOf(비교객체)
		// lastIndexOf(비교객체) ==> List에 '비교객체'가 있으면 '비교객체'가 저장된
		//			index값을 반환하고 없으면 -1을 반환한다.
		// - indexOf()메서드는 검색 방향이 앞에서 뒤쪽으로 검색하고,
		// 	 lastIndexOf()메서드는 검색 방향이 뒤에서 앞쪽으로 검색한다.
		// - List에 '비교객체'가 많으면 첫번째로 찾아진 데이터의 위치값이 반환된다.
		
		list2.add("AAAA");
		list2.add("BBBB");
		list2.add("CCCC");
		list2.add("DDDD");
		list2.add("EEEE");
		System.out.println("list2 ==>" +list2);
		
		System.out.println("DDDD의 위치값 : " + list2.indexOf("DDDD"));
		System.out.println("ZZZZ의 위치값 : " + list2.indexOf("ZZZZ"));
		System.out.println("DDDD의 위치값 : " + list2.lastIndexOf("DDDD"));
		System.out.println();
		// - toArray() ==> List안의 데이터를 배열로 변환해서 반환한다.
		// 			   ==> 기본적으로 Object형 배열로 변환한다.
		// - toArray(new 제네릭타입[0]) ==> 제네릭 타입의 배열로 변환해서 반환한다.
		
		
		Object[] strArr = list2.toArray();
//		String[] strArr2 = (String[])list2.toArray();	//이 방법은 사용할 수 없다
		
		System.out.println("List의 개수 : " + list2.size());
		System.out.println("배열의 개수 : " + strArr.length);
		System.out.println();
		
		for(int i=0; i<strArr.length; i++) {
			System.out.println(i +"번째 자료 : " +strArr[i]);
		}
		System.out.println("------------------------------------------");
		
		String[] strArr2 = list2.toArray(new String[0]);
		for (String str : strArr2) {
			System.out.println(str);
		}
		
		
		}

	
	
	
}

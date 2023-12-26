package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class MapTest {
	public static void main(String[] args) {
		/*
		 * - Map ==> key값과 value값을 한 쌍으로 관리하는 객체
		 * - key값은 중복을 허용하지 않고 순서도 없다. ==> Set의 특징을 갖는다.
		 * - value값은 중복을 허용한다.
		 */
		
		   HashMap<String, String> map = new HashMap<String, String>();
		   
		   // 자료 추가 ==> put(key값, value값)
		   map.put("name", "홍길동");
		   map.put("addr", "대전");
		   map.put("tel", "010-1234-5678");
		   
		   System.out.println("map=> "+map);
		   
		   //자료 수정 ==> 데이터를 추가할때 key값이 같으면 나중에 추가한 내용이 저장된다.
		   map.put("addr", "서울");
		   System.out.println("map=> "+map);
		
		   //자료 삭제 ==> remove(key값) ==> key값이 같은 자료를 찾아서 삭제한다.
		   //			==> 반환값 : 삭제된 자료의 value값
		   //					     삭제 실패시 null값 반환
		   
//		   String removeTel = map.remove("tel");
//		   System.out.println("map=> "+map);
//		   System.out.println("삭제된 value값" + removeTel);
		   
		   // 자료 읽기 ==>get(key값)
		   // 	     ==> 반환값 : 주어진 'key값'과 짝이되는 value값을 반환한다.
		   //					주어진 'key'값이 없으면 null값을 반환한다.
		   
		   System.out.println("이름 : " + map.get("name"));
		   System.out.println();
		   
		   // key값이 존재한하는지 여부를 나타내는 메서드 ==> containsKey(key값)
		   //			==> 해당 key값이 있으면  true, 없으면  false를 반환단다.
		   System.out.println("tel 키값의 존재 여부 : "+map.containsKey("tel"));
		   System.out.println("age 키값의 존재 여부 : "+map.containsKey("age"));
		   System.out.println();
		   
		   //Map에 저장된 모든 데이터를 차례로 읽어와 사용하는 방법
		   
		   //1. 모든 key값을 읽어와 처리하기 ==> keySet()메서드 이용하기
		   // keySet()메서드 ==> Map의 모든 key값들을 읽어와 Set형으로 반환한다.
		   
		   Set<String> keySet = map.keySet();
		   
		   //방법1
		   Iterator<String> it = keySet.iterator();
		   while(it.hasNext()) {
			   String key = it.next();    //키값 구하기
			   String value = map.get(key);
			   System.out.println(key + "==>" + value);
		   }
		  System.out.println("----------------------------------");
		  
		  //방법2
		  for (String key : keySet) {
			String value = map.get(key);
			System.out.println(key+" : "+value);
		}
		  System.out.println("----------------------------------");
		   
		
		// 2. 모든value값들만 읽어와 처리하기 ==> values()메서드 이용
		  for(String value : map.values()) {
			  System.out.println(value);
		  }
		  System.out.println("----------------------------------");
		  
	}
}

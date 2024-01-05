package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ObjectIOTest01 {

	public static void main(String[] args) {
		// Member의 인스턴스 생성
		Member mem1 = new Member("홍길동", 20, "대전");
		Member mem2 = new Member("박길동", 30, "강원");
		Member mem3 = new Member("홍길서", 40, "경기");
		Member mem4 = new Member("김길북", 50, "인천");
		
		// 객체를 파일에 저장하기
		try {
			// 출력용 스트림 객체 생성
			FileOutputStream fout 
				= new FileOutputStream("d:/d_other/memObj.data");
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			ObjectOutputStream oout =new ObjectOutputStream(bout);
				
			// 쓰기작업
			System.out.println("객체 저장 작업 시작...");
			oout.writeObject(mem1);
			oout.writeObject(mem2);
			oout.writeObject(mem3);
			oout.writeObject(mem4);
			
			//객체를 저장할 떄 마지막에 null을 저장하여 EOEFexception 아
			// 박생하는 것을 방지할 수 있다.
			oout.writeObject(null);
			System.out.println("객체 저장 작업 끝 ...");
			
			oout.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

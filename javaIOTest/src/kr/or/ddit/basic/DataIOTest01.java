package kr.or.ddit.basic;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataIOTest01 {

	public static void main(String[] args) {
		try {
			FileOutputStream fout = 
				new FileOutputStream("d:/d_other/test.dat");
			
			// 자료형 단위로 출력할 보조 스트림(DataOutputStream)객체 생성
			DataOutputStream dout = new DataOutputStream(fout);
			
			dout.writeInt(250);			// 정수형 출력
			dout.writeFloat(3.14f);		// 실수형 출력
			dout.writeBoolean(true);	// 논리형 출력
			dout.writeUTF("ABCDEabcde");// 문자열 출력
			
			System.out.println("출력 완료...");
			
			dout.close();	// 스트림 닫기
			
		} catch (IOException e) {
			// TODO: handle exception
		}
		
		try {
			FileInputStream fin = 
				new FileInputStream("d:/d_other/test.dat");
			DataInputStream din = new DataInputStream(fin);
			
			// DataInputStream 으로 자료를 읽어올 떄는
			// DataOutputStream으로 출력한 순서와 같은 순서로 읽어와야 된다.
			System.out.println("정수형 : " + din.readInt());
			System.out.println("실수형 : " + din.readFloat());
			System.out.println("논리형 : " + din.readBoolean());
			System.out.println("문자열 : " + din.readUTF());
			
			System.out.println("읽기 작업 끝 ...");
			
			din.close();
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}

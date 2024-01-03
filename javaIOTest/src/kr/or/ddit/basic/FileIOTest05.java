package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class FileIOTest05 {

	public static void main(String[] args) {
		// 사용자가 키보드로 입력한 내용을 그대로 파일로 저장하기
		
		try {
			//System.in ==> 콘솔(표준입출력장치)의 입력장치와 연결된 스트림 객체
			//	  			(바이트 기반 스트림이다.)
//			System.out.println("아무거나 입력하세요.");
//			System.out.println((char)System.in.read());
			
			// 입력용 바이트 기반 스트림을 문자 기반의 스트림으로 변환해야 한다.
			// ==> InputStreamReader클래스를 이용한다.(보조스트림)
			InputStreamReader isr = new InputStreamReader(System.in);
			
			FileWriter fw = new FileWriter("d:/d_other/testChar.txt");
			
			System.out.println("아무 내용이나 입력하세요. ");
			System.out.println("입력의 끝은 Ctrl + z 키 입니다.");
			
			int c;
			
			// 콘솔에서 입력할 때 입력의 끝은 Ctrl + z키를 누르면 된다.
			while((c=isr.read())!= -1) {
				fw.write(c);  // 콘솔로 입력 받은 데이터를 파일로 출력하기
			}
			
			isr.close();
			fw.close();
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}

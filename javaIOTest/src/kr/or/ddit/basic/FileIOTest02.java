package kr.or.ddit.basic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileIOTest02 {

	public static void main(String[] args) {
		try {
			File file = new File("d:/d_other/out.txt");
			
			// 파일 출력용 스트림 객체 생성
			FileOutputStream fout = new FileOutputStream(file);
			
			for(char ch='A'; ch<='Z'; ch++) {
				fout.write(ch);	// ch변수의 데이터를 파일로 출력한다.
			}
			fout.write('\n');// 줄바꿈 문자 출력
			
			// 문자열을 byte기반의 스트림으로 출력하기
			String str = "HELLO!! 안녕?? 아아아아아가가가가가가";
			byte[] bStrArr = str.getBytes("utf-8");	// 문자열이 갖고있는 각각의 데이터들 문자하나하나를 코드값으로 바꿈
			fout.write(bStrArr);
			
			
			
			System.out.println("작업 완료!!!");
			fout.close();	// 스트림 닫기
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
	
}

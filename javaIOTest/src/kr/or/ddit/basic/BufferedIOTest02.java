package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BufferedIOTest02 {

	public static void main(String[] args) {
		// 문자 기반의 Buffered 스트림 사용 예제
		try {
			FileReader fr = 
				new FileReader("./src/kr/or/ddit/basic/FileTest01.java");
			BufferedReader br = new BufferedReader(fr);
			
			String temp = "";  // 읽어온 데이터가 저장될 변수
			
			for(int i=1;(temp = br.readLine())!=null; i++) {
				System.out.printf("%4d : %s\n ", i, temp);
			}

			br.close();
			
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}

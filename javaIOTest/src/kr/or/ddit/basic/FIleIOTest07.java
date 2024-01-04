package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class FIleIOTest07 {

	public static void main(String[] args) {
		// 사용자로부터 출력할 단을 입력 받아 구구단을 파일로 출력하는 프로그램 작성하기
		// (출력할 파일명 : 'd:/d_other/gugudan2.txt')
		// 문자 기반 스트림을 이용해서 작성하시오.
		
		Scanner sc = new Scanner(System.in);
		FileWriter fw = null;
		
		try {
			fw = new FileWriter("d:/d_other/gugudan2.txt");
			
			System.out.println("원하는 단을 입력하세요");
			int num = sc.nextInt();
			
			
			for(int i=1; i<=9; i++) {
				String str = num+ " x " + i + " = " + num*i + "\n";
				fw.write(str);
			}			
			System.out.println("다됬다!");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(fw!=null) try { fw.close();} catch(IOException e) {}
		}
		
		
		
		
	}
	
}

package kr.or.ddit.basic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class FileIOTest03 {

	public static void main(String[] args) {
		// 사용자로부터 출력할 단을 입력 받아 구구단을 파일로 출력하는 프로그램 작성하기
		// (출력할 파일명 : 'd:/d_other/gugudan.txt')
		
		Scanner sc = new Scanner(System.in);
		FileOutputStream fo = null;
		
		try {
			fo = new FileOutputStream("d:/d_other/gugudan.txt");
			
			System.out.println("원하는 단을 입력하세요");
			int num = sc.nextInt();
			
			String str = "";
			for(int i=1;i<=9;i++) {
				str += num+ " x " + i + " = " + num*i + "\n";
			}
//			for(int i=1; i<=9; i++) {
//				str = num+ " x " + i + " = " + num*i + "\n";
//				fo.write(str.getBytes("utf-8"));
//			}
			byte[] bArr = str.getBytes("utf-8");
			fo.write(bArr);
			System.out.println("다됬다!");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(fo!=null) try { fo.close();} catch(IOException e) {}
		}
		
	}
}

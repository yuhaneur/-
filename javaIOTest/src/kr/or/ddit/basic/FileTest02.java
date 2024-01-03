package kr.or.ddit.basic;

import java.io.File;
import java.io.IOException;

public class FileTest02 {

	public static void main(String[] args) {
		File f1 = new File("d:/d_other/test.txt");
		
		System.out.println(f1.getName() + "의 크기 : " + f1.length() + "byte(s)");
		System.out.println("path : " + f1.getPath());
		System.out.println("absolutePath : " + f1.getAbsolutePath());
		System.out.println();
		
		// 현재 위치 나타내기 (이클립스는 '프로젝트'가 만들어진 폴더가 현재 위치가 된다.)
//		File f2 = new File("");
		File f2 = new File(".");
		System.out.println("path : " + f2.getPath());
		System.out.println("absolutePath : " + f2.getAbsolutePath());
		System.out.println();
		
		if(f1.isDirectory()) {
			System.out.println(f1.getName() + " 은 디렉토리입니다.");
		}else if(f1.isFile()) {
			System.out.println(f1.getName() + " 은 파일입니다.");			
		}else {
			System.out.println(f1.getName() + " 은 뭘까요???");						
		}
		System.out.println();

		if(f2.isDirectory()) {
			System.out.println(f2.getName() + " 은 디렉토리입니다.");
		}else if(f2.isFile()) {
			System.out.println(f2.getName() + " 은 파일입니다.");			
		}else {
			System.out.println(f2.getName() + " 은 뭘까요???");						
		}
		System.out.println();
		
		File f3 = new File("d:/d_other/sample.txt");
		if(f3.isDirectory()) {
			System.out.println(f3.getName() + " 은 디렉토리입니다.");
		}else if(f3.isFile()) {
			System.out.println(f3.getName() + " 은 파일입니다.");			
		}else {
			System.out.println(f3.getName() + " 은 뭘까요???");						
		}
		System.out.println();
		
		if(f3.exists()) {
			System.out.println(f3.getName() + "은 존재합니다.");
		}else {
			System.out.println(f3.getName() + "은 존재하지 않습니다.");
			// 빈 파일 생성하기
			try {
				if(f3.createNewFile()){
					System.out.println(f3.getAbsolutePath() + "파일을 새로 만들었습니다.");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("==================================================");
		
		File f4 = new File("d:/d_other");
		File[] fileArr = f4.listFiles();
		for(File f : fileArr) {
			System.out.print(f.getName() + " ==> ");
			if(f.isFile()) {
				System.out.println("파일");
			}else {
				System.out.println("디렉토리(폴더)");
			}
		}
		System.out.println("--------------------------");
		
		String[] strFileArr = f4.list();
		for(String strFile : strFileArr) {
			System.out.println(strFile);
		}
	}
}

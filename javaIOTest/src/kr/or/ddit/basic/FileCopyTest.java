package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
	'd:/d_other' 폴더에 있는 '펭귄.png'파일을 
	'd:/d_other/연습용'폴더에'복사본_펭귄.png'파일로 복사하는 프로그램을 작성하시오.
	
*/
public class FileCopyTest {

	public static void main(String[] args) {
		File file = new File("d:/d_other/펭귄.png");
		// 파일이 존재하지않으면 
		if(!file.exists()) {
			System.out.println(file.getPath() + " 파일이 없습니다.");
			System.out.println("복사 작업을 중지합니다...");
			return;
		}
		
		try {
			// 원본 파일을 읽어올 스트림 객체 생성
			FileInputStream fin = new FileInputStream(file);
			BufferedInputStream bin = new BufferedInputStream(fin);
			
			// 대상 파일에 저장할 스트림 객체 생성
			FileOutputStream fout = new FileOutputStream("d:/d_other/연습용/복사본_펭귄.png");
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			
			System.out.println("복사 작업 시작...");
			//읽어올 데이터 저장변수
			int data;
			
//			while((data=fin.read()) != -1) {
//				fout.write(data);
//			}
//			
//			fout.close();
//			fin.close();
			
			while((data=fin.read()) != -1) {
				bout.write(data);
			}
			bout.flush();
			
			bin.close();
			bout.close();
			
			System.out.println("복사 작업 완료...");
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
	
}

package kr.or.ddit.basic;

import java.awt.Panel;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/*
	'd:/d_other' 폴더에 있는 '펭귄.png'파일을 
	'd:/d_other/연습용'폴더에'복사본_펭귄.png'파일로 복사하는 프로그램을 작성하시오.
	
*/
public class FileCopyTestDialog {

	public File chooseFile(String option) {
		// SWING의 파일 열기, 저장 창 연습
		
				JFileChooser chooser = new JFileChooser();
				
				// 선택할 파일의 확장자 설정하기
				FileNameExtensionFilter txt =
					new FileNameExtensionFilter("text파일(*.txt)", "txt");

				FileNameExtensionFilter img =
					new FileNameExtensionFilter("이미지파일",
							new String[] {"png","jpg","gif"});

				FileNameExtensionFilter doc =
						new FileNameExtensionFilter("MS워드", "doc","docx");
				
				chooser.addChoosableFileFilter(txt);
				chooser.addChoosableFileFilter(img);
				chooser.addChoosableFileFilter(doc);
				
				// 확장자 목록 중 기본적으로 선택될 확장자 지정하기
				chooser.setFileFilter(img);
				
				// Dialog 창에 보여줄 기본 디렉토리(폴더) 설정 
				chooser.setCurrentDirectory(new File("d:/d_other"));
				
				int result;
				if("SAVE".equals(option.toUpperCase())) {
					result = chooser.showSaveDialog(new Panel()); // 저장용 창
				}else if("OPEN".equals(option.toUpperCase())) {
				    result = chooser.showOpenDialog(new Panel());  // 열기용 창 
				}else {
					System.out.println("Option은 'SAVE' 또는 'OPEN'만 가능합니다. ");
					return null;
					
				}
				
				// Dialog 창에서 '열기' 또는 '저장' 버튼을 눌렀을 경우...
				File selectedFile = null;
				if(result == JFileChooser.APPROVE_OPTION) {  
					// Dialog 창에서 선택한 파일 정보를 가져와 
					// 실제 '저장' 또는 '읽기' 작업을 수행한다.
					selectedFile = chooser.getSelectedFile();
				}
				return selectedFile;
	}
	
	public static void main(String[] args) {
		FileCopyTestDialog test = new FileCopyTestDialog();
		
//		File file = new File("d:/d_other/펭귄.png");
		File file = test.chooseFile("OPEN"); // 원본 파일 선택
		if(file == null) {
			System.out.println("원본 파일을 선택하지 않았습니다.");
			System.out.println("복사 작업을 중지합니다...");
			return;
		}
		
		// 파일이 존재하지않으면 
		if(!file.exists()) {
			System.out.println(file.getPath() + " 파일이 없습니다.");
			System.out.println("복사 작업을 중지합니다...");
			return;
		}
		
		try {
			// 원본 파일을 읽어올 스트림 객체 생성
			FileInputStream fin = new FileInputStream(file);
			
			// 대상 파일 선택하기
			File targetFile = test.chooseFile("SAVE");
			// 대상 파일에 저장할 스트림 객체 생성
//			FileOutputStream fout = new FileOutputStream("d:/d_other/연습용/복사본_펭귄.png");
			FileOutputStream fout = 
					new FileOutputStream(targetFile);
			
			System.out.println("복사 작업 시작...");
			//읽어올 데이터 저장변수
			int data;
			
			while((data=fin.read()) != -1) {
				fout.write(data);
			}
			
			fout.close();
			fin.close();
			System.out.println("복사 작업 완료...");
		} catch (IOException e) {
			// TODO: handle exception
		}
	}
	
}

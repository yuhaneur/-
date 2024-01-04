package kr.or.ddit.basic;

import java.awt.Panel;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DialogTest {

	public static void main(String[] args) {
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
		
		
//		int result = chooser.showOpenDialog(new Panel());  // 열기용 창 
		int result = chooser.showSaveDialog(new Panel());  // 저장용 창
		
		// Dialog 창에서 '열기' 또는 '저장' 버튼을 눌렀을 경우...
		if(result == JFileChooser.APPROVE_OPTION) {  
			// Dialog 창에서 선택한 파일 정보를 가져와 
			// 실제 '저장' 또는 '읽기' 작업을 수행한다.
			File selectedFile = chooser.getSelectedFile();
			System.out.println("선택한 파일 : " + selectedFile.getAbsolutePath());
		}
	}

}

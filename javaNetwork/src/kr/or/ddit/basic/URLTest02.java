package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class URLTest02 {

	public static void main(String[] args) throws IOException {
		// URLConnection 클래스 ==> 애플리케이션과 URL간의 통신 연결을 위한 클래스
		
		// 특정 서버의 정보와 파일 내용을 가져오는 예제
		URL url = new URL("https://www.naver.com/index.html");
		
		// URLConnection객체 구하기 (==>URL 객체를 통해서 구할 수 있다.)
		URLConnection urlCon = url.openConnection();
		
		// Header 정보 가져오기
		Map<String, List<String>> headerMap = urlCon.getHeaderFields();
		
		for(String headerKey : headerMap.keySet()) {
			System.out.println(headerKey + " : " + headerMap.get(headerKey));
		}
		System.out.println("--------------------------------------------------");
		
		// URL에 지정한 문서(파일)의 내용 가져오기 ==> index.html 문서의 내용 가져오기
		
		// 방법1) ==> URLConnection객체를 이용하는 방법
		
		// 파일 내용을 가져오기 위한 스트림 객체를 구한다.
//		InputStream in = urlCon.getInputStream();
//		InputStreamReader isr = new InputStreamReader(in,"utf-8");
//		BufferedReader br = new BufferedReader(isr);
//		
//		// 파일 내용을 읽어와 출력하기
//		while(true) {
//			String str = br.readLine();
//			if(str==null)break;
//			System.out.println(str);
//		}
//		br.close();
		
		// 방법2) ==> URL객체의 openStream() 메서드 이용하기
		InputStream in2 = url.openStream();
		BufferedReader br2 = new BufferedReader(
				new InputStreamReader(in2,"utf-8"));
		
		while(true) {
			String str2 = br2.readLine();
			if(str2==null)break;
			System.out.println(str2);
		}
		
	}
	
}

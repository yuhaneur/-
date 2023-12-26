package kr.or.ddit.basic;

import java.util.Stack;

public class StackTest {
	public static void main(String[] args) {
		Browser b = new Browser();
		
		b.history();
		
		b.goURL("1.네이버");
		b.history();
		
		b.goURL("2.야후");
		b.history();
		
		b.goURL("3.구글");
		b.goURL("4.다음");
		b.history();
		
		System.out.println("뒤로 가기 후...");
		b.goBack();
		b.history();
		
		b.goBack();
		b.history();

		System.out.println("앞으로 가기 후...");
		b.goForward();
		b.history();
		
		System.out.println("새로운 사이트로 접속한 후...");
		b.goURL("5.네이트");
		b.history();
		
		
	}

}

//웹 브라우저의 앞으로 가기, 뒤로 가기 기능 구현 클래스 작성(스택 이용)

class Browser{
	private Stack<String> back; 	// 이전 방문 내역이 저장될 스택
	private Stack<String> forward;  // 다음 방문 내역이 저장될 스택
	private String currentURL; 		// 현재 페이지
	
	// 생성자
	public Browser() {
		back = new Stack<String>();
		forward = new Stack<String>();
		currentURL = "";
		
	}
	
	//사이트를 방문하는 메서드==> 매개변수에는 방문할 URL이 저장된다.
	public void goURL(String url) {
		System.out.println(url + "사이트에 접속합니다...");
		
		//현재 페이지가 있으면 현재 페이지를 back스택에 추가한다.
		if(currentURL != null && !"".equals(currentURL)) {
			back.push(currentURL);
		}
		currentURL = url;  //현재 페이지를 이동할 페이지로 변경한다.
		forward.clear();
	}
	
	// 뒤로가기 ==> 현재 페이지를 forward스택에 추가하고, 
	//			  back스택에서 주소를 꺼내와 현재 페이지로 변경한다.
	public void goBack() {
		if(!back.isEmpty()) {
			forward.push(currentURL);
			currentURL = back.pop();
		}
	}
	
	// 앞으로 가기  ==> 현재 페이지를 back스택에 추가하고,
	//				forward스택에서 주소를 꺼내와 현재 페이지로 변경한다.
	public void goForward() {
		if(!forward.isEmpty()) {
			back.push(currentURL);
			currentURL = forward.pop();
		}
	}
	
	// 방문 기록을 확인하는 메서드
	public void history() {
		System.out.println("---------------------------");
		System.out.println("           방     문     기     록");
		System.out.println("---------------------------");
		System.out.println("back ==> " + back);
		System.out.println("현재 ==> " + currentURL);
		System.out.println("forward ==> " + forward);
		System.out.println("---------------------------");
		System.out.println();
	}
	
	
	
	
}




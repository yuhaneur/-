package kr.or.ddit.basic.tcp;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient01 {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// 현재 자신 컴퓨터를 나타내는 방법
		// 1) 원래의 IP주소 : 예) 192.168.142.18
		// 2) 지정된 IP주소 : 127.0.0.1
		// 3) 원래의 컴퓨터 이름 : 예) Host Name : DESKTOP-2D2MI8N
		// 4) 지정된 컴퓨터 이름 : localhost
		
		System.out.println("서버에 연결을 시도 합니다...");
		System.out.println();
		// 서버의 IP주소와 Port번호를 지정하여 Socket객체를 생성한다.
		// Socket객체는 생성이 완료되면 해당 IP주소와 Port번호로 연결 신호를 보낸다.
		Socket socket = new Socket("localhost",7777);
		
		// 이 부분은 서버와 연결된 이후에 처리할 내용을 기술하면 된다.
		System.out.println("서버에 연결 되었습니다...");
		
		// 서버에서 보낸 메시지를 받아서 출력하기
		
		// Socket을 이용하여 InputStream 객체를 생성한다.
		// (Socket의 getInputStream()메서드 이용)
		InputStream is = socket.getInputStream();
		DataInputStream din = new DataInputStream(is);
		
		// 서버가 보낸 메시지를 받아서 출력한다.
		System.out.println("서버에서 보낸 메시지 : " + din.readUTF());
		System.out.println();
		
		// 스트림과 소켓 닫기
		din.close();
		socket.close();
		
	}
	
}

package kr.or.ddit.basic.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer02 {

	public static void main(String[] args) {
		
		try {
			// ServerSocket 객체를 생성하여 클라이언트의 접속을 기다리고,
			// 클라이언트가 접속해 오면 Socket객체를 생성하여 
			// 메시지를 보내는 Thread와 메시지를 받는 Thread를 생성할 때 매개변수로 넣어준다.
			ServerSocket server = new ServerSocket(7777);
			
			System.out.println("서버가 준비 되었습니다...");
			
			Socket socket = server.accept();
			
			Sender sender = new Sender(socket);
			Receiver receiver = new Receiver(socket);
			
			sender.start();
			receiver.start();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

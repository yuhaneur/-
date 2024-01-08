package kr.or.ddit.basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {

	public static void main(String[] args) throws UnknownHostException {
		// InetAddress 클래스 ==> IP주소를 다루기 위한 클래스
		
		// www.naver.com의 IP정보 가져오기
		InetAddress ip = InetAddress.getByName("www.naver.com");
		
		System.out.println("Host Name : " + ip.getHostName());
		System.out.println("Host Address " + ip.getHostAddress());
		System.out.println("toString : " + ip.toString());
		System.out.println();
		
		// 자신의 컴퓨터의 IP정보 가져오기
		InetAddress localIp = InetAddress.getLocalHost();
		System.out.println("Host Name : " + localIp.getHostName() );
		System.out.println("Host Address : " + localIp.getHostAddress());
		System.out.println("toString: " + localIp.toString() );
		System.out.println();
		
		// IP 주소가 여러개인 호스트의 IP정보 가져오기
		InetAddress[] ipArr= InetAddress.getAllByName("www.naver.com");
		for(InetAddress temp : ipArr) {
			System.out.println(temp.toString());
		}
		
	}
}

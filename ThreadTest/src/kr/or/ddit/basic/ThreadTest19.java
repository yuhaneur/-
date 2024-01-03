package kr.or.ddit.basic;

public class ThreadTest19 {

	public static void main(String[] args) {
		
		DataBox databox = new DataBox();
		ProducerThread th1 = new ProducerThread(databox);
		ConsumerThread th2 = new ConsumerThread(databox);
		
		th1.start();
		th2.start();
	}
}

// 데이터를 공통으로 사용하는 클래스
class DataBox{
	private String data;
	
	
	// data변수의 값이 null이면 data변수에 문자열이 채워질 때까지 기다리고,
	// data변수에 값이 있으면 해당 문자열을 반환한다.
	// 반환 후에는 data변수를 null로 만든다.
	public synchronized String getData() {
		if(data==null) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		String temp = data;
		System.out.println("쓰레드가 읽은 데이터 : " + temp);
		data = null;
		
		notify();
		
		return temp;
	}
	
	// data변수에 값이 있으면 data변수가 null이 될 때까지 기다린다.
	// data변수가 null이 되면 새로운 데이터값을 저장한다.
	public synchronized void setData(String data) {
		if(this.data !=null) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		this.data=data;  // 새로운 데이터 저장
		System.out.println("Thread에서 새로 저장한 데이터 : " + data);
		notify();
	}
}

//데이터를 공급해 주는 쓰레드
class ProducerThread extends Thread{
	private DataBox databox;

	public ProducerThread(DataBox databox) {
		super();
		this.databox = databox;
	}
	
	@Override
	public void run() {
		String[] nameArr={"홍길동","이순신","강감찬"};
		
		for(int i=0;i<nameArr.length;i++) {
			databox.setData(nameArr[i]);
		}
	}
}

// 데이터를 꺼내서 사용하는 쓰레드
class ConsumerThread extends Thread{
	private DataBox databox;

	public ConsumerThread(DataBox databox) {
		this.databox = databox;
	}
	
	@Override
	public void run() {
		for(int i=0; i<3;i ++) {
			String data = databox.getData();
		}
	}
	
	
}
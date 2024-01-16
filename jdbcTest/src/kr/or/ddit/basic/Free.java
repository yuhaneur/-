package kr.or.ddit.basic;

public class Free {
	int a;
	String str ;
	public Free(int a) {
		this.a=a;
	}
	public Free(String str) {
		this.str=str;
	}
	Runnable runn = new Runnable() {
		
		@Override
		public void run() {
			
			System.out.println(a);
			System.out.println(str);
		}
	};
	
	
	public static void main(String[] args) {
		Free free = new Free(50);
		free = new Free("뱀");
		free.runn.run();
		
		Free2 free2 = new Free2(80);
		free2.run();
	}
}


class Free2 extends Thread{
	int a;
	public Free2(int a) {
		this.a = a;
	}
	
	@Override
	public void run() {
		System.out.println(a);
	}
}

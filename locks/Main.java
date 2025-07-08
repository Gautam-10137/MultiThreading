package locks;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		MyLock t1=new MyLock();
		MyLock t2=new MyLock();
		
		t1.start();
		t2.start();
		
		t1.join();
		t2.join();
				
	}

}

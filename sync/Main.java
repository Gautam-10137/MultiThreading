package sync;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		
//		MySync t1=new MySync("Thread1");
//		MySync t2=new MySync("Thread2");
//		t1.start();
//		
//		t2.start();
//		t1.join();
//		t2.join();
//		System.out.println(MySync.counter);
		
		SharedObject obj=new SharedObject();
		
        Thread t3=new Thread(()->obj.printValues(200));
        Thread t4=new Thread(()->obj.printValues(400));
        
        t3.start();
        t4.start();
        t3.join();
        t4.join();
	}

}

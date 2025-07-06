package basic;

class MyT implements Runnable{

	@Override
	public void run() {
		for(int i=0;i<100;i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Gautam");
		}
		
	}
	
}
public class Basic {

	
	public static void main(String args[]) throws InterruptedException {
//		extending Thread class
		MyThread t1 = new MyThread();
		
//	    implementing Runnable interface	
		Thread t2= new Thread(new MyT());
		
//		using lamda
		Thread t3=new Thread(()->{		
	
				for(int i=0;i<100;i++) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println("Glad to see you ,back!");
				}			
			
		});
		
		t1.start();
		t1.join();
		t2.start();
		t2.join();
		t3.start();
		t3.join();
		
	}
}

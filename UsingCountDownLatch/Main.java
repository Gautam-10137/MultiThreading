package UsingCountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// Problem: If we have multiple threads or multiple executor service, then we to use join() or get()
//          to wait for task execution, this is such a cumbersome task to write same thing again & again.

// Solution: Here we have solution, CountDownLatch, which wait for all threads that are associated with this latch.

class DependentService extends Thread{
	
	private final CountDownLatch latch;
	
	public DependentService(CountDownLatch latch) {
		this.latch = latch;
	}
    	
	public void run() {
		try {
			Thread.sleep(6000);
			System.out.println(Thread.currentThread().getName()+ " Service started.");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			latch.countDown();
		}
		
		
	}
}


public class Main {

	public static void main(String[] args) throws InterruptedException {
		
		int numberOfServices=3;
		CountDownLatch latch=new CountDownLatch(numberOfServices);
		
		for(int i=0;i<3;i++) {
			new DependentService(latch).start();
		}
		
		latch.await();   // try without using .await()
        System.out.println("Main");
	}

}

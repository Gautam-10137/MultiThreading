package locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class MyLock extends Thread{

	static int counter=0;
	static Lock lock= new ReentrantLock();
	
	
	public void run() {
		for(int i=0;i<100;i++) {
			lock.lock();
			try {
				counter++;
				System.out.println(Thread.currentThread().getName()+" -> Printing counter :"+counter);
			}
			finally {
				lock.unlock();
			}
		}
	}
	
}

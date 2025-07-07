package sync;

public class SharedObject {

	public void printValues(int num) {
		synchronized(this) {
			for(int i=1;i<=num;i++) {
			   System.out.println(Thread.currentThread().getName()+" -> printing :"+i);	
			   try {
				   Thread.sleep(1);
				   
			   }
			   catch(InterruptedException e) {
				 
			   }
			}
		}
	}
}

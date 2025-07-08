package locks.bank_system;

public class Bank {

	public static void main(String args[]) throws InterruptedException {
		// will make multiple reading threads and writing threads m simulating real world devices.
		
		BankAccount account=new BankAccount();
		
		Thread reader1=new Thread(()->{
			for(int i=0;i<3;i++) {
				account.viewBalance("Reader-1");
			}
		});
		
		Thread reader2=new Thread(()->{
			for(int i=0;i<3;i++) {
				account.viewBalance("Reader-2");
			}
		});
		
		Thread writer1=new Thread(()->{
			account.deposit("Writer-1", 300);
		});
		
		Thread writer2=new Thread(()->{
			account.withdraw("Writer-2", 1100);
		});
		
		reader1.start();
		reader2.start();
		writer1.start();
		writer2.start();
		
		reader1.join();
		reader2.join();
		writer1.join();
		writer2.join();
		
// all threads will run simultaneously , but reader thread will wait , until writer thread hasn't finished execution
		
	}
}

package sync;

public class MySync extends Thread{

	static int counter=0;
	
	static int counter2=0;
	
	public MySync(String name) {
		super(name);
	}
	
	public  void run() {
		
		for(int i=0;i<1000;i++) {
			synchronized(MySync.class) {
			  counter++;
			  System.out.println("Printing counter: "+counter+" by Thread :"+ Thread.currentThread().getName());
			}
		}
		
		for(int i=0;i<1000;i++) {
			increment();
		}
		
	}
	
	public static synchronized void increment() {
		counter2++;
		System.out.println("Printing counter2: "+counter2+" by Thread :"+ Thread.currentThread().getName());
		
	}
	
	
	
	
}

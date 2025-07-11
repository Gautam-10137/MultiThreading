package thread_pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SolutionUsingThreadPool {

	private static long factorial(int n) {
		try {
			Thread.sleep(1000);
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		
		long result=1;
		for(int i=2;i<=n;i++) {
			result=result*i;
		}
		return result;
	}
	
	public static void main(String[] args) {

		ExecutorService executor=  Executors.newFixedThreadPool(5);
		
		long startTime=System.currentTimeMillis();
		for(int i=1;i<=10;i++) {
			// using lambda expression
			    int finalI=i;
				executor.submit(()->{
					long result=factorial(finalI);
					System.out.println(result);
				});
		}
		
		
		executor.shutdown();
		try {
			// waiting for some time for each thread in thread-pool so that they complete their task. 
			executor.awaitTermination(10, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Using threads for each computation takes: " +(System.currentTimeMillis()-startTime));
		
	}

}

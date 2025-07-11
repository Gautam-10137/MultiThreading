package thread_pool;

public class SolutionUsingThreads {
	
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
	
	public static void main(String[] args) throws InterruptedException {
		
//		Using threads for each computation is not a good approach, since it will have heavy resource usage.
		
//     using a thread for each computation
		for(int i=1;i<=10;i++) {
		// using lambda expression
		    int finalI=i;
			Thread thread=new Thread(()->{
				long result=factorial(finalI);
				System.out.println(result);
			});
			thread.start();
		}

//		Generally, this is not a good approach. Here we not waiting for threads to complete their execution.
//		Since we haven't used .join() for threads
		
//		Now, we will store references for each thread
		long startTime2=System.currentTimeMillis();
		Thread threads[]=new Thread[10];
		for(int i=1;i<=10;i++) {
			// using lambda expression
			    int finalI=i;
				threads[i-1] =new Thread(()->{
					long result=factorial(finalI);
					System.out.println(result);
				});
				threads[i-1].start();
		}
		
		for(Thread thread:threads) {
			thread.join();
		}
        System.out.println("Using threads for each computation takes: " +(System.currentTimeMillis()-startTime2));
	}

}

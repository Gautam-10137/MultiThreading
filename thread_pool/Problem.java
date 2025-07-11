package thread_pool;



public class Problem {

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
    //   It takes so much time to compute each factorial sequentially
		long startTime=System.currentTimeMillis();
		for(int i=1;i<=10;i++) {
			long result=factorial(i);
			System.out.println(result);
		}
		
		System.out.println("Time taken: "+(System.currentTimeMillis()-startTime));
		
		
		
	}

}

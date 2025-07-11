package thread_pool;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExploringExecutorService {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// TODO Auto-generated method stub
		
		ExecutorService executor=Executors.newSingleThreadExecutor();
		Future<Integer> future= executor.submit(()->10); // it will auto take it as Callable

		if(future.isDone()) {
			System.out.println("Task is completed");
		} // this will not print, since till now task is not completed
		
		System.out.println(future.get()); // using get(), we will wait for task completion
		
		if(future.isDone()) {  // now , it will print since now task is completed.
			System.out.println("Task is completed");
		}
		
		executor.shutdown();
		
		// there are two things Callable and runnable, callable is using when we have to return something
		// and runnable is used when we don't returning anything. 
		// Callable has return type Future.
		// if we donot return anything, then executor will auto take it as runnable , otherwise callable
		// also, call method of callable ,throws exception, so no need to do try/catch while using thread inside call()
		
		ExecutorService executor2=Executors.newSingleThreadExecutor();
		executor2.submit(()->System.out.println("Hello, Gautam"));
		executor2.shutdown();
		// But if we want to check for status , we need to use Future type.
		// Note: Runnable dono't return anything, but .submit() return Future.
		
		// checking status
		ExecutorService executor3=Executors.newSingleThreadExecutor();
		Future<?> future2= executor3.submit(()->System.out.println("Hii!"));
		System.out.println(future2.state());
		future2.get();  // to block the further execution until this task is completed 
		executor3.shutdown();
		
		Callable<Integer> callable1=()->{
			System.out.println("Task 1");
			return 1;
		};
		
		Callable<Integer> callable2=()->{
			System.out.println("Task 2");
			return 2;
		};
		
		Callable<Integer> callable3=()->{
			System.out.println("Task 3");
			return 3;
		};
		
		ExecutorService executor4=Executors.newFixedThreadPool(2);
		List<Callable<Integer>> list=Arrays.asList(callable1,callable2,callable3);
		
		// note: invokeAll() will block the further execution, until all tasks completed
		List<Future<Integer>> futures= executor4.invokeAll(list);  
		// also we can use time-limits with invokeAll()
		
		for(Future<Integer> f:futures) {
			System.out.println(f.get());
		}
		
		executor4.shutdown();
		System.out.println("Yes, it blocked the execution");
		
		
		
	}

}

package thread_pool;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class UsingScheduledExecutorService {

	public static void main(String[] args) {

		// Scheduled Executor service is used to execute task after delay or execute periodically
		ScheduledExecutorService scheduler=Executors.newScheduledThreadPool(1);
		
		scheduler.schedule(()->{ System.out.println("Hello, Gautam"); }, 
				          2000 ,
				          TimeUnit.MILLISECONDS);
		
		scheduler.shutdown();
		
        ScheduledExecutorService scheduler2=Executors.newScheduledThreadPool(3);
		
        scheduler2.scheduleAtFixedRate(()->{ System.out.println("Hii, Task is Running at fixed rate");},
				                      3, 
				                      2, 
				                      TimeUnit.SECONDS);
		
        scheduler2.shutdown();  // This task will not run, Since we haven't defined when to do shutdown.
		                       // This task will keep on running, infinitely, if don't define shutdown.
		                       // Here we have defined the shutdown immediately. 
		                       // executor add tasks in queue, and this shutdown() is not entered in queue, it just do shutdown
		                       
		// Hence we will add shutdown() as a task in queue of executor
		
        ScheduledExecutorService scheduler3=Executors.newScheduledThreadPool(3);
		
        scheduler3.scheduleAtFixedRate(()->{ System.out.println("Hii, Task is Running at fixed rate");},
				                      3, 
				                      2, 
				                      TimeUnit.SECONDS);
        scheduler3.schedule(()->{ 
			                    System.out.println("Performing shutdown of fixed rate"); 
			                    scheduler3.shutdown();
			                }, 
				            20, 
				            TimeUnit.SECONDS);
        
        // Running task with fixed delay
        // Problem with fixed rate is that we haven;t consider the time of execution of task, task can be executed in any time;
        // so , here we will consider time taken to complete task.
        
        ScheduledExecutorService scheduler4=Executors.newScheduledThreadPool(1);
        
		// we have tried to use previous scheduler3 , by increasing pool size to 3.
        scheduler3.scheduleWithFixedDelay(()->{ System.out.println("Hii, Task is Running at fixed delay");},
                6, 
                4, 
                TimeUnit.SECONDS);
        
        
        scheduler4.scheduleWithFixedDelay(()->{ System.out.println("Hii, Task is Running at fixed delay");},
				                      21, 
				                      4, 
				                      TimeUnit.SECONDS);
        
        scheduler4.schedule(()->{ 
			                    System.out.println("Performing shutdown of fixed delay"); 
			                    scheduler4.shutdown();
			                }, 
				            40, 
				            TimeUnit.SECONDS);
		
	}

}

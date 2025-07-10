package thread_communication;

import java.util.LinkedList;
import java.util.Queue;

class SharedBuffer{
	
	// buffer
	private final Queue<Integer> queue=new LinkedList<Integer>();
	private int capacity=5;
	
	// produce
	public synchronized void produce(int item) throws InterruptedException {
		while(queue.size()==capacity) {
			System.out.println("Buffer is full. Producer is waiting!");
			wait();
		}
		queue.offer(item);
		System.out.println("Produces: "+item);
		notifyAll();  // waking up consumer
	}
	
	//consume
	public synchronized void consume() throws InterruptedException {
		while(queue.isEmpty()) {
			System.out.println("Buffer is empty.Consumer is waiting!");
			wait();
		}
		
		int item=queue.poll();
		System.out.println("Consumes: "+item);
		notifyAll();   // waking up producer
	}
}

class Producer extends Thread{
	private final SharedBuffer buffer;
	
	public Producer(SharedBuffer buffer) {
		this.buffer=buffer;
		
	}
	
	public void run() {
		
		for(int i=1;i<=5;i++) {
			try {
				buffer.produce(i);
			    Thread.sleep(500);  // time to produce
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

class Consumer extends Thread{
	private final SharedBuffer buffer;
	
	public Consumer(SharedBuffer buffer) {
		this.buffer=buffer;
		
	}
	
	public void run() {
		
		for(int i=1;i<=5;i++) {
			try {
				buffer.consume();
			    Thread.sleep(600);   // time to consume
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

public class ProducerConsumer {

	public static void main(String args[]) throws InterruptedException {
		
		SharedBuffer sharedBuffer=new SharedBuffer();
		
		Producer producer=new Producer(sharedBuffer);
		
		Consumer consumer=new Consumer(sharedBuffer);
		
		producer.start();
//		producer.join();
		consumer.start();
		
		producer.join();
		consumer.join();
	}
}

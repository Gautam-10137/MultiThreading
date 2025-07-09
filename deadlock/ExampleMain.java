package deadlock;

// Reason of deadlock: when we apply synchronized keyword with method, then it locks the current object itself.
// that's why thread-0(taks1) holds then Paper's object and waiting for release of lock of Pen's object.
// And thread-1(taks2) holds then Pen's object and waiting for release of lock of Paper's object.
// And Both objects are Shared Resource.
// Solution for deadlock: acquire lock in serial manner.
class Pen{
	
	public synchronized void writeWithPenAndPaper(Paper paper) {
		System.out.println(Thread.currentThread().getName()+" is using Pen "+this +"and trying to use Paper");
		paper.finishWriting();
	}
	
	public synchronized void finishWriting() {
		System.out.println(Thread.currentThread().getName()+" finish using Pen "+this);
	}
}

class Paper{
	
	public synchronized void writeWithPaperAndPen(Pen pen) {
		System.out.println(Thread.currentThread().getName()+" is using Paper "+this +"and trying to use Pen");
		pen.finishWriting();
	}
	
	public synchronized void finishWriting() {
		System.out.println(Thread.currentThread().getName()+" finish using Paper "+this);
	}
}

class Task1 implements Runnable{
	
	private Paper paper;
	private Pen pen;

	public Task1(Paper paper,Pen pen) {
		this.paper=paper;
		this.pen=pen;
	}
	@Override
	public void run() {
        paper.writeWithPaperAndPen(pen);
	}
	
}

class Task2 implements Runnable{
	
	private Paper paper;
	private Pen pen;

	public Task2(Pen pen,Paper paper) {
		this.pen=pen;
		this.paper=paper;
	}
	@Override
	public void run() {
        pen.writeWithPenAndPaper(paper);
	}
	
}

public class ExampleMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Paper paper=new Paper();
		Pen pen = new Pen();
		
		Thread t1=new Thread(new Task1(paper,pen));
		Thread t2=new Thread(new Task2(pen,paper));
		
        t1.start();
        t2.start();
	}

}

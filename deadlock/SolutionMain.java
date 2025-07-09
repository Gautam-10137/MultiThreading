package deadlock;

// Solution: we will acquire locks in serial manner, we will proceed only if all locks we will need are acquired
//           by using the synchronized block
class Task3 implements Runnable{
	
	private Paper paper;
	private Pen pen;

	public Task3(Paper paper,Pen pen) {
		this.paper=paper;
		this.pen=pen;
	}
	@Override
	public void run() {
		synchronized(pen) {
			paper.writeWithPaperAndPen(pen);
		}
        
	}
	
}

class Task4 implements Runnable{
	
	private Paper paper;
	private Pen pen;

	public Task4(Pen pen,Paper paper) {
		this.pen=pen;
		this.paper=paper;
	}
	@Override
	public void run() {
		// firstly acquiring all the locks we will need
		synchronized(paper) {
			pen.writeWithPenAndPaper(paper);
		}
        
	}
	
}

public class SolutionMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Paper paper=new Paper();
		Pen pen = new Pen();
		
		Thread t1=new Thread(new Task3(paper,pen));
		Thread t2=new Thread(new Task4(pen,paper));
		
        t1.start();
        t2.start();
	}

}

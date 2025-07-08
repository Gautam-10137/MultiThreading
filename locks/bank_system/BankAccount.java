package locks.bank_system;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class BankAccount {

	private double balance=1000;
	
	private final ReentrantReadWriteLock rwLock=new ReentrantReadWriteLock();
	private final Lock readLock=rwLock.readLock();
	private final Lock writeLock=rwLock.writeLock();
	
	public void viewBalance(String threadName) {
		readLock.lock();
		try {
			
			System.out.println(threadName+" is viewing balance : Rs."+balance);
			Thread.sleep(100);
		}catch(InterruptedException e) {
            e.printStackTrace();			
		}
		finally {
			readLock.unlock();
		}
	}
	
	public void deposit(String threadName, double amount) {
		writeLock.lock();
		try {
			System.out.println(threadName+" is depositing Rs."+amount);
			Thread.sleep(100);  // processing time for deposit
			balance+=amount;
			System.out.println(threadName+ " finished depositing. New Balance Rs:"+balance);
			
		}catch(InterruptedException e) {
            e.printStackTrace();			
		}
		finally {
			writeLock.unlock();
		}
	}
	
	public void withdraw(String threadName, double amount) {
		writeLock.lock();
		try {
			System.out.println(threadName+" is withdrawing Rs."+amount);
			Thread.sleep(100);  // processing time for withdrawing
		
			if(balance>=amount) {
				balance-=amount;
				System.out.println(threadName+ " finished withdrawing. New Balance Rs:"+balance);
			}
			else {
				System.out.println(threadName+ " : Insufficient Balance");
			}
			
			
		}catch(InterruptedException e) {
            e.printStackTrace();			
		}
		finally {
			writeLock.unlock();
		}
	}
}

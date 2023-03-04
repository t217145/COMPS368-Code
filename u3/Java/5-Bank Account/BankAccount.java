
import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BankAccount {

    public static void main(String[] args) {

        Account myAcc = new Account();

        System.out.println("Thread 1\t\tThread 2\t\tBalance");
        Runnable task1 = new Boss(myAcc);
        Runnable task2 = new Servant(myAcc);
        Thread thread1 = new Thread(task1);
        Thread thread2 = new Thread(task2);
        thread1.start();
        thread2.start();
    }
}

class Boss implements Runnable {
    Account myAcc;
    public Boss(Account acc) {
        myAcc = acc;
    }
    public void run() {
        try {
            while (true) {
                int amount = (int) (Math.random() * 10) + 1;// randomly deposits 1 to 10 dollars
                System.out.println("Deposit attempt: " + amount);
                myAcc.deposit(amount);
                Thread.sleep(500);// sleep for half a second
            }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}

class Servant implements Runnable {
    Account myAcc;
    public Servant(Account acc) {
        myAcc = acc;
    }
    public void run() {
		while (true) {
			// randomly withdraw 3 to 12 dollars
			int amount = (int) (Math.random() * 10) + 3;
			System.out.println("\t\t\tWithdraw attempt: " + amount);
			myAcc.withdraw(amount);
		}
    }
}

class Account {

    Lock lock = new ReentrantLock();
    Condition hasDeposit = lock.newCondition();
    private int balance = 0;

    public int getBalance() {
        return balance;
    }

    public void withdraw(int amount) {
        lock.lock(); // Acquire the lock
        try {
            // make sure there are enough amount to be withdrawed
            while (balance < amount) {
                hasDeposit.await();
            }
            balance -= amount;
            System.out.println("\t\t\t\t\t\tW: " + getBalance());
        } catch (InterruptedException ex) {
		//} catch (Exception ex) {
            Logger.getLogger(Account.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            lock.unlock();
        }
    }

    public void deposit(int amount) {
        lock.lock(); // Acquire the lock
        try {
            balance += amount;
            System.out.println("\t\t\t\t\t\tD: " + getBalance());            
        } finally {
            // signal and unlocks should be in finally generally
            hasDeposit.signalAll();
            lock.unlock();
        }
    }
}

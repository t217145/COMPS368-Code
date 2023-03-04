
public class SharedDataTest {

    public static void main(String args[]) {
        if (args.length != 2) {
            System.out.println("Correct usage: java SharedDataTest numThreads reps");
            System.exit(1);
        }
        int numThreads = Integer.parseInt(args[0]);
        int reps = Integer.parseInt(args[1]);
        int correctSum = numThreads * reps;
        Buf buf = new Buf();
        Thread[] myThread;
        myThread = new Thread[numThreads];
        for (int i = 0; i < numThreads; i++) {
            myThread[i] = new Thread(new Adder(reps, buf));
        }
        for (int i = 0; i < numThreads; i++) {
            myThread[i].start();
        }
        try {
            for (int i = 0; i < numThreads; i++) {
                myThread[i].join();
            }
        } catch (InterruptedException ie) {
            System.out.println("Main thread cannot join other threads.");
        }
        System.out.println("Computed sum = " + buf.getSum());
        System.out.println("Correct sum = " + (numThreads * reps));
        System.out.println("Overlapped increments = "
                + 100.0 * (correctSum - buf.getSum()) / correctSum + "%");
    }
}

// class for common shared buffer
class Buf {
    private int sum;
    
    public Buf() {
        sum = 0;
    }
    
    public int getSum() {
        return sum;
    }

    // method 1: add synchronized to make it thread safe
    //synchronized public void increment()
    public void increment() 
    {
        int tmpSum = sum;
        System.out.println("Thread-" + Thread.currentThread().getId() + "\tread " + tmpSum);
        sum = tmpSum + 1;
    }   
}

class Adder implements Runnable {

    private int repetitions;
    Buf myBuf;

    public Adder(int count, Buf buf) {
        repetitions = count;
        myBuf = buf;
    }
    public void run() {
        for (int i = 0; i < repetitions; i++) {
            // method 2: use lock in myBuf
            // synchronized(myBuf) 
            {
                myBuf.increment();
            }
        }
    }
}

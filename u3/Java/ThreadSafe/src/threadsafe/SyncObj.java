/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadsafe;

/**
 *
 * @author Cyrus Cheng
 */
public class SyncObj{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        DummyThreadSafeSyncObj[] t = new DummyThreadSafeSyncObj[10];
        CounterSafeSyncObj _cnt = new CounterSafeSyncObj();
        
        System.out.println("Create 10 threads, each thread run 10000 times add one");
        for(int i=0;i<10;i++){
            t[i] = new DummyThreadSafeSyncObj(_cnt);
            t[i].start();
        }
        
        try{
            for(int i=0;i<10;i++){
                t[i].join();
            }   
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
        
        System.out.println("Expected result : " + (10 * 10000));
        System.out.println("Actual result : " + _cnt.getCnt());
    }
    
}

class CounterSafeSyncObj{

    private int cnt;

    public void addOne(){
        synchronized(this){
            int tmp = this.cnt;
            tmp = tmp + 1;
            this.cnt = tmp;
        }
    }

    public int getCnt(){
        return this.cnt;
    }
}

class DummyThreadSafeSyncObj extends Thread{

    private CounterSafeSyncObj _cnt;

    public DummyThreadSafeSyncObj(CounterSafeSyncObj cnt){
        this._cnt = cnt;
    }

    @Override
    public void run(){
        for(int i=0;i<10000;i++){
            this._cnt.addOne();
        }
        System.out.println("called add one 100 times");
    }
}


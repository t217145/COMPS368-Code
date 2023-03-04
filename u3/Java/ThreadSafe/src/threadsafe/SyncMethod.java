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
public class SyncMethod {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        DummyThreadSafeSyncMethod[] t = new DummyThreadSafeSyncMethod[10];
        CounterSafeSyncMethod _cnt = new CounterSafeSyncMethod();
        
        System.out.println("Create 10 threads, each thread run 10000 times add one");
        for(int i=0;i<10;i++){
            t[i] = new DummyThreadSafeSyncMethod(_cnt);
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

class CounterSafeSyncMethod{

    private int cnt;

    public synchronized void addOne(){
        int tmp = this.cnt;
        tmp = tmp + 1;
        this.cnt = tmp;
    }

    public int getCnt(){
        return this.cnt;
    }
}

class DummyThreadSafeSyncMethod extends Thread{

    private CounterSafeSyncMethod _cnt;

    public DummyThreadSafeSyncMethod(CounterSafeSyncMethod cnt){
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

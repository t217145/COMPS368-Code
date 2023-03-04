/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadsafe;

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Cyrus Cheng
 */
public class UseLock {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        DummyThreadSafeLock[] t = new DummyThreadSafeLock[10];
        CounterSafeLock _cnt = new CounterSafeLock();
        
        System.out.println("Create 10 threads, each thread run 10000 times add one");
        for(int i=0;i<10;i++){
            t[i] = new DummyThreadSafeLock(_cnt);
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

class CounterSafeLock{

    private int cnt;
    private final ReentrantLock _lock;
    
    public CounterSafeLock(){
        this._lock = new ReentrantLock();
    }

    public void addOne(){
        _lock.lock();
        try{
            int tmp = this.cnt;
            tmp = tmp + 1;
            this.cnt = tmp;
        }finally{
          _lock.unlock();  
        }
    }

    public int getCnt(){
        return this.cnt;
    }
}

class DummyThreadSafeLock extends Thread{

    private CounterSafeLock _cnt;

    public DummyThreadSafeLock(CounterSafeLock cnt){
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
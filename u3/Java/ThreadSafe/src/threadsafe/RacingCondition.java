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
public class RacingCondition {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        DummyThread[] t = new DummyThread[10];
        Counter _cnt = new Counter();
        
        System.out.println("Create 10 threads, each thread run 1000 times add one");
        for(int i=0;i<10;i++){
            t[i] = new DummyThread(_cnt);
            t[i].start();
        }
        
        try{
            for(int i=0;i<10;i++){
                t[i].join();
            }   
        }catch(InterruptedException e){
            System.out.println(e.getMessage());
        }
        
        System.out.println("Expected result : " + (10 * 1000));
        System.out.println("Actual result : " + _cnt.getCnt());
    }
    
}

class Counter{

    private int cnt;

    public void addOne(){
        int tmp = this.cnt;
        tmp = tmp + 1;
        this.cnt = tmp;
    }

    public int getCnt(){
        return this.cnt;
    }
}

class DummyThread extends Thread{

    private Counter _cnt;

    public DummyThread(Counter cnt){
        this._cnt = cnt;
    }

    @Override
    public void run(){
        for(int i=0;i<1000;i++){
            this._cnt.addOne();
        }
        System.out.println("called add one 100 times");
    }
}
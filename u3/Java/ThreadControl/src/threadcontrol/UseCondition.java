/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadcontrol;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Cyrus Cheng
 */
public class UseCondition {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Semaphore account = new Semaphore(0);
        BankUseCondition _bank = new BankUseCondition();
        new BossUseCondition(_bank, account).start();
        for(int i=0; i<10; i++){
            new EmployeeUseCondition(_bank, account).start();
        }
    }
    
}

class BankUseCondition{

    private int _balance = 0;
    private final ReentrantLock _lock;
    private final Condition _zeroNegBalance;
    
    public BankUseCondition(){
        this._lock = new ReentrantLock();
        this._zeroNegBalance = this._lock.newCondition();
        
    }
    
    public void withdraw(int amt){
        this._lock.lock();
        try{
            while(this._balance < amt){
                this._zeroNegBalance.await();
            }
            this._balance -= amt;
        } catch(InterruptedException e) {
            System.out.println(e.getMessage());
        } finally{
            this._lock.unlock();
        }
    }
    
    public void deposit(int amt){
        this._lock.lock();
        try{
            this._balance += amt;
        } catch(Exception e) {
            System.out.println(e.getMessage());
        } finally{
            this._zeroNegBalance.signalAll();
            this._lock.unlock();
        }        
    }
    
    public int GetBalance(){
        return this._balance;
    }
}

class BossUseCondition extends Thread{
        private Semaphore acct;
    private final BankUseCondition _bank;
    private final Random _r;
    
    public BossUseCondition(BankUseCondition bank, Semaphore _acct){
        this.acct = _acct;
        this._bank = bank;
        this._r = new Random();
    }
    
    @Override
    public void run(){
        try{
            for(int i=0; i<100; i++){
                this._r.setSeed(System.currentTimeMillis());
                int _amt = this._r.nextInt(50) + 1;
                System.out.println("\t\t\tBoss deposit : " + _amt);
                this._bank.deposit(_amt);
                System.out.println("Current balance after deposit : " + this._bank.GetBalance());
                Thread.sleep(1500);
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }finally{
            
        }
    }
}

class EmployeeUseCondition extends Thread{
    
    private final BankUseCondition _bank;
    private final Random _r;
    
    public EmployeeUseCondition(BankUseCondition bank){
        this._bank = bank;
        this._r = new Random();
    }
    
    @Override
    public void run(){
        try{
            for(int i=0; i<100; i++){
                this._r.setSeed(System.currentTimeMillis() * System.currentTimeMillis());
                int _amt = this._r.nextInt(50) + 1;
                System.out.println("\t\t\t\t\t\tEmployee withdraw : " + _amt);
                this._bank.withdraw(_amt);
                System.out.println("Current balance after withdraw : " + this._bank.GetBalance());
                Thread.sleep(1500);
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }finally{
            
        }
    }
}
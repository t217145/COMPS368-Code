/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadcontrol;

import java.util.Random;

/**
 *
 * @author Cyrus Cheng
 */
public class NoCondition {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        BankNoCondition _bank = new BankNoCondition();
        new BossNoCondition(_bank).start();
        for(int i=0; i<10; i++){
            new EmployeeNoCondition(_bank).start();
        }
    }
    
}

class BankNoCondition{
    
    private int _balance = 0;
    
    public synchronized void withdraw(int amt){
        this._balance -= amt;
    }
    
    public synchronized void deposit(int amt){
        this._balance += amt;
    }
    
    public int GetBalance(){
        return this._balance;
    }
}

class BossNoCondition extends Thread{
    
    private final BankNoCondition _bank;
    private final Random _r;
    
    public BossNoCondition(BankNoCondition bank){
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

class EmployeeNoCondition extends Thread{
    
    private final BankNoCondition _bank;
    private final Random _r;
    
    public EmployeeNoCondition(BankNoCondition bank){
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
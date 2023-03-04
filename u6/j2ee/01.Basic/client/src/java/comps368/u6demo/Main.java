/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comps368.u6demo;

import java.util.Scanner;
import javax.ejb.EJB;

/**
 *
 * @author Cyrus Cheng
 */
public class Main {
    
    @EJB
    private static MyStatefulBeansRemote svcFul;
    @EJB
    private static MyStatelessBeansRemote svcLess;
    
    public static void main(String[] args) throws Exception {
        //Context ctx = new InitialContext();
        //MyBeansRemote svc = (MyBeansRemote)ctx.lookup("java:global/ejb/MyBeans");
        
        //Get Stateless or Stateful
        String types = null;
        do{
            types = getString("State[L]ess or State[F]ul : ");
        }while(!types.toUpperCase().equals("L") && !types.toUpperCase().equals("F"));
        
        MyBeansRemote svc = (types.toUpperCase().equals("L")) ? svcLess : svcFul;
        
        //Get Name
        String name = getString("What's your name : ");
        
        Thread[] ts = new Thread[100];
        
        //thread 1 - assign the name into the bean
        ts[0] = new Thread(new MyThread(svc, name));
        ts[0].start();
        ts[0].join(); //must wait this thread finish first
        
        //thread 2 to 100 - only get value
        for(int i=1;i<100;i++){
            ts[i] = new Thread(new MyThread(svc, null));
            ts[i].start();
        }
        
        //end the main until all thread finished
        for(int i=1;i<100;i++){
            ts[i].join();
        }        
    }
    
    public static String getString(String msg){
        String rtn = null;
        try{
            Scanner _in = new Scanner(System.in);
            System.out.print(msg);
            rtn = _in.nextLine();
        } catch (Exception e){
            e.printStackTrace();
        }
        return rtn;
    }
}

class MyThread implements Runnable{
    
    private MyBeansRemote svc;
    private String name;
    
    public MyThread(MyBeansRemote _svc, String _name){
        this.svc = _svc;
        this.name = _name;
    }
    
    public void run(){
        try{
            if(this.name != null && !this.name.isEmpty()){
                svc.SetStr(this.name);
            }
            String str = svc.GetStr();
            if(str == null || str.isEmpty()){
                System.err.println("Null Value");
            } else {
                System.out.println(str);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}

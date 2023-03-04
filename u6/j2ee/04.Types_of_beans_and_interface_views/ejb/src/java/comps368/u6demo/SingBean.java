/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comps368.u6demo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Remove;
import javax.ejb.Singleton;

/**
 *
 * @author Cyrus Cheng
 */
@Singleton
@Lock(LockType.READ)
public class SingBean implements SingBeanRemote {

    private final String BEANTYPE = "Singleton";
    private String str;
    private long sleepTime = 5;
    
    @EJB
    private LocalRemote local;
    
    @EJB
    private LocalRemote2 local2;
    
    @Override
    @Lock(LockType.WRITE)
    public void SetStr(String str1) {
        try{
            this.str = str1;
            Thread.sleep(sleepTime * 1000);
            System.out.println(local.LocalMtd(this.str));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public String GetStr() {
        try{
            Thread.sleep(sleepTime * 1000);
        }catch(Exception e){
            e.printStackTrace();
        }        
        return this.str;
    }
    
    @Remove
    @Override
    public void Quit(){
        System.out.println(local2.LocalMtd(BEANTYPE + " :: GoodBye"));
    }    
    
    @PreDestroy
    public void PD(){
        System.out.println(BEANTYPE + " :: PreDestroy");
    }
    
    @PostConstruct
    public void PoC(){
        System.out.println(BEANTYPE + " :: PostConstruct");
    }    
}

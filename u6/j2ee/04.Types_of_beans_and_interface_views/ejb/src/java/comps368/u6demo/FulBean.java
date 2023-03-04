/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comps368.u6demo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Remove;
import javax.ejb.Stateful;

/**
 *
 * @author Cyrus Cheng
 */
@Stateful
public class FulBean implements FulBeanRemote {

    private final String BEANTYPE = "Stateful";
    private String str;
    
    @Override
    public void SetStr(String str1) {
        this.str = str1;
    }

    @Override
    public String GetStr() {
        return this.str;
    }
    
    //@Remove
    @Override
    public void Quit(){
        System.out.println(BEANTYPE + " :: GoodBye");
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

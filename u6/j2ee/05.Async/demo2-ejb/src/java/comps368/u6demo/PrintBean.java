/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comps368.u6demo;

import java.util.Date;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

/**
 *
 * @author Cyrus Cheng
 */
@Stateless
public class PrintBean implements PrintBeanRemote {

    @Override

    @Asynchronous
    public void printing() {
        System.out.println("Inner Start : " + new Date());
        try{
            Thread.sleep(10000);//generating the report
        }catch(Exception e){
            
        }
        System.out.println("Inner End : " + new Date());
    }
    
}

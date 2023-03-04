/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comps368.u6demo;

import java.util.Date;
import javax.ejb.EJB;

/**
 *
 * @author Cyrus Cheng
 */
public class Main {

    @EJB
    private static PrintBeanRemote svc;
    
    public static void main(String[] args) {
        System.out.println("Outer Start : " + new Date());
        svc.printing();
        System.out.println("Outer End : " + new Date());
    }
    
}

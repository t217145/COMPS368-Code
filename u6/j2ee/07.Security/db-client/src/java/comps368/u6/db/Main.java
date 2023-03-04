/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comps368.u6.db;

import javax.ejb.EJB;

/**
 *
 * @author Cyrus Cheng
 */
public class Main {

    @EJB
    private static TestBeanRemote svc;
    
    public static void main(String[] args) {
        System.out.println(svc.GetOne(9));
    }
    
}
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comps368.u7;

import javax.ejb.EJB;

/**
 *
 * @author Cyrus Cheng
 */
public class Main {

    @EJB
    private static HelloRemote svc;
    
    public static void main(String[] args) {
        //System.out.println(svc.HelloEveryone("Testing"));
        System.out.println(svc.HelloUser("Testing User"));
        System.out.println(svc.HelloAdmin("Testing Admin"));
    }
    
}

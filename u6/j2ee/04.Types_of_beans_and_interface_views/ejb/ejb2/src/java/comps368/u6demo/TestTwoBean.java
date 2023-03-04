/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comps368.u6demo;

import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Cyrus Cheng
 */
@Stateless
public class TestTwoBean implements TestTwoBeanRemote {

    @EJB
    private LocalRemote2 local2;
    
    @Override
    public String test2(String name) {
        System.out.println(local2.LocalMtd(name + ". From Bean2"));
        return name + ". From Bean2";
    }

}

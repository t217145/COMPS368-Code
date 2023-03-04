/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comps368.u6demo;

import javax.ejb.Local;

/**
 *
 * @author Cyrus Cheng
 */
@Local
public interface TestBeansLocal {

    String Testing(String String);
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comps368.u6.db;

import javax.ejb.Remote;

/**
 *
 * @author Cyrus Cheng
 */
@Remote
public interface TestBeanRemote {

    String GetOne(int id);
    
}

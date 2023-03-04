/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comps368.u7;

import javax.ejb.Remote;

/**
 *
 * @author Cyrus Cheng
 */
@Remote
public interface HelloRemote {

    String HelloEveryone(String name);

    String HelloUser(String name);

    String HelloAdmin(String name);
    
}

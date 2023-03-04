/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comps368.u7;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;

/**
 *
 * @author Cyrus Cheng
 */
@Stateless
public class Hello implements HelloRemote {

    @Override
    @PermitAll
    public String HelloEveryone(String name) {
        return "Hello, " + name;
    }

    @Override
    @RolesAllowed({"admin","user"})
    public String HelloUser(String name) {
        return "Hello, " + name + ". You are a user";
    }

    @Override
    @RolesAllowed("admin")
    public String HelloAdmin(String name) {
        return "Hello, " + name + ". You are an admin";
    }
}

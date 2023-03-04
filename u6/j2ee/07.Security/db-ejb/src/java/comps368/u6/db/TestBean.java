/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comps368.u6.db;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Cyrus Cheng
 */
@Stateless
public class TestBean implements TestBeanRemote {

    @PersistenceContext(unitName="db-ejbPU")
    private EntityManager em;
    
    @Override
    @RolesAllowed("admin")
    public String GetOne(int id) {
        Items i = em.find(Items.class, id);
        String name = (i == null) ? "No result" : i.getItemName();
        return name;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comps368.u6demo;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateful;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 *
 * @author Cyrus Cheng
 */
@Stateful
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class dbBean implements dbBeanRemote {

    private List<Students> stds = new ArrayList<Students>();
    
    @PersistenceContext(unitName = "db-ejbPU", type=PersistenceContextType.EXTENDED)
    private EntityManager em;

    @Override
    public void AddStd(Students std) {
        stds.add(std);
    }

    @Override
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void Confirm(){
        for(Students s : stds){
            s.getsCode().codePointAt(0);
            em.persist(s);
            System.out.println(s.getsName() + " saved to DB");
        }
        stds.clear();
    }
}

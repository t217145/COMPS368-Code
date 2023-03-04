/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package u4;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Cyrus Cheng
 */
public class JPADemo001 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            //1. get emf
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPADemo001PU");
            //2. get em
            EntityManager em = emf.createEntityManager();
            //3. CRUD
            
            Contacts myRecord = em.find(Contacts.class, 1);
            
            em.getTransaction().begin();
            
//            myRecord.setEmail("cyrus@cyrus-sir.com");
//            em.persist(myRecord);

            em.remove(myRecord);
            
            em.getTransaction().commit();            
            
            //System.out.println(myRecord.getName() + "\t" + myRecord.getEmail());

            System.out.println("Done");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}

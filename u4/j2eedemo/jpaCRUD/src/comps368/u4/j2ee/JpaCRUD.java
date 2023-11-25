
package comps368.u4.j2ee;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JpaCRUD {

    public static void main(String[] args) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpaCRUDPU");
        EntityManager em = emf.createEntityManager();

        // CRUD
        // 1. Create
//        Contacts newCyrus = new Contacts("Cyrus", "Tsz Wan Shan");
//        em.getTransaction().begin();
//        em.persist(newCyrus);
//        em.getTransaction().commit();
//        
//        Contacts newRuby = new Contacts("Ruby", "Tsz Wan Shan");
//        em.getTransaction().begin();
//        em.persist(newRuby);
//        em.getTransaction().commit();
//        
//        Contacts newMandy = new Contacts("Mandy", "Tsz Wan Shan");
//        em.getTransaction().begin();
//        em.persist(newMandy);
//        em.getTransaction().commit();        
        
        // 2. Retrieve
        //Contacts mandy = em.find(Contacts.class, 3);
        // 2a. NamedQuery
        Query getMandyQuery = em.createNamedQuery("findByName");
        getMandyQuery.setParameter("cname", "Man%");
        Contacts mandy = (Contacts)(getMandyQuery.getSingleResult());
        System.out.println(mandy);
        
        Query getAllTWSQ = em.createNamedQuery("findByAddr");
        getAllTWSQ.setParameter("cAddr", "%Wan%");
        List<Contacts> allTWS = getAllTWSQ.getResultList();
        allTWS.stream().forEach(System.out::println); 
        
        // 2b. AdhocQuery / JPQL
        Query adhocQ = em.createQuery("select c from Contacts c where c.name like :cname");
        adhocQ.setParameter("cname", "Cyrus");
        Contacts cyrus = (Contacts)(adhocQ.getSingleResult());
        System.out.println(cyrus);
        
        // 3. Update
//        em.getTransaction().begin();
//        mandy.setAddr("Mong Kok");
//        em.persist(mandy);
//        em.getTransaction().commit();
      
        // 4. Delete
//        em.getTransaction().begin();
//        em.remove(mandy);
//        em.getTransaction().commit();
    }
    
}

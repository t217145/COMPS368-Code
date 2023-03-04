package basicjpa;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class BasicJPA {

    public static void main(String[] args) {
        try{
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("BasicJPAPU");
            EntityManager em = emf.createEntityManager();
            
            Students s = em.find(Students.class, 1);
            
            if(s == null){
                em.getTransaction().begin();
                
                Students sNew = new Students();
                sNew.setStdName("Cyrus");
                sNew.setGender('M');
                em.persist(sNew);
                
                em.getTransaction().commit();
                System.out.println("New Students created : " + sNew.toString());
            } else {
//                System.out.println("Before : " + s.toString());
//                
//                em.getTransaction().begin();
//                
//                s.setStdName("Cyrus Cheng");
//                em.persist(s);
//                
//                em.getTransaction().commit(); 
//                
//                System.out.println("After : " + s.toString());

                em.getTransaction().begin();
                em.remove(s);
                em.getTransaction().commit();
            }

//              Query q = em.createNamedQuery("std.myQuery2");
//              q.setParameter("surname", "%Ko");
//              List<Students> ss = q.getResultList();
//              for(Students s : ss){
//                  System.out.println(s.toString());
//              }
        }catch(Exception e){
            Logger.getLogger(BasicJPA.class.getName()).log(Level.SEVERE, e.getMessage());
        }
    }
    
}

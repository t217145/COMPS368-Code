package comps368.u4.demo.jpa;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

public class JpaDemo {

    public static void main(String[] args) {
        try{
            //1. Preparation jpabasicPU
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabasicPU");
            EntityManager em = emf.createEntityManager();
            Scanner in = new Scanner(System.in);
            
            //2. create
            System.out.print("Name : ");
            String name = in.nextLine();
            
            System.out.print("Phone : ");
            String phone = in.nextLine();

            System.out.print("Email : ");
            String email = in.nextLine();
            
            //2a. save to db
            MyContact newContact = new MyContact(name, phone, email);
            em.getTransaction().begin();
            em.persist(newContact);
            em.getTransaction().commit();
            
            //3. retrieve
            Query q = em.createQuery("select c from MyContact c");
            
            List<MyContact> allContact = q.getResultList();
            for(MyContact c : allContact){
                System.out.println("ID : " + c.getId() + "\tName : " + c.getName());
            }
        }catch(Exception e){
            e.printStackTrace();
        }//end of try-catch
    }
    
}

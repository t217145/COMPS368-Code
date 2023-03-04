package comps368.u4.entitymanagerdemo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Component
@SuppressWarnings("unchecked")
public class App01 implements CommandLineRunner{

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        //c
        DemoModel model1a = new DemoModel(0, "Cyrus", "cyrus@cyrus-sir.com");
        em.persist(model1a);

        DemoModel model1b = new DemoModel(0, "Mandy", "mandy@cyrus-sir.com");
        em.persist(model1b);
        System.out.println("Created, getting all result");

        //r
        Query q2 = em.createNamedQuery("DemoModel.getAll");
        q2.getResultList().forEach(System.out::println);

        Query q3 = em.createNamedQuery("DemoModel.getByName");
        q3.setParameter("name", "Cyrus");
        DemoModel model3 =  (DemoModel)q3.getSingleResult();
        model3.setName("Cyrus Cheng");

        System.out.println("Updated, getting all result again");
        q2.getResultList().forEach(System.out::println);

        //d
        Query q4 = em.createNamedQuery("DemoModel.getById");
        q4.setParameter("id", 2);
        DemoModel model4 =  (DemoModel)q4.getSingleResult();
        em.remove(model4);
        System.out.println("1 record deleted, go to console to check [http://localhost:8080/h2-console]");
    }
}

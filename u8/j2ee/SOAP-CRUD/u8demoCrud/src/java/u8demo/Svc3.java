package u8demo;

import java.util.List;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
@WebService(serviceName="StdSvc")
public class Svc3 {

    @PersistenceContext(unitName="u8demoCrudPU")
    private EntityManager em;
    
    @WebMethod(operationName="getAll")
    public @WebResult(name="AllStudents") List<Students> getAll(){
        return em.createNamedQuery("Students.findAll").getResultList();
    }
    
    @WebMethod(operationName="AddStd")
    public @WebResult(name="DataBaseId") int addStd(Students std){
        em.persist(std);
        Students s = (Students)(em.createNamedQuery("Students.findByOuhkID").setParameter("ouhkID", std.getOuhkID()).getResultList().get(0));
        return s.getId();
    }
}

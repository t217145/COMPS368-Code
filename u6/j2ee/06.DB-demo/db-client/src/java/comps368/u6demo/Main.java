package comps368.u6demo;

import javax.ejb.EJB;

public class Main {

    @EJB
    private static dbBeanRemote svc;
    
    public static void main(String[] args) {
        try{
            Students s1 = new Students();
            s1.setsName("Cyrus");
            s1.setsCode("s1039518");
            s1.setGender('M');
            svc.AddStd(s1);
            
            Students s2 = new Students();
            s2.setsName("Mary");
            s2.setsCode("s1234567");
            s2.setGender('F');
            svc.AddStd(s2);
            
            Students s3 = new Students();
            s3.setsName("Peter");
            s3.setsCode(null);
            s3.setGender('X');
            svc.AddStd(s3);
            
            svc.Confirm();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}

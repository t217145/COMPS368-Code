package comps368.q7;

import javax.ejb.EJB;

public class SecurityMain {

    @EJB
    public static SecurityGreetingRemote _bean;
    
    public static void main(String[] args) {
        System.out.println(_bean.Mtd1());
        
        System.out.println(_bean.Mtd2("Cyrus"));
        
        System.out.println(_bean.Mtd3("Cyrus", "Admin"));        
    }
    
}

package u6demo01client;

import comps368.u6demo.TestingRemote;
import javax.ejb.EJB;

public class Main {

    @EJB
    private static TestingRemote svc;
    
    public static void main(String[] args) {
        System.out.println(svc.Calculate("Div", 6, 3));
    }
    
}
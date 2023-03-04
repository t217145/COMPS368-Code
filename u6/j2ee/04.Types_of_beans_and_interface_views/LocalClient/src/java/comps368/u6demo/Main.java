package comps368.u6demo;

import javax.ejb.EJB;


public class Main {

    @EJB
    private static LessBeanRemote2 beans;
    
    @EJB
    private static LocalRemote2 lB;
    
    public static void main(String[] args) {
        System.out.println(lB.LocalMtd("Test"));
        //beans.SetStr("Test");
        System.out.println(beans.GetStr());
    }
    
}

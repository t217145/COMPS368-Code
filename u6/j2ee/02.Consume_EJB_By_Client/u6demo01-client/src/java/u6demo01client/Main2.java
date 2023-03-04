package u6demo01client;

import comps368.u6demo.TestingRemote;
import javax.naming.Context;
import javax.naming.InitialContext;

public class Main2 {
    public static void main(String args[]) throws Exception{
        Context ctx = new InitialContext();
        TestingRemote svc = (TestingRemote)ctx.lookup("java:global/u6demo01-ejb/Testing");
        System.out.println(svc.Calculate("Add", 5, 4));
    }
}

package comps368.u6demo;

import javax.ejb.Stateful;
import javax.naming.Context;
import javax.naming.InitialContext;

@Stateful
public class FulBean implements FulBeanRemote {

    private String str;

    @Override
    public void SetStr(String str1) {
        try{
            Context ctx = new InitialContext();
            TestBeansLocal b = (TestBeansLocal)ctx.lookup("java:u6demo02a-ejb/TestBeans");
            this.str = str1;
        }catch(Exception e){
            
        }
    }

    @Override
    public String GetStr() {
        return this.str;
    }
}

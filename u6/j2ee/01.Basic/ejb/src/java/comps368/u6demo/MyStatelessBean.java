package comps368.u6demo;

import javax.ejb.Stateless;

@Stateless
public class MyStatelessBean implements MyStatelessBeansRemote{
    
    private String str;
    
    @Override
    public String GetStr() {
        return str;
    }
    
    @Override
    public void SetStr(String str1) {
        this.str = "[Stateless]Hello, " + str1;
    }
    
}
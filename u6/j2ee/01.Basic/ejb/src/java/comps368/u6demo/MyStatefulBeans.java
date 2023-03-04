package comps368.u6demo;

import javax.ejb.Stateful;

@Stateful
public class MyStatefulBeans implements MyStatefulBeansRemote{
    
    private String str;
    
    @Override
    public String GetStr() {
        return str;
    }
    
    @Override
    public void SetStr(String str1) {
        this.str = "[Stateful]Hello, " + str1;
    }
    
}
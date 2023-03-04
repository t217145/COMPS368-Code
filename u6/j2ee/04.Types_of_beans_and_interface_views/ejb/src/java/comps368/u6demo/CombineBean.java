package comps368.u6demo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Local;
import javax.ejb.Remote;
import javax.ejb.Remove;
import javax.ejb.Stateless;

@Stateless
@Local(LocalRemote2.class)
@Remote(LessBeanRemote2.class)
public class CombineBean implements LocalRemote2, LessBeanRemote2{

    private final String BEANTYPE = "Combine";
    private String str;
    
    @Override
    public String LocalMtd(String str1) {
        return "Hello 2, " + str1;
    }

    @Override
    public void SetStr(String str1) {
        this.str = "From Combine Bean : " + str1;
    }

    @Override
    public String GetStr() {
        return this.str;
    }

    @Remove
    @Override
    public void Quit(){
        System.out.println(BEANTYPE + " :: GoodBye");
    }    
    
    @PreDestroy
    public void PD(){
        System.out.println(BEANTYPE + " :: PreDestroy");
    }
    
    @PostConstruct
    public void PoC(){
        System.out.println(BEANTYPE + " :: PostConstruct");
    }
    
}

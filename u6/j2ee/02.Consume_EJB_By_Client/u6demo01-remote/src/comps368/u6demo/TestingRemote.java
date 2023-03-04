package comps368.u6demo;

import javax.ejb.Remote;

@Remote
public interface TestingRemote {

    int Calculate(String cmd, int n1, int n2);
    
}
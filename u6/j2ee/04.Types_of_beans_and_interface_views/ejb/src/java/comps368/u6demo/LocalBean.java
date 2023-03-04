package comps368.u6demo;

import javax.ejb.Stateless;

@Stateless
public class LocalBean implements LocalRemote {

    @Override
    public String LocalMtd(String str1) {
        return "Hello, " + str1;
    }
}
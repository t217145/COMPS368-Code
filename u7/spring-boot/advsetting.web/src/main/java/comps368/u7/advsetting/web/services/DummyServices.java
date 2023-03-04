package comps368.u7.advsetting.web.services;

import org.springframework.stereotype.Component;

@Component
public class DummyServices {

    public String helloWorld(String name){
        return "Hello, " + name;
    }
}

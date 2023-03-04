package comps368.q7;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.annotation.security.RolesAllowed;

@Stateless
@DenyAll
public class SecurityGreeting implements SecurityGreetingRemote {

    @Override
    @RolesAllowed({"ADMIN", "USER"})
    public String Mtd1() {
        return "Hello, World";
    }

    @Override
    @PermitAll
    public String Mtd2(String name) {
        return "Hello, " + name;
    }

    @Override
    @RolesAllowed("ADMIN")
    public String Mtd3(String s1, String s2) {
        return "Hello, " + s1 + ", you are " + s2;
    }
    
}

package comps368.q7;

import javax.ejb.Remote;

@Remote
public interface SecurityGreetingRemote {
    String Mtd1();
    String Mtd2(String name);
    String Mtd3(String s1, String s2);
}

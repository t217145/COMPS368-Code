package comps368.u6demo;

import javax.ejb.Stateless;

@Stateless
public class LessBean implements LessBeanRemote {

    private String str;

    @Override
    public void SetStr(String str1) {
        this.str = str1;
    }

    @Override
    public String GetStr() {
        return this.str;
    }
}

package comps368.u6demo;

import javax.ejb.Stateless;

@Stateless
public class Testing implements TestingRemote {

    @Override
    public int Calculate(String cmd, int n1, int n2) {
        int rtn;
        
        switch(cmd){
            case "Add":
                rtn = n1 + n2;
                break;
            case "Sub":
                rtn = n1 - n2;
                break;
            case "Mul":
                rtn = n1 * n2;
                break;
            case "Div":
                rtn = Math.floorDiv(n1, n2);
                break;
            default:
                rtn = 0;
        }
        
        return rtn;
    }
}
package u8demo;

import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;


@Stateless
@WebService
public class Svc1 {

    @WebMethod(operationName="HelloBasic")
    public @WebResult(name="GreetingMessage") String HelloBasic(@WebParam(name="YourName") String name){
        return "Hello, " + name;
    }
    
    @WebMethod(operationName="CalReactangle")
    public String CalReactangle(int length, int height){
        int area = length * height;
        int perimeter = (height + length) * 2;
        
        return "The area is : {" + area + "} and the perimeter is : {" + perimeter + "}";
    }
    
    @WebMethod(operationName="CalculateAreaNPerimeter")
    public @WebResult(name="AreaNPerimeter") Result CalReact(@WebParam(name="RectangleToCalculate") Rectangle r1){
        Result rtn = new Result();
        rtn.setArea(r1.getHeight() * r1.getWidth());
        rtn.setPerimeter((r1.getHeight() + r1.getWidth()) * 2);
        return rtn;
    }
}

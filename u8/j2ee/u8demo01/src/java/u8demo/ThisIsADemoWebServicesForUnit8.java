/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package u8demo;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(serviceName="Svc1")
public class ThisIsADemoWebServicesForUnit8 {
    
    @WebMethod(operationName="GeneralGreeting")
    public @WebResult(name="GreetingMessage") String HelloBasic(){
        return "Hello, Nobody";
    }
    
    @WebMethod(operationName="GreetingWithName")
    public @WebResult(name="GreetingMessage") String HelloParam(@WebParam(name="YourName") String name){
        return "Hello, again : " + name;
    }
}

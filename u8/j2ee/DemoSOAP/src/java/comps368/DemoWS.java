/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comps368;

import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Cyrus Cheng
 */
@WebService(serviceName = "DemoWS")
public class DemoWS {

    /**
     * This is a sample web service operation
     */
    @WebMethod(operationName = "hello")
    public String hello(String txt) {
        return "Hello " + txt + " !";
    }
    @WebMethod(operationName = "orderTicket")
    public String orderTicket(@WebParam(name="eventId") int eventId, @WebParam(name="numberOfTicket") int numberOfTicket){
        return "You have ordered ["+ numberOfTicket+"] for event [" + eventId + "]";
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "CreateStudents")
    public Students CreateStudents(@WebParam(name = "stdId") int stdId, @WebParam(name = "stdName") String stdName) {
        return new Students(stdId, stdName);
    }
    
}

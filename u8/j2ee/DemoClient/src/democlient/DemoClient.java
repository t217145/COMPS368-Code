/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package democlient;

/**
 *
 * @author Cyrus Cheng
 */
public class DemoClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println(orderTicket(1, 2));
        Students std = createStudents(1, "Cyrus");
        System.out.println("Student id : " + std.getStdId());
        System.out.println("Student Name : " + std.getStdName());
    }

    private static String orderTicket(int eventId, int numberOfTicket) {
        democlient.DemoWS_Service service = new democlient.DemoWS_Service();
        democlient.DemoWS port = service.getDemoWSPort();
        return port.orderTicket(eventId, numberOfTicket);
    }

    private static Students createStudents(int stdId, java.lang.String stdName) {
        democlient.DemoWS_Service service = new democlient.DemoWS_Service();
        democlient.DemoWS port = service.getDemoWSPort();
        return port.createStudents(stdId, stdName);
    }
    
}

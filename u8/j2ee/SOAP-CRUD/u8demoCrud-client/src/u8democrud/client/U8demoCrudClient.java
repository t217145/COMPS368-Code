/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package u8democrud.client;

import java.util.List;

/**
 *
 * @author Cyrus Cheng
 */
public class U8demoCrudClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Students s1 = new Students();
        s1.setName("Cyrus");
        s1.setOuhkID("s1039518");
        s1.setPhone("55443544");
        int id1 = addStd(s1);
        System.out.println("Student with DB id [" + id1 + "] added");
        
        Students s2 = new Students();
        s2.setName("ABC");
        s2.setOuhkID("s1234567");
        s2.setPhone("12345678");
        int id2 = addStd(s2);
        System.out.println("Student with DB id [" + id2 + "] added");        
        
        for(Students s : getAll()){
            System.out.println(s.toString());
        }
    }

    private static List<Students> getAll() {
        StdSvc service = new StdSvc();
        Svc3 port = service.getSvc3Port();
        return port.getAll();
    }

    private static int addStd(Students std) {
        StdSvc service = new StdSvc();
        Svc3 port = service.getSvc3Port();
        return port.addStd(std);
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package u8demo03;

/**
 *
 * @author Cyrus Cheng
 */
public class U8demo03 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String msg = helloBasic("Cyrus");
        System.out.println(msg);
        
        u8demo03.Rectangle r = new u8demo03.Rectangle();
        r.setHeight(5);
        r.setWidth(3);
        u8demo03.Result rtn = calculateAreaNPerimeter(r);
        System.out.println("Area : " + rtn.getArea());
        System.out.println("Perimeter : " + rtn.getPerimeter());
    }

    private static Result calculateAreaNPerimeter(u8demo03.Rectangle rectangleToCalculate) {
        u8demo03.Svc1Service service = new u8demo03.Svc1Service();
        u8demo03.Svc1 port = service.getSvc1Port();
        return port.calculateAreaNPerimeter(rectangleToCalculate);
    }

    private static String helloBasic(java.lang.String arg0) {
        u8demo03.Svc1Service service = new u8demo03.Svc1Service();
        u8demo03.Svc1 port = service.getSvc1Port();
        return port.helloBasic(arg0);
    }
    
    
}

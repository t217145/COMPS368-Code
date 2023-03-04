/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package u8demo04;

import javax.xml.ws.WebServiceRef;
import u8demo.Svc1Service;

/**
 *
 * @author Cyrus Cheng
 */
public class Main {

    @WebServiceRef
    private static Svc1Service svc;
    
    public static void main(String[] args) {
        u8demo.Rectangle r = new u8demo.Rectangle();
        r.setHeight(5);
        r.setWidth(3);
        u8demo.Result rtn = svc.getSvc1Port().calculateAreaNPerimeter(r);
        System.out.println("Area : " + rtn.getArea());
        System.out.println("Perimeter : " + rtn.getPerimeter());
    }
    
}

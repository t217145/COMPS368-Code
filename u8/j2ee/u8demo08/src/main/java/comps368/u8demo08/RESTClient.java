package comps368.u8demo08;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import org.glassfish.jersey.jackson.JacksonFeature;

public class RESTClient {
    private static final String URI = "http://localhost:8080/u8demo06/Svc4";
    
    public static void main(String[] args) {
        Client c = ClientBuilder.newClient().register(JacksonFeature.class);//Step 1 - Get the Client object
        WebTarget t = c.target(URI);//Get the WebTarget object
        
        Rectangle r = new Rectangle();
        r.setHeight(5);
        r.setWidth(3);
        
        Result rtn = t.request(MediaType.APPLICATION_XML)
                      .post(Entity.json(r), Result.class);
        System.out.println("Area: " + rtn.getArea());
        System.out.println("Perimeter: " + rtn.getPerimeter());
        c.close();
    } 
}

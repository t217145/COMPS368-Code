package u8demo07;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;


public class U8demo07 {

    private static final String URI = "http://localhost:8080/u8demo05";
    
    public static void main(String[] args) {
        Client c = ClientBuilder.newClient();//Step 1 - Get the Client object
        WebTarget t = c.target(URI);//Get the WebTarget object
        
        /* .request().get(String.class); <= this is a must for eveery request */
        
        //GET /Basic
        String rtn1 = t.path("Basic")
                       .request().get(String.class);
        System.out.println(rtn1);
        
        //GET /?name=xxx
        String rtn2 = t.queryParam("name", "Cyrus")
                       .queryParam("surname", "Cheng")
                       .request().get(String.class);
        System.out.println(rtn2);    
        
        //GET /{name}/{surname}
        String rtn3 = t.path("{surname}/{name}")
                       .resolveTemplate("name", "Cyrus")
                       .resolveTemplate("surname", "Cheng")
                       .request().get(String.class);
        System.out.println(rtn3);  
        
        //GET /mix/{name}
        String rtn4 = t.path("mix/{name}").resolveTemplate("name", "Cyrus")
                       .queryParam("age", "36")
                       .request().get(String.class);
        System.out.println(rtn4);          
    }
    
}

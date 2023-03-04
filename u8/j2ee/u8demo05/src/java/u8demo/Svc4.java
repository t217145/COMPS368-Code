/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package u8demo;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

@Path("/")
public class Svc4 {
    
    @GET
    @Path("/Basic")
    public String helloBasic(){
        return "Hello, Nobody ";
    }    
   
    @GET
    public String helloWithNameQuery(@QueryParam("name") String name, @QueryParam("surname") String surname){
        return "Hello Query, " + name + " " + surname;
    }
    
    @GET
    @Path("/{name}/{surname}")
    public String helloWithNamePath(@PathParam("name") String name, @PathParam("surname") String surname){
        return "Hello Path, " + name + " " + surname;
    }      
    
    @GET
    @Path("/mix/{name}")
    public String helloWithMix(@PathParam("name") String name,  @QueryParam("age") @DefaultValue("10") int age){
        return "Hello Mix, " + name + " and your age is " + age;
    }
}

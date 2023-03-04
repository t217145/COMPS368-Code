package u8demo;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

@Path("/Svc4")
public class ComplexREST {

    @POST
    @Produces(MediaType.APPLICATION_XML)
    @Consumes(MediaType.APPLICATION_JSON)
    public Result CalGeo(Rectangle r1) {
        Result rtn = new Result();
        rtn.setArea(r1.getHeight() * r1.getWidth());
        rtn.setPerimeter((r1.getHeight() + r1.getWidth()) * 2);
        return rtn;
    }

}

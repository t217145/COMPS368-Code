package comp368.demo.cmdclient.feignapi;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import comp368.demo.cmdclient.models.Contacts;

@FeignClient(name="demows")
public interface demowsapi {
    
    @GetMapping(value="/api/index", produces = "application/json")
    List<Contacts> index();

    @PutMapping(value="/api/index", produces = "application/json")
    Contacts add(Contacts newContacts);
}
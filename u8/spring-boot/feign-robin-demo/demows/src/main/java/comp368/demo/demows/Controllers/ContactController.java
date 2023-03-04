package comp368.demo.demows.Controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import comp368.demo.demows.models.Contacts;
import comp368.demo.demows.repos.ContactsRepo;

@RestController
@RequestMapping(value = "/api")
public class ContactController {
    
    @Autowired
    private ContactsRepo repo;

    @GetMapping(value={"","/","/index"}, produces = "application/json")
    public List<Contacts> index(){
        System.out.println("/*****Service called*****/");
        return repo.findAll();
    }

    @GetMapping(value={"/{id}","/index/{id}"}, produces = "application/json")
    public Contacts findOne(@PathVariable("id") Integer id){
        System.out.println("/*****Service called*****/");
        Optional<Contacts> oC = repo.findById(id);
        if(oC.isPresent()){
            return oC.get();
        } else {
            return new Contacts();
        }
    }      

    @PutMapping(value={"","/","/index"}, produces = "application/json", consumes = "application/json")
    public Contacts add(@RequestBody Contacts newContacts){
        System.out.println("/*****Service called*****/");
        return repo.save(newContacts);
    }

    @PostMapping(value={"/{id}","/index/{id}"}, produces = "application/json", consumes = "application/json")
    public Contacts update(@PathVariable("id") Integer id, @RequestBody Contacts updContacts){
        System.out.println("/*****Service called*****/");
        Optional<Contacts> oC = repo.findById(id);
        if(oC.isPresent()){
            repo.save(updContacts);
            return updContacts;
        } else {
            return new Contacts();
        }
    }

    @DeleteMapping(value={"/{id}","/index/{id}"}, produces = "application/json")
    public Contacts delete(@PathVariable("id") Integer id){
        Optional<Contacts> oC = repo.findById(id);
        if(oC.isPresent()){
            repo.deleteById(id);
            return oC.get();
        } else {
            return new Contacts();
        }
    }    
}
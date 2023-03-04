package comps368.u8demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StdSvc{

    @Autowired
    private StdRepository svc;

    @GetMapping("/")
    public @ResponseBody Iterable<Students> getAll(){
        return svc.findAll();
    }
    
    @GetMapping("/{id}")
    public @ResponseBody Students getById(@PathVariable("id")int id){
        return svc.findById(id).get();
    }    

    @PostMapping("/")
    public @ResponseBody int addStd(@RequestBody Students std){
        svc.save(std);
        List<Students> stds = svc.findAllByOuhkId(std.getOuhkID());
        return stds.get(0).getId();
    }

    @PutMapping("/{id}")
    public @ResponseBody Students addStd(@PathVariable("id")int id, @RequestBody Students std){
        Students s = svc.findById(id).get();
        s.setName(std.getName());
        s.setOuhkID(std.getOuhkID());
        s.setPhone(std.getPhone());
        svc.save(s);
        return s;
    }

    @DeleteMapping("/{id}")
    public void delStd(@PathVariable("id")int id){
        svc.deleteById(id);
    }
}
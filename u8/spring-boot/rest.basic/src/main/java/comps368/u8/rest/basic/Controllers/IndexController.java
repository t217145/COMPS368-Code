package comps368.u8.rest.basic.Controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import comps368.u8.rest.basic.modes.CustomException;
import comps368.u8.rest.basic.modes.Students;
import comps368.u8.rest.basic.repos.StudentsRepo;
import org.springframework.boot.context.properties.bind.DefaultValue;

@RestController
@RequestMapping("/students")
public class IndexController {
    @Autowired
    private StudentsRepo repo;

    @GetMapping({"", "/", "/index"})
    public List<Students> index(){
        return repo.findAll();
    }

    @GetMapping("/index/{stdId}")
    public ResponseEntity<Students> indexById(@PathVariable("stdId") Integer stdId){
        Optional<Students> oStd = repo.findById(stdId);
        if(oStd.isEmpty()){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(oStd.get());
        }
    }  

    @PostMapping({"","/"})
    public Students add(@RequestBody Students newStd) throws CustomException{
        Optional<Students> oStd = repo.getByCode(newStd.getStdCode());
        if(!oStd.isEmpty()){
            throw new CustomException("Err001", "Student Code already exists!");
        }
        repo.save(newStd);
        return newStd;
    }

    @PutMapping({"","/"})
    public Students update(@RequestBody Students updatedStd) throws CustomException{
        Optional<Students> oStd = repo.getByCode(updatedStd.getStdCode());
        if(!oStd.isEmpty()){
            throw new CustomException("Err001", "Student Code already exists!");
        }        
        repo.save(updatedStd);
        return updatedStd;
    }

    @DeleteMapping({"","/"})// url = http://localhost:8080/students?stdId=123
    public ResponseEntity<Void> delete(@DefaultValue("-1") @RequestParam("stdId") Integer deleteStdId){        
        Optional<Students> oStd = repo.findById(deleteStdId);
        if(oStd.isEmpty()){
            return ResponseEntity.notFound().build();
        } else {
            repo.deleteById(deleteStdId);
            return ResponseEntity.accepted().build();
        }
    }
}
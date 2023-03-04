package comps368.u8.eureka.web.svc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.boot.context.properties.bind.DefaultValue;
import comps368.u8.eureka.web.svc.models.CustomException;
import comps368.u8.eureka.web.svc.models.Students;
import comps368.u8.eureka.web.svc.repos.StudentsRepo;

@RestController
@RequestMapping("/students")
public class StudentsController {
    @Autowired
    private StudentsRepo repo;

    @GetMapping({"", "/", "/index"})
    public List<Students> index(){
        System.err.println("/ ******* get all called *******/");
        return repo.findAll();
    }

    @GetMapping("/index/{stdId}")
    public ResponseEntity<Students> indexById(@PathVariable("stdId") Integer stdId){
        System.err.println("/ ******* get one called *******/");
        Optional<Students> oStd = repo.findById(stdId);
        if(oStd.isEmpty()){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(oStd.get());
        }
    }  

    @PostMapping({"","/"})
    public Students add(@RequestBody Students newStd) throws CustomException{
        System.err.println("/ ******* add called *******/");
        Optional<Students> oStd = repo.getByCode(newStd.getStdCode());
        if(!oStd.isEmpty()){
            throw new CustomException("Err001", "Student Code already exists!");
        }
        repo.save(newStd);
        return newStd;
    }

    @PutMapping({"","/"})
    public Students update(@RequestBody Students updatedStd) throws CustomException{
        System.err.println("/ ******* update called *******/");
        Optional<Students> oStd = repo.getByCode(updatedStd.getStdCode());
        if(!oStd.isEmpty() && oStd.get().getStdId() != updatedStd.getStdId()){
            throw new CustomException("Err001", "Student Code already exists!");
        }        
        repo.save(updatedStd);
        return updatedStd;
    }

    @DeleteMapping({"","/"})// url = http://localhost:8080/students?stdId=123
    public ResponseEntity<Void> delete(@DefaultValue("-1") @RequestParam("stdId") Integer deleteStdId){   
        System.err.println("/ ******* delete called *******/");     
        Optional<Students> oStd = repo.findById(deleteStdId);
        if(oStd.isEmpty()){
            return ResponseEntity.notFound().build();
        } else {
            repo.deleteById(deleteStdId);
            return ResponseEntity.accepted().build();
        }
    }
}
package comps368.u8.eureka.web.mvc.services;

import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import comps368.u8.eureka.web.mvc.models.Students;

@FeignClient(name = "web-svc")
public interface StudentsSvc {
    @GetMapping(value = "/students/index", produces="application/json")
    List<Students> getAll();

    @GetMapping(value = "/students/index/{stdId}", produces="application/json")
    Students getOneById(@PathVariable("stdId") Integer stdId);

    @PostMapping(value = "/students", produces="application/json")
    public Students create(@RequestBody Students newStd);

    @PutMapping(value = "/students", produces="application/json")
    public Students update(@RequestBody Students updatedStd);

    @DeleteMapping(value = "/students")
    public void delete(@RequestParam("stdId") Integer deleteStdId);
}

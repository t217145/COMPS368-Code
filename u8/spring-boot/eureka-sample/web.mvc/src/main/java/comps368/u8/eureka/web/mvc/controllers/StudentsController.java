package comps368.u8.eureka.web.mvc.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import comps368.u8.eureka.web.mvc.models.Students;
import comps368.u8.eureka.web.mvc.services.StudentsSvc;

@Controller
public class StudentsController {
    @Autowired
    private StudentsSvc svc;

    @GetMapping({"","/","/index"})
    public String index(ModelMap m){
        m.addAttribute("allStd", svc.getAll());
        return "index";
    }

    @GetMapping("/getOne/{stdId}")
    public String index(@PathVariable("stdId") Integer stdId, ModelMap m){
        Students std = svc.getOneById(stdId);
        m.addAttribute("allStd", Arrays.asList(std));
        return "index";
    }    

    @GetMapping("/add")
    public String add(ModelMap m){
        svc.create(new Students(0, "s1039518", "Cyrus Cheng", 'M'));
        svc.create(new Students(0, "s1064213", "Mandy", 'F'));
        svc.create(new Students(0, "s1234567", "Ruby Cheng", 'F'));
        return "redirect:/index";
    }

    @GetMapping("/update")
    public String update(ModelMap m){
        List<Students> allStd = svc.getAll();
        Optional<Students> oStd = allStd.stream().filter(s -> s.getStdCode().equals("s1234567")).findFirst();
        if(oStd.isPresent()){
            Students std = oStd.get();
            std.setStdName("Mandy Ko");
            svc.update(std);
        }
        return "redirect:/index";
    }

    @GetMapping("/delete")
    public String delete(ModelMap m){
        svc.delete(3);
        return "redirect:/index";
    }    
}

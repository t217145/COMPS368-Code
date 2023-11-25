package comps368.u5.basic.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/variables")
public class GetVariableController {

    //Sample http://localhost:8080/variables/byPath/Cyrus/39
    @RequestMapping("/byPath/{name}/{age}")
    public String path(@PathVariable("name") String name, @PathVariable("age") int age, ModelMap m){
        m.addAttribute("myName", name);
        m.addAttribute("myAge", age);        
        return "index";
    }

    //Sample http://localhost:8080/variables/byQuery?name=Ruby&&age=13
    @RequestMapping("/byQuery")
    public String query(@RequestParam("name") String name, @RequestParam("age") int age, ModelMap m){
        m.addAttribute("myName", name);
        m.addAttribute("myAge", age);        
        return "index";
    }
}